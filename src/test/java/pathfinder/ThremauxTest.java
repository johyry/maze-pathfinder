/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import pathfinder.domain.Coordinates;
import pathfinder.domain.MazeAnalyzer;
import pathfinder.domain.Thremaux;

/**
 *
 * @author johyry
 */
public class ThremauxTest {

    private int[][] mazeExample = {
        {0, 0, 0, 1, 0},
        {0, 1, 1, 1, 0},
        {0, 1, 0, 1, 0},
        {0, 1, 1, 0, 0},
        {0, 1, 0, 0, 0}};
    private int[][] mazeExample1 = {
        {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
        {0, 1, 1, 1, 0, 1, 1, 1, 0, 0},
        {0, 0, 0, 1, 1, 1, 0, 1, 0, 0},
        {0, 1, 1, 1, 0, 0, 0, 1, 0, 0},
        {0, 0, 0, 1, 0, 1, 1, 1, 1, 0},
        {0, 1, 1, 1, 0, 1, 0, 0, 1, 0},
        {0, 0, 0, 1, 0, 1, 1, 0, 1, 0},
        {0, 1, 1, 1, 0, 0, 1, 0, 0, 0},
        {0, 0, 0, 1, 0, 1, 1, 1, 1, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 1, 0}
    };
    private Thremaux thremaux;
    private Thremaux thremaux1;

    @Before
    public void setup() {
        MazeAnalyzer analyzer = new MazeAnalyzer(mazeExample);
        thremaux = new Thremaux(mazeExample, analyzer.findStartCoordinates(), analyzer.findGoalCoordinates());
        MazeAnalyzer analyzer1 = new MazeAnalyzer(mazeExample1);
        thremaux1 = new Thremaux(mazeExample1, analyzer1.findStartCoordinates(), analyzer1.findGoalCoordinates());
    }
    
    @Test
    public void searchTest1() {
        assertEquals(1, thremaux.search());
    }
    
//    @Test
//    public void searchTest2() {
//        assertEquals(1, thremaux1.search());
//    }
    
    @Test
    public void moveForwardTest1() {
        Coordinates current = new Coordinates(2, 3);
        Coordinates previous = new Coordinates(1, 3);
        int[] junctionTable = new int[5];
        junctionTable[1] = 0;
        junctionTable[2] = 0;
        junctionTable[3] = 0;
        junctionTable[4] = 0;
        Coordinates next = thremaux.moveForward(junctionTable, current, previous);
        assertEquals(1, next.getY());
        assertEquals(3, next.getX());
    }
    
    @Test
    public void moveForwardTest2() {
        Coordinates current = new Coordinates(1, 1);
        Coordinates previous = new Coordinates(1, 2);
        int[] junctionTable = new int[5];
        junctionTable[1] = 0;
        junctionTable[2] = 0;
        junctionTable[3] = 1;
        junctionTable[4] = 0;
        Coordinates next = thremaux.moveForward(junctionTable, current, previous);
        assertEquals(2, next.getY());
        assertEquals(1, next.getX());
    }
    
    @Test
    public void moveForwardTest3() {
        Coordinates current = new Coordinates(1, 2);
        Coordinates previous = new Coordinates(1, 3);
        int[] junctionTable = new int[5];
        junctionTable[1] = 0;
        junctionTable[2] = 0;
        junctionTable[3] = 0;
        junctionTable[4] = 1;
        Coordinates next = thremaux.moveForward(junctionTable, current, previous);
        assertEquals(1, next.getY());
        assertEquals(1, next.getX());
    }
    
    @Test
    public void moveForwardTest4() {
        Coordinates current = new Coordinates(1, 1);
        Coordinates previous = new Coordinates(2, 1);
        int[] junctionTable = new int[5];
        junctionTable[1] = 0;
        junctionTable[2] = 1;
        junctionTable[3] = 0;
        junctionTable[4] = 0;
        Coordinates next = thremaux.moveForward(junctionTable, current, previous);
        assertEquals(1, next.getY());
        assertEquals(2, next.getX());
    }
    

    @Test
    public void chooseOptionWithLowestMarksTest1() {
        Coordinates current = new Coordinates(1, 3);
        int[] junctionTable = new int[5];
        junctionTable[1] = 1;
        junctionTable[2] = 0;
        junctionTable[3] = 1;
        junctionTable[4] = 1;
        thremaux.marked[0][3] = 1;
        thremaux.marked[2][3] = 2;
        thremaux.marked[1][2] = 2;

        Coordinates next = thremaux.chooseOptionWithLowestMarks(junctionTable, current);
        assertEquals(0, next.getY());
        assertEquals(3, next.getX());
    }

    @Test
    public void chooseOptionWithLowestMarksTest2() {
        Coordinates current = new Coordinates(1, 3);
        int[] junctionTable = new int[5];
        junctionTable[1] = 1;
        junctionTable[2] = 0;
        junctionTable[3] = 1;
        junctionTable[4] = 1;
        thremaux.marked[0][3] = 2;
        thremaux.marked[2][3] = 2;
        thremaux.marked[1][2] = 1;

        Coordinates next = thremaux.chooseOptionWithLowestMarks(junctionTable, current);
        assertEquals(1, next.getY());
        assertEquals(2, next.getX());
    }
    
//    @Test
//    public void chooseOptionWithLowestMarksTest3() {
//        Coordinates current = new Coordinates(1, 3);
//        int[] junctionTable = new int[5];
//        junctionTable[1] = 1;
//        junctionTable[2] = 0;
//        junctionTable[3] = 1;
//        junctionTable[4] = 1;
//        thremaux.marked[0][3] = 1;
//        thremaux.marked[2][3] = 1;
//        thremaux.marked[1][2] = 1;
//
//        Coordinates next = thremaux.chooseOptionWithLowestMarks(junctionTable, current);
//        assertEquals(1, next.getY());
//        assertEquals(2, next.getX());
//    }

    @Test
    public void chooseRandomRouteTest1() {
        Coordinates current = new Coordinates(1, 3);
        int randomNr = 0;
        int[] junctionTable = new int[5];
        junctionTable[1] = 0;
        junctionTable[2] = 0;
        junctionTable[3] = 1;
        junctionTable[4] = 1;
        Coordinates next = thremaux.chooseRandomRoute(junctionTable, current, randomNr);
        assertEquals(2, next.getY());
        assertEquals(3, next.getX());
    }

    @Test
    public void chooseRandomRouteTest2() {
        Coordinates current = new Coordinates(1, 3);
        Coordinates previous = new Coordinates(0, 3);
        int randomNr = 1;
        int[] junctionTable = new int[5];
        junctionTable[1] = 0;
        junctionTable[2] = 0;
        junctionTable[3] = 1;
        junctionTable[4] = 1;
        Coordinates next = thremaux.chooseRandomRoute(junctionTable, current, randomNr);
        assertEquals(1, next.getY());
        assertEquals(2, next.getX());
    }

    @Test
    public void otherOptionsThanPreviousMarkedTest1() {
        assertEquals(false, thremaux.otherOptionsThanPreviousMarked(new Coordinates(1, 3), new Coordinates(1, 3)));
    }

    @Test
    public void otherOptionsThanPreviousMarkedTest2() {
        thremaux.marked[0][3] = 1;
        assertEquals(false, thremaux.otherOptionsThanPreviousMarked(new Coordinates(1, 3), new Coordinates(0, 3)));
    }

    @Test
    public void otherOptionsThanPreviousMarkedTest3() {
        thremaux.marked[0][3] = 1;
        thremaux.marked[2][3] = 1;
        assertEquals(true, thremaux.otherOptionsThanPreviousMarked(new Coordinates(1, 3), new Coordinates(0, 3)));
    }

    @Test
    public void howManyRouteOptionsTest1() {
        int[] risteyksia = thremaux.howManyRouteOptions(new Coordinates(1, 3), new Coordinates(0, 3));
        assertEquals(2, risteyksia[0]);
    }

    @Test
    public void howManyRouteOptionsTest2() {
        int risteyksia[] = thremaux.howManyRouteOptions(new Coordinates(0, 3), new Coordinates(0, 3));
        assertEquals(1, risteyksia[0]);
    }

    @Test
    public void howManyRouteOptionsTest3() {
        int risteyksia[] = thremaux.howManyRouteOptions(new Coordinates(3, 1), new Coordinates(3, 1));
        assertEquals(3, risteyksia[0]);
    }

    @Test
    public void howManyRouteOptionsTest4() {
        int risteyksia[] = thremaux.howManyRouteOptions(new Coordinates(3, 1), new Coordinates(3, 1));
        assertEquals(1, risteyksia[1]);
        assertEquals(1, risteyksia[2]);
        assertEquals(1, risteyksia[3]);
        assertEquals(0, risteyksia[4]);

    }

}
