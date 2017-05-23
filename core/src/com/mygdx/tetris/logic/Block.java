package com.mygdx.tetris.logic;

import com.badlogic.gdx.math.GridPoint2;

import java.awt.Point;

/**
 * Created by up201505145 on 22/05/2017.
 */
public class Block {
    private GridPoint2 coords;
    private GameMap map;

    public Block(int x, int y, GameMap map) {
        coords = new GridPoint2(x, y);
        this.map = map;
    }

    public Block(Point coords, GameMap map) {
        setCoords(coords);
        this.map = map;
    }

    public void setCoords(Point coords) {
        this.coords = (GridPoint2) coords.clone();
    }

    public GridPoint2 getCoords() {
        return coords;
    }

    public void moveUp() {
        coords.set(coords.x, coords.y + 1);
    }

    public void moveDown() {
        coords.set(coords.x, coords.y - 1);
    }

    public void moveLeft() {
        coords.set(coords.x - 1, coords.y);
    }

    public void moveRight() {
        coords.set(coords.x + 1, coords.y);
    }
}
