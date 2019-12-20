package pathfinder.domain;

/**
 *
 * Algoritmi joka etsii reittiä ulos labyrintistä pitämällä aina vasemman käden
 * seinässä. Löytää reitin ulos vain täydellisistä labyrinteistä.
 *
 * @author johyry
 */
public class LeftWallFollower {

    private int[][] maze;
    private Coordinates start;
    private Coordinates goal;
    private int roundsOfCalculations;
    private int[][] marked;

    public LeftWallFollower(int[][] maze, Coordinates start, Coordinates goal) {
        this.maze = maze;
        this.start = start;
        this.goal = goal;
        marked = new int[maze.length][maze[0].length];
        table2DInitAsZeros(marked);
    }

    /**
     * Suorittaa itse haun, palauttaa 1, jos reitti löytyi, ja 0 jos ei
     *
     * @return 1 jos löytyi, 0 jos ei
     */
    public int init() {
        return search();
    }

    /**
     * Suorittaa itse haun. Palauttaa 1 jos reitti löytyi, 0 jos ei
     *
     * @return 1 jos löytyi, 0 jos ei
     */
    public int search() {
        Coordinates current = start;
        Coordinates previous = start;

        roundsOfCalculations = 0;
        int foundRoute = 0;

        while (true) {
            roundsOfCalculations++;

            if (current.equals(goal)) {
                marked[current.getY()][current.getX()]++;
                foundRoute = 1;
                break;
            }

            if (current.equals(start) && roundsOfCalculations > 1) {
                break;
            }
            
            marked[current.getY()][current.getX()]++;

            Coordinates next = followLeftWall(current, previous);
            previous = current;
            current = next;

        }

        return foundRoute;
    }

    /**
     * Liikkuu labyrintissa eteenpäin pitäen aina vasemman käden seinässä.
     *
     * @return seuraavan pisteen Coordinates -olio
     */
    public Coordinates followLeftWall(Coordinates current, Coordinates previous) {
        int y = current.getY();
        int x = current.getX();

        Coordinates next = previous;

        // Jos ollaan alussa, palautetaan alempi piste
        if (current.equals(start)) {
            next = new Coordinates(y + 1, x);
        }

        // Jos edellinen oli ylhäällä, aloitetaan oikeasta reunasta ensin
        if (y != 0) {
            if (previous.getY() == y - 1) {
                next = nextStepWhenPreviousWasUp(current, previous);
            }
        }

        // Jos edellinen oli oikealla, aloitetaan alhaalta ensin
        if (previous.getX() == x + 1) {
            next = nextStepWhenPreviousWasRight(current, previous);
        }

        // Jos edellinen oli alhaalla, aloitetaan vasemmalta ensin
        if (y != maze.length - 1) {
            if (previous.getY() == y + 1) {
                next = nextStepWhenPreviousWasDown(current, previous);
            }
        }

        // Jos edellinen oli vasemmalla, aloitetaan ylhäältä ensin
        if (previous.getX() == x - 1) {
            next = nextStepWhenPreviousWasLeft(current, previous);
        }

        return next;
    }

    /**
     * Liikkuu labyrintissa eteenpäin pitäen aina vasemman käden seinässä.
     * Edellinen ruutu ylempänä
     *
     * @return seuraavan pisteen Coordinates -olio
     */
    public Coordinates nextStepWhenPreviousWasUp(Coordinates current, Coordinates previous) {
        int y = current.getY();
        int x = current.getX();

        // Oikea
        if (maze[y][x + 1] == 1) {
            return new Coordinates(y, x + 1);

        }

        // Alas
        if (y != maze.length - 1) {
            if (maze[y + 1][x] == 1) {
                return new Coordinates(y + 1, x);

            }
        }

        // Vasen
        if (maze[y][x - 1] == 1) {
            return new Coordinates(y, x - 1);
        }

        // Jos umpikuja, palautetaan edellinen
        return previous;
    }

    /**
     * Liikkuu labyrintissa eteenpäin pitäen aina vasemman käden seinässä.
     * Edellinen ruutu oikealla
     *
     * @return seuraavan pisteen Coordinates -olio
     */
    public Coordinates nextStepWhenPreviousWasRight(Coordinates current, Coordinates previous) {
        int y = current.getY();
        int x = current.getX();

        // Alas
        if (y != maze.length - 1) {
            if (maze[y + 1][x] == 1) {
                return new Coordinates(y + 1, x);

            }
        }

        // Vasen
        if (previous.getX() != x - 1 && maze[y][x - 1] == 1) {
            return new Coordinates(y, x - 1);
        }

        // Ylös
        if (y != 0) {
            if (previous.getY() != y - 1 && maze[y - 1][x] == 1) {
                return new Coordinates(y - 1, x);

            }
        }

        // Jos umpikuja, palautetaan edellinen
        return previous;
    }

    /**
     * Liikkuu labyrintissa eteenpäin pitäen aina vasemman käden seinässä.
     * Edellinen ruutu alhaalla
     *
     * @return seuraavan pisteen Coordinates -olio
     */
    public Coordinates nextStepWhenPreviousWasDown(Coordinates current, Coordinates previous) {
        int y = current.getY();
        int x = current.getX();

        // Vasen
        if (maze[y][x - 1] == 1) {
            return new Coordinates(y, x - 1);
        }

        // Ylös
        if (y != 0) {
            if (maze[y - 1][x] == 1) {
                return new Coordinates(y - 1, x);

            }
        }

        // Oikea
        if (maze[y][x + 1] == 1) {
            return new Coordinates(y, x + 1);

        }

        // Jos umpikuja, palautetaan edellinen
        return previous;
    }

    /**
     * Liikkuu labyrintissa eteenpäin pitäen aina vasemman käden seinässä.
     * Edellinen ruutu vasemmalla
     *
     * @return seuraavan pisteen Coordinates -olio
     */
    public Coordinates nextStepWhenPreviousWasLeft(Coordinates current, Coordinates previous) {
        int y = current.getY();
        int x = current.getX();

        // Ylös
        if (y != 0) {
            if (maze[y - 1][x] == 1) {
                return new Coordinates(y - 1, x);

            }
        }

        // Oikea
        if (maze[y][x + 1] == 1) {
            return new Coordinates(y, x + 1);

        }

        // Alas
        if (y != maze.length - 1) {
            if (maze[y + 1][x] == 1) {
                return new Coordinates(y + 1, x);

            }
        }

        // Jos umpikuja, palautetaan edellinen
        return previous;
    }
    
    
    private void table2DInitAsZeros(int[][] table) {
        for (int y = 0; y < table.length; y++) {
            for (int x = 0; x < table[0].length; x++) {
                table[y][x] = 0;
            }
        }
    }

    /**
     * Palauttaa laskentakierrosten määrän
     * 
     * @return laskentakierrosten määrän
     */
    public int getRoundsOfCalculations() {
        return roundsOfCalculations;
    }

    /**
     * Palauttaa taulukon käydyistä ruuduista
     * 1 = käyty kerran
     * 2 = käyty kahdesti
     * 0 = ei käyty
     * 
     * @return taulukko int[][] käydyistä ruuduista
     */
    public int[][] getMarked() {
        return marked;
    }
    
    

}
