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

    public Block(GridPoint2 coords, GameMap map) {
        setCoords(coords);
        this.map = map;
    }

    public void setCoords(GridPoint2 coords) {
        this.coords = coords;
    }

    public GridPoint2 getCoords() {
        return new GridPoint2(coords);
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

    public Object clone() {
        return new Block(coords, map);
    }
}
