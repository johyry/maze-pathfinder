/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import pathfinder.domain.LeftWallFollower;
import pathfinder.domain.MazeAnalyzer;

/**
 *
 * @author johyry
 */
public class LeftWallFollowerTest {

    private int[][] mazeExample1 = {
        {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 1, 1, 1, 0, 1, 1, 1, 0, 0},
        {0, 0, 0, 1, 0, 1, 0, 1, 0, 0},
        {0, 1, 1, 1, 0, 0, 0, 1, 0, 0},
        {0, 1, 0, 0, 0, 1, 1, 1, 1, 0},
        {0, 1, 0, 1, 0, 1, 0, 0, 1, 0},
        {0, 1, 0, 1, 0, 1, 1, 0, 1, 0},
        {0, 1, 1, 1, 0, 1, 1, 0, 1, 0},
        {0, 0, 0, 1, 1, 1, 0, 1, 1, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 1, 0}
    };
    private LeftWallFollower lwf;
    private int[][] mazeExample2 = {
        {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 1, 1, 1, 0, 1, 1, 1, 0, 0},
        {0, 0, 0, 1, 0, 1, 0, 1, 0, 0},
        {0, 1, 1, 1, 0, 0, 0, 1, 0, 0},
        {0, 1, 0, 0, 0, 1, 1, 1, 1, 0},
        {0, 1, 0, 1, 0, 1, 0, 0, 1, 0},
        {0, 1, 0, 1, 0, 1, 1, 0, 1, 0},
        {0, 1, 1, 1, 0, 1, 1, 0, 1, 0},
        {0, 0, 0, 1, 1, 1, 0, 1, 1, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
    private LeftWallFollower lwf2;

    @Before
    public void setup() {
        MazeAnalyzer analyzer = new MazeAnalyzer(mazeExample1);
        lwf = new LeftWallFollower(mazeExample1, analyzer.findStartCoordinates(), analyzer.findGoalCoordinates());
        MazeAnalyzer analyzer2 = new MazeAnalyzer(mazeExample2);
        lwf2 = new LeftWallFollower(mazeExample2, analyzer2.findStartCoordinates(), analyzer2.findGoalCoordinates());
    }
    
    @Test
    public void roundsOfCalculationsTest() {
        lwf.search();
        assertEquals(45, lwf.getRoundsOfCalculations());
    }
    
    @Test
    public void foundRouteTest() {
        assertEquals(1, lwf.init());
    }
    
    @Test
    public void foundRouteTest2() {
        assertEquals(0, lwf2.init());
    }

}
