package com.mygdx.tetris.logic;

import com.badlogic.gdx.math.GridPoint2;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by up201505145 on 22/05/2017.
 */
public abstract class Piece {
    protected List<Block> blocks;
    protected GameMap map;

    public ArrayList<GridPoint2> getCoords() {
        ArrayList<GridPoint2> coords = new ArrayList<GridPoint2>();
        for (Block block : blocks) {
            coords.add(block.getCoords());
        }
        return coords;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }

    public void move(char direction) {
        switch(direction) {
            case 'W':
                moveUp(direction);
                break;
            case 'S':
                moveDown(direction);
                break;
            case 'A':
                moveLeft(direction);
                break;
            case 'D':
                moveRight(direction);
                break;
        }
    }

    private void moveUp(char direction) {
        for (Block block : blocks) {
            block.moveUp();
        }
    }

    private void moveDown(char direction) {
    for (Block block : blocks) {
            block.moveDown();
        }
    }

    private void moveLeft(char direction) {
        for (Block block : blocks) {
            block.moveLeft();
        }
    }

    private void moveRight(char direction) {
        for (Block block : blocks) {
            block.moveRight();
        }
    }
}