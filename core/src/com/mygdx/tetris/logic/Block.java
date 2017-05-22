package com.mygdx.tetris.logic;

import java.awt.Point;

/**
 * Created by up201505145 on 22/05/2017.
 */
public class Block {
    private Point coords;

    public Block(int x, int y) {
        coords = new Point(x, y);
    }

    public Block(Point coords) {
        setCoords(coords);
    }

    public void setCoords(Point coords) {
        this.coords = (Point) coords.clone();
    }

    public Point getCoords() {
        return coords;
    }

    public void moveUp() {
        coords.move(coords.x, coords.y + 1);
    }

    public void moveDown() {
        coords.move(coords.x, coords.y - 1);
    }

    public void moveLeft() {
        coords.move(coords.x - 1, coords.y);
    }

    public void moveRight() {
        coords.move(coords.x + 1, coords.y);
    }
}
