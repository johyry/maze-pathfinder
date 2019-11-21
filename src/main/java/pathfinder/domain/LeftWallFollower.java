
package pathfinder.domain;

/**
 *
 * Algoritmi joka etsii reittiä ulos labyrintistä pitämällä aina
 * vasemman käden seinässä. Löytää reitin ulos vain täydellisistä labyrinteistä.
 * 
 * @author johyry
 */
public class LeftWallFollower {

    private int[][] maze;
    private Coordinates start;
    private Coordinates goal;

    public LeftWallFollower(int[][] maze, Coordinates start, Coordinates goal) {
        this.maze = maze;
        this.start = start;
        this.goal = goal;
    }

    public void init() {
        System.out.println("LFW: ");
        System.out.println("Reitti löytyi, laskentakierroksia kertyi: " + search());
    }
    
    /**
     * Suorittaa itse haun. Palauttaa laskukierrosten määrän.
     *
     * @return int määrän suorituskertoja loopissa joka etsii reittiä
     */
    public int search() {
        Coordinates current = start;
        Coordinates previous = start;

        int roundsOfCalculationsDone = 0;

        while (true) {
            roundsOfCalculationsDone++;

            if (current.equals(goal)) {
                break;
            }

            Coordinates next = followLeftWall(current, previous);
            previous = current;
            current = next;

        }

        return roundsOfCalculationsDone;
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

    public Coordinates nextStepWhenPreviousWasUp(Coordinates current, Coordinates previous) {
        int y = current.getY();
        int x = current.getX();

        // Oikea
        if (previous.getX() != x + 1 && maze[y][x + 1] == 1) {
            return new Coordinates(y, x + 1);

        }

        // Alas
        if (y != maze.length - 1) {
            if (previous.getY() != y + 1 && maze[y + 1][x] == 1) {
                return new Coordinates(y + 1, x);

            }
        }

        // Vasen
        if (previous.getX() != x - 1 && maze[y][x - 1] == 1) {
            return new Coordinates(y, x - 1);
        }

        // Jos umpikuja, palautetaan edellinen
        return previous;
    }

    public Coordinates nextStepWhenPreviousWasRight(Coordinates current, Coordinates previous) {
        int y = current.getY();
        int x = current.getX();

        // Alas
        if (y != maze.length - 1) {
            if (previous.getY() != y + 1 && maze[y + 1][x] == 1) {
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

    public Coordinates nextStepWhenPreviousWasDown(Coordinates current, Coordinates previous) {
        int y = current.getY();
        int x = current.getX();

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

        // Oikea
        if (previous.getX() != x + 1 && maze[y][x + 1] == 1) {
            return new Coordinates(y, x + 1);

        }

        // Jos umpikuja, palautetaan edellinen
        return previous;
    }

    public Coordinates nextStepWhenPreviousWasLeft(Coordinates current, Coordinates previous) {
        int y = current.getY();
        int x = current.getX();

        // Ylös
        if (y != 0) {
            if (previous.getY() != y - 1 && maze[y - 1][x] == 1) {
                return new Coordinates(y - 1, x);

            }
        }

        // Oikea
        if (previous.getX() != x + 1 && maze[y][x + 1] == 1) {
            return new Coordinates(y, x + 1);

        }

        // Alas
        if (y != maze.length - 1) {
            if (previous.getY() != y + 1 && maze[y + 1][x] == 1) {
                return new Coordinates(y + 1, x);

            }
        }

        // Jos umpikuja, palautetaan edellinen
        return previous;
    }

}
