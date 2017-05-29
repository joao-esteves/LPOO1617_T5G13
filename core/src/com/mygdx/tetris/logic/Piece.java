package com.mygdx.tetris.logic;

import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by up201505145 on 22/05/2017.
 */
public abstract class Piece {
    protected List<Block> blocks;
    protected GameMap map;
    protected char symbol;

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

    public void move(Direction direction) {
        switch(direction) {
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
        }
    }

    private void moveDown() {
    for (Block block : blocks) {
            block.moveDown();
        }
    }

    private void moveLeft() {
        for (Block block : blocks) {
            if (block.getCoords().x == 0 || map.isOccupied(block.getCoords().x - 1, block.getCoords().y)){
                return;
            }
        }
        for (Block block : blocks) {
            block.moveLeft();
        }
    }

    private void moveRight() {
        for (Block block : blocks) {
            if (block.getCoords().x == map.getCols() - 1 || map.isOccupied(block.getCoords().x + 1, block.getCoords().y)) {
                return;
            }
        }
        for (Block block : blocks) {
            block.moveRight();
        }
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public char getSymbol() {
        return symbol;
    }

    public abstract void setPos(GridPoint2 pos);
}