/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.domain;

import java.util.Random;

/**
 *
 * @author johyry
 */
public class Thremaux {

    private int[][] maze;
    private Coordinates start;
    private Coordinates goal;
    public int[][] marked; // public for testing purposes
    private Coordinates[][] previousPoint;

    public Thremaux(int[][] maze, Coordinates start, Coordinates goal) {
        this.maze = maze;
        this.start = start;
        this.goal = goal;
        this.marked = new int[maze.length][maze[0].length];
        table2DInitZeros(marked);
        this.previousPoint = new Coordinates[maze.length][maze[0].length];
        previousPoint[start.getY()][start.getX()] = start;
    }

    public void init() {

    }

    public void search() {
        // 1. Liiku Taulukossa
        // 2. Merkkaa reitti aina kun liikut sillä
        // 3. Tarkista, oletko maalissa
        // 4. Älä koskaan mene reitille jossa on kaksi merkkiä
        // 5. Kun saavut risteykseen, jossa muita reittejä kun tuloreitti ei ole merkitty, valitse satunnainen reitti
        // 6. Muuten:
        //     - Jos reitillä josta saavuit on vain yksi merkki palaa sinne
        //     - Jos kaksi, valitse reitti jossa on vähiten merkkejä, jos yhtä paljon, valitse satunnaisesti

//        Huomioita:
//            - Pidä kirjaa mistä saavuit
        // Aloituspiste
        Coordinates current = start;

        while (true) {
            int x = current.getX();
            int y = current.getY();

            Coordinates previousNode = previousPoint[y][x];

            // taulukko, jossa on tiedot onko risteys ([0]), ja mitkä suunnat mahdollisia([1-4])
            int[] junctionTable = howManyRouteOptions(current, previousNode);
            // Jos on risteys
            if (junctionTable[0] > 1) {

                // Onko muita reittejä kun tuloreitti merkitty
                if (!otherOptionsThanPreviousMarked(current, previousNode)) {
                    // Jos ei, valitse satunnainen reitti mahdollisista
                    current = chooseRandomRoute(junctionTable, current, previousNode, randomInt(junctionTable[0]));
                    break;
                    
                    // Jos muita reittejä on merkitty
                } else {
                    
                }
                
                

            }
        }

    }
    
    /**
     * Arpoo satunnaisen numeron väliltä 0-possibleOptions
     *
     * @return satunnainen int 0-possibleOptions väliltä
     */
    public int randomInt(int possibleOptions) {
        Random random = new Random();
        int randomNr = random.nextInt(possibleOptions);
        return randomNr;
    }
    
    /**
     * Valitsee mahdollisista vaihtoehdoista risteyksessä satunnaisen ja palauttaa sen
     *
     * @return Coordinates -olion, missä on valitun reitin ensimmäisen pisteen koordinaatit
     */
    public Coordinates chooseRandomRoute(int[] junctionTable, Coordinates current, Coordinates previous, int randomNr) {
        int amount = junctionTable[0];
        
        int x = current.getX();
        int y = current.getY();
        
        if (junctionTable[1]  == 1) {
            randomNr--;
            if (randomNr == 0) {
                return new Coordinates(y-1, x);
            }
        }
        
        if (junctionTable[2]  == 1) {
            randomNr--;
            if (randomNr == 0) {
                return new Coordinates(y, x+1);
            }
        }
        
        if (junctionTable[3]  == 1) {
            randomNr--;
            if (randomNr == 0) {
                return new Coordinates(y+1, x);
            }
        }
        
        if (junctionTable[4]  == 1) {
            randomNr--;
            if (randomNr == 0) {
                return new Coordinates(y, x-1);
            }
        }
        return null;
    }

    /**
     * Tarkistaa, onko risteyksessä muut mahdolliset reitit merkattu kuin se mistä tultiin
     *
     * @return true jos on, false jos ei
     */
    public boolean otherOptionsThanPreviousMarked(Coordinates current, Coordinates previous) {
        int x = current.getX();
        int y = current.getY();

        int amount = 0;

        // Ylös
        if (y != 0) {
            if (previous.getY() != y - 1 && marked[y - 1][x] > 0) {
                amount++;
            }
        }

        // Oikea
        if (previous.getX() != x + 1 && marked[y][x + 1] > 0) {
            amount++;
        }

        // Alas
        if (y != maze.length - 1) {
            if (previous.getY() != y + 1 && marked[y + 1][x] > 0) {
                amount++;
            }
        }

        // Vasen
        if (previous.getX() != x - 1 && marked[y][x - 1] > 0) {
            amount++;
        }

        return amount > 0;
    }

    public int[] optionsMarked(Coordinates current) {
        int[] table = new int[5];
        for (int i = 0; i < table.length; i++) {
            table[i] = 0;
        }

        int x = current.getX();
        int y = current.getY();

        if (marked[y - 1][x] == 1) {

        }

        return table;
    }

    /**
     * Laskee 1) mahdollisten suuntien määrän kyseisestä pisteestä kohtaan
     * taulukko[0] 2) minne suuntaan voi pisteestä liikkua, 1 == on reitti, 0 ==
     * ei ole reittiä [1] = ylös [2] = oikealle [3] = alas [4] = vasemmalle
     *
     *
     * @return taulukon, taulukko[0] = mahdollisten suuntien määrän, table[1]
     * ylös, table[2] oikealle, table[3] alas, table[4] vasemmalle
     */
    public int[] howManyRouteOptions(Coordinates current, Coordinates previous) {
        int[] table = new int[5];
        for (int i = 0; i < table.length; i++) {
            table[i] = 0;
        }

        int x = current.getX();
        int y = current.getY();

        int options = 0;

        // Ylös
        if (y != 0) {
            if (previous.getY() != y - 1 && maze[y - 1][x] == 1) {
                options++;
                table[1] = 1;
            }
        }

        // Oikea
        if (previous.getX() != x + 1 && maze[y][x + 1] == 1) {
            options++;
            table[2] = 1;
        }

        // Alas
        if (y != maze.length - 1) {
            if (previous.getY() != y + 1 && maze[y + 1][x] == 1) {
                options++;
                table[3] = 1;
            }
        }

        // Vasen
        if (previous.getX() != x - 1 && maze[y][x - 1] == 1) {
            options++;
            table[4] = 1;
        }

        table[0] = options;

        return table;
    }
    
    private void table2DInitZeros(int[][] table) {
        for (int y = 0; y < table.length; y++) {
            for (int x = 0; x < table[0].length; x++) {
                table[y][x] = 0;
            }
        }
    }
}
