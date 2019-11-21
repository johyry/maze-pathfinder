package pathfinder.domain;

import java.util.LinkedList;
import java.util.Queue;

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
    private int foundRoute;

    public BreathFirstSearch(int[][] maze, Coordinates start, Coordinates goal) {
        this.maze = maze;
        this.start = start;
        this.goal = goal;

    }

    public void init() {
        int pituus = search();
        if (foundRoute == 1) {
            System.out.println("Leveyshaku: ");
            System.out.println("Reitti löydetty.");
            System.out.println("Reitin pituus: " + pituus);
        } else {
            System.out.println("Reittiä ei löytynyt.");
        }

    }

    /**
     * Suorittaa itse haun, palauttaa reitin pituuden testauksen helpottamiseksi
     * laskee samalla reitin reitti taulukkoon
     *
     * @return int reitin pituuden
     */
    public int search() {
        Queue<Coordinates> jono = new LinkedList<>();

        int roundsDone = 0;
        this.foundRoute = 0;

        jono.add(start);

        int[][] vierailtu = new int[maze.length][maze[0].length];
        int[][] etaisyys = new int[maze.length][maze[0].length];
        reitti = new Coordinates[maze.length][maze[0].length];

        etaisyys[0][start.getX()] = 0;
        vierailtu[0][start.getX()] = 1;

        while (!jono.isEmpty()) {
            roundsDone++;

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

        System.out.println("Rounds done leveyshaku: " + roundsDone);
        return etaisyys[goal.getY()][goal.getX()];
    }

    /**
     * Printtaa haun löytämän reitin alusta loppuun koordinaatteina. Lähinnä
     * debug tarkoituksiin.
     */
    public void printRoute(Coordinates[][] reitti) {

        System.out.println(goal);

        Coordinates current = reitti[goal.getY()][goal.getX()];

        while (true) {
            current = reitti[current.getY()][current.getX()];
            System.out.println(current);

            if (current.getX() == start.getX() && current.getY() == start.getY()) {
                break;
            }
        }
    }
    
    

}
