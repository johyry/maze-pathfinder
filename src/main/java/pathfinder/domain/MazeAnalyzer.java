/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.domain;

/**
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
    public CoordinatesXY findStartCoordinates() {

        for (int i = 0; i < maze[0].length; i++) {
            int node = maze[0][i];
            if (node == 1) {
                return new CoordinatesXY(0, i);
            }
        }
        return null;
    }

    /**
     * Löytää maalin X koordinaatin, Y on aina maze.length-1
     *
     * @return CoordinatesXY olion maalin koordinaateista
     */
    public CoordinatesXY findGoalCoordinates() {

        for (int i = 0; i < maze[0].length; i++) {
            int node = maze[maze.length - 1][i];
            if (node == 1) {
                return new CoordinatesXY(maze.length-1, i);
            }
        }
        return null;
    }
}
