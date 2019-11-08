package pathfinder;

import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import pathfinder.domain.BreathFirstSearch;
import pathfinder.domain.MazeAnalyzer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author johyry
 */
public class BreathFirstSearchTest {

    private int[][] mazeExample = {{0,0,0,1,0}, {0,1,1,1,0}, {0,1,0,1,0}, {0,1,0,0,0}, {0,1,0,0,0}};
    private BreathFirstSearch bfs;
    
    @Before
    public void setup() {
        MazeAnalyzer analyzer = new MazeAnalyzer(mazeExample);
        bfs = new BreathFirstSearch(mazeExample, analyzer.findStartCoordinates(), analyzer.findGoalCoordinates());
    }
    
    
    @Test
    public void searchTest() {
        assertEquals(6, bfs.search());
    }
    
}
