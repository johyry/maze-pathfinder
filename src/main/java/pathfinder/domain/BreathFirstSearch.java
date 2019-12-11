package pathfinder.domain;

import java.util.LinkedList;
//import java.util.Queue;
import pathfinder.datastructures.Queue;

/**
 *
 * Leveyshaku reitinhakualgoritmi.
 *
 * @author johyry
 */
public class BreathFirstSearch {

    private int[][] maze;
    private Coordinates start;
    private Coordinates goal;
    private Coordinates[][] reitti;
    private int[][] etaisyys;
    private int roundsOfCalculations;

    public BreathFirstSearch(int[][] maze, Coordinates start, Coordinates goal) {
        this.maze = maze;
        this.start = start;
        this.goal = goal;

    }

    public int init() {
        return search();

    }

    /**
     * Suorittaa itse haun, palauttaa 1, jos reitti löytyi, ja 0 jos ei
     *
     * laskee samalla reitin reitti taulukkoon
     *
     * @return 1 jos löytyi, 0 jos ei
     */
    public int search() {
        Queue jono = new Queue();
//        Queue<Coordinates> jono = new LinkedList<>();

        int foundRoute = 0;

        jono.add(start);

        int[][] vierailtu = new int[maze.length][maze[0].length];
        etaisyys = new int[maze.length][maze[0].length];
        reitti = new Coordinates[maze.length][maze[0].length];

        etaisyys[0][start.getX()] = 0;
        vierailtu[0][start.getX()] = 1;

        while (!jono.isEmpty()) {
            roundsOfCalculations++;

            Coordinates xy = jono.poll();
            int x = xy.getX();
            int y = xy.getY();

            // Jos ollaan maalissa, lopeta looppaus
            if (y == goal.getY() && x == goal.getX()) {
                foundRoute = 1;
                break;
            }

            // Reitti ylös
            // Tarkista, ollaanko aloituspisteessä, jos ollaan, ei tarvitse tarkistaa ylläolevaa pistettä
            if (y != 0) {
                // Onko yllä reitti
                if (maze[y - 1][x] == 1) {
                    // Onko reitillä vierailtu
                    if (vierailtu[y - 1][x] != 1) {
                        // Reitin lisäys jonoon
                        jono.add(new Coordinates(y - 1, x));
                        // Ylläolevan pisteen vierailluksi merkitseminen
                        vierailtu[y - 1][x] = 1;
                        // Etäisyyden mittaaminen ylläolevalle pisteelle
                        etaisyys[y - 1][x] = etaisyys[y][x] + 1;
                        // Mistä pisteestä ollaan tultu ylläolevaan pisteeseen
                        reitti[y - 1][x] = new Coordinates(y, x);
                    }
                }
            }

            // Reitti oikealle
            if (maze[y][x + 1] == 1) {
                if (vierailtu[y][x + 1] != 1) {
                    jono.add(new Coordinates(y, x + 1));
                    vierailtu[y][x + 1] = 1;
                    etaisyys[y][x + 1] = etaisyys[y][x] + 1;
                    reitti[y][x + 1] = new Coordinates(y, x);
                }
            }

            // Reitti alas
            if (y != maze.length - 1) {
                if (maze[y + 1][x] == 1) {
                    if (vierailtu[y + 1][x] != 1) {
                        jono.add(new Coordinates(y + 1, x));
                        vierailtu[y + 1][x] = 1;
                        etaisyys[y + 1][x] = etaisyys[y][x] + 1;
                        reitti[y + 1][x] = new Coordinates(y, x);
                    }
                }
            }

            // Reitti vasemmalle
            if (maze[y][x - 1] == 1) {
                if (vierailtu[y][x - 1] != 1) {
                    jono.add(new Coordinates(y, x - 1));
                    vierailtu[y][x - 1] = 1;
                    etaisyys[y][x - 1] = etaisyys[y][x] + 1;
                    reitti[y][x - 1] = new Coordinates(y, x);
                }
            }

        }

        return foundRoute;
    }

    /**
     * Palauttaa löydetyn reitin int[][] taulukkona. Reitti merkattu ykkösinä
     *
     * @return int[][] taulukko
     */
    public int[][] getFoundRoute() {
        int[][] foundRoute = new int[maze.length][maze[0].length];
        table2DInitAsZeros(foundRoute);

        foundRoute[goal.getY()][goal.getX()] = 1;

        Coordinates current = reitti[goal.getY()][goal.getX()];

        while (true) {
            current = reitti[current.getY()][current.getX()];
            foundRoute[current.getY()][current.getX()] = 1;

            if (current.getX() == start.getX() && current.getY() == start.getY()) {
                break;
            }
        }

        return foundRoute;
    }

    private void table2DInitAsZeros(int[][] table) {
        for (int y = 0; y < table.length; y++) {
            for (int x = 0; x < table[0].length; x++) {
                table[y][x] = 0;
            }
        }
    }

    /**
     * Palauttaa reitin pituuden
     *
     * @return -1, jos ei löytynyt ja muuten reitin pituuden
     */
    public int getRouteLength() {
        return etaisyys[goal.getY()][goal.getX()];
    }

    public int getRoundsOfCalculations() {
        return roundsOfCalculations;
    }

}
