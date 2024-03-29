
package pathfinder.domain;

/**
 *
 * Luokka lähtökoordinaattien ja maalikoordinaattien löytämistä varten
 * 
 * @author johyry
 */
public class MazeAnalyzer {
    private int[][] maze;
    
    public MazeAnalyzer(int[][] maze) {
        this.maze = maze;
    }
    
    /**
     * Löytää lähdön X koordinaatin, Y on aina 0
     *
     * @return CoordinatesXY olion lähdön koordinaateista, null jos ei löytynyt
     */
    public Coordinates findStartCoordinates() {

        for (int i = 0; i < maze[0].length; i++) {
            int node = maze[0][i];
            if (node == 1) {
                return new Coordinates(0, i);
            }
        }
        return new Coordinates(-1, -1);
    }

    /**
     * Löytää maalin X koordinaatin, Y on aina maze.length-1
     *
     * @return CoordinatesXY olion maalin koordinaateista
     */
    public Coordinates findGoalCoordinates() {

        for (int i = 0; i < maze[0].length; i++) {
            int node = maze[maze.length - 1][i];
            if (node == 1) {
                return new Coordinates(maze.length-1, i);
            }
        }
        return new Coordinates(-1, -1);
    }
}
