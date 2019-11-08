/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder;

import org.junit.Before;
import org.junit.Test;
import pathfinder.domain.MazeAnalyzer;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author johyry
 */
public class MazeAnalyzerTest {

    private int[][] mazeExample = {{0, 0, 0, 1, 0}, {0, 1, 1, 1, 0}, {0, 1, 0, 1, 0}, {0, 1, 0, 0, 0}, {0, 1, 0, 0, 0}};
    private MazeAnalyzer analyzer;

    @Before
    public void setup() {
        analyzer = new MazeAnalyzer(mazeExample);
    }

    @Test
    public void findStartCoordinatesTest() {
        assertEquals(3, analyzer.findStartCoordinates().getX());
        assertEquals(0, analyzer.findStartCoordinates().getY());

    }

    @Test
    public void findGoalCoordinatesTest() {
        assertEquals(1, analyzer.findGoalCoordinates().getX());
        assertEquals(mazeExample.length-1, analyzer.findGoalCoordinates().getY());
    }

}
