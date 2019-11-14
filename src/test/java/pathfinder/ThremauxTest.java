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
    private Thremaux thremaux;

    @Before
    public void setup() {
        MazeAnalyzer analyzer = new MazeAnalyzer(mazeExample);
        thremaux = new Thremaux(mazeExample, analyzer.findStartCoordinates(), analyzer.findGoalCoordinates());
    }

    @Test
    public void chooseRandomRouteTest1() {
        Coordinates current = new Coordinates(1, 3);
        Coordinates previous = new Coordinates(0, 3);
        int randomNr = 1;
        int[] junctionTable = new int[5];
        junctionTable[1] = 0;
        junctionTable[2] = 0;
        junctionTable[3] = 1;
        junctionTable[4] = 1;
        Coordinates next = thremaux.chooseRandomRoute(junctionTable, current, previous, randomNr);
        assertEquals(2, next.getY());
        assertEquals(3, next.getX());
    }
    
    @Test
    public void chooseRandomRouteTest2() {
        Coordinates current = new Coordinates(1, 3);
        Coordinates previous = new Coordinates(0, 3);
        int randomNr = 2;
        int[] junctionTable = new int[5];
        junctionTable[1] = 0;
        junctionTable[2] = 0;
        junctionTable[3] = 1;
        junctionTable[4] = 1;
        Coordinates next = thremaux.chooseRandomRoute(junctionTable, current, previous, randomNr);
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
