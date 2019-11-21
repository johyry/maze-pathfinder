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

    @Before
    public void setup() {
        MazeAnalyzer analyzer = new MazeAnalyzer(mazeExample1);
        lwf = new LeftWallFollower(mazeExample1, analyzer.findStartCoordinates(), analyzer.findGoalCoordinates());
    }
    
    @Test
    public void searchTest() {
        assertEquals(45, lwf.search());
    }

}
