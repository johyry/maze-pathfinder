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

    private int[][] mazeExample = {
        {0, 0, 0, 1, 0},
        {0, 1, 1, 1, 0},
        {0, 1, 0, 1, 0},
        {0, 1, 0, 0, 0},
        {0, 1, 0, 0, 0}};
    private int[][] mazeExample2 = {
        {0, 0, 0, 1, 0},
        {0, 1, 1, 1, 0},
        {0, 1, 0, 1, 0},
        {0, 1, 0, 0, 0},
        {0, 0, 0, 0, 0}};
    private int[][] mazeExample1 = {
        {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 1, 1, 1, 0, 1, 1, 1, 0, 0},
        {0, 0, 0, 1, 1, 1, 0, 1, 0, 0},
        {0, 1, 1, 1, 0, 0, 0, 1, 0, 0},
        {0, 1, 0, 0, 0, 1, 1, 1, 1, 0},
        {0, 1, 0, 1, 0, 1, 0, 0, 1, 0},
        {0, 1, 0, 1, 0, 1, 1, 0, 1, 0},
        {0, 1, 1, 1, 0, 1, 1, 0, 1, 0},
        {0, 0, 0, 1, 1, 1, 0, 1, 1, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 1, 0}
    };
    private BreathFirstSearch bfs;
    private BreathFirstSearch bfs1;
    private BreathFirstSearch bfs2;

    @Before
    public void setup() {
        MazeAnalyzer analyzer = new MazeAnalyzer(mazeExample);
        bfs = new BreathFirstSearch(mazeExample, analyzer.findStartCoordinates(), analyzer.findGoalCoordinates());
        MazeAnalyzer analyzer1 = new MazeAnalyzer(mazeExample1);
        bfs1 = new BreathFirstSearch(mazeExample1, analyzer1.findStartCoordinates(), analyzer1.findGoalCoordinates());
        MazeAnalyzer analyzer2 = new MazeAnalyzer(mazeExample2);
        bfs2 = new BreathFirstSearch(mazeExample2, analyzer2.findStartCoordinates(), analyzer2.findGoalCoordinates());
    }

    @Test
    public void searchTest() {
        assertEquals(1, bfs.search());
    }

    @Test
    public void searchTest1() {
        assertEquals(1, bfs1.search());
    }

    @Test
    public void searchTest2() {
        assertEquals(0, bfs2.search());
    }
    @Test
    public void getRouteLengthTest1() {
        bfs.search();
        assertEquals(6, bfs.getRouteLength());
    }

    @Test
    public void getRouteLengthTest2() {
        bfs1.search();
        assertEquals(18, bfs1.getRouteLength());
    }
    

}
