/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import pathfinder.datastructures.Queue;
import pathfinder.domain.Coordinates;

/**
 *
 * @author johyry
 */
public class QueueTest {

    private Queue jono;

    @Before
    public void setup() {
        jono = new Queue();
    }
    
    @Test
    public void pollReturnsNullWhenQueueIsEmptyTest() {
        Queue jono1 = new Queue(3);
        
        assertEquals(null, jono1.poll());
    }
    
    @Test
    public void queueSizeTest() {
        Queue jono1 = new Queue(3);

        Coordinates xy1 = new Coordinates(0, 0);
        Coordinates xy2 = new Coordinates(1, 0);
        Coordinates xy3 = new Coordinates(2, 0);
        Coordinates xy4 = new Coordinates(3, 0);

        jono1.add(xy1);
        jono1.add(xy2);
        jono1.add(xy3);
        jono1.add(xy4);

        assertEquals(4, jono1.getSize());
    }

    @Test
    public void tableSizeCoordinationTest1() {
        Queue jono1 = new Queue(3);

        Coordinates xy1 = new Coordinates(0, 0);
        Coordinates xy2 = new Coordinates(1, 0);
        Coordinates xy3 = new Coordinates(2, 0);
        Coordinates xy4 = new Coordinates(3, 0);

        jono1.add(xy1);
        jono1.add(xy2);
        jono1.add(xy3);
        jono1.add(xy4);

        assertEquals(6, jono1.getTableSize());
    }

    @Test
    public void tableSizeCoordinationTest2() {
        Queue jono1 = new Queue(4);

        Coordinates xy1 = new Coordinates(0, 0);
        Coordinates xy2 = new Coordinates(1, 0);
        Coordinates xy3 = new Coordinates(2, 0);
        Coordinates xy4 = new Coordinates(3, 0);
        Coordinates xy5 = new Coordinates(3, 0);

        jono1.add(xy1);
        jono1.add(xy2);
        jono1.add(xy3);
        jono1.add(xy4);
        
        jono1.poll();
        jono1.poll();
        
        jono1.add(xy5);

        assertEquals(4, jono1.getTableSize());
    }
    
    @Test
    public void tableSizeCoordinationTest3() {
        Queue jono1 = new Queue(4);

        Coordinates xy1 = new Coordinates(0, 0);
        Coordinates xy2 = new Coordinates(1, 0);
        Coordinates xy3 = new Coordinates(2, 0);
        Coordinates xy4 = new Coordinates(3, 0);
        Coordinates xy5 = new Coordinates(3, 0);

        jono1.add(xy1);
        jono1.add(xy2);
        jono1.add(xy3);
        jono1.add(xy4);
        
        jono1.poll();
//        jono1.poll();
        
        jono1.add(xy5);

        assertEquals(8, jono1.getTableSize());
    }

    @Test
    public void addAndPollTest1() {
        Coordinates xy = new Coordinates(0, 0);

        assertEquals(true, jono.add(xy));
        assertEquals(xy, jono.poll());

    }

    @Test
    public void addAndPollTest2() {
        Coordinates xy1 = new Coordinates(0, 0);
        Coordinates xy2 = new Coordinates(1, 0);
        Coordinates xy3 = new Coordinates(2, 0);
        Coordinates xy4 = new Coordinates(3, 0);

        jono.add(xy1);
        jono.add(xy2);
        jono.add(xy3);
        jono.add(xy4);

        assertEquals(xy1, jono.poll());
        assertEquals(xy2, jono.poll());
    }

    @Test
    public void isEmptyTest1() {
        assertEquals(true, jono.isEmpty());
    }

    @Test
    public void isEmptyTest2() {
        Coordinates xy = new Coordinates(0, 0);
        jono.add(xy);
        assertEquals(false, jono.isEmpty());
    }
}
