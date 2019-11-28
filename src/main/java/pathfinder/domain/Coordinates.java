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
public class Coordinates {

    private int x;
    private int y;

    public Coordinates(int y, int x) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "y: " + y + ", x: " + x;
    }

    public boolean equals(Coordinates c) {
        return x == c.getX() && y == c.getY();
    }

}
