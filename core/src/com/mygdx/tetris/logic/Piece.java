package com.mygdx.tetris.logic;

import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by up201505145 on 22/05/2017.
 */
public abstract class Piece {
    protected List<Block> blocks;
    int axisBlockIndex;
    protected GameMap map;
    protected char symbol;
    protected Direction orientation;

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
            case ROT_ANTICLOCKWISE:
                rotateAnticlockwise();
                break;
            case ROT_CLOCKWISE:
                rotateClockwise();
                break;
        }
    }

    protected void rotateClockwise() {
        GridPoint2 axis = blocks.get(axisBlockIndex).getCoords();
        switch (orientation) {
            case UP:
                setRightOrientation(axis);
                break;
            case DOWN:
                setLeftOrientation(axis);
                break;
            case LEFT:
                setUpOrientation(axis);
                break;
            case RIGHT:
                setDownOrientation(axis);
                break;
        }
    }

    protected abstract void setDownOrientation(GridPoint2 axis);

    protected abstract void setUpOrientation(GridPoint2 axis);

    protected abstract void setLeftOrientation(GridPoint2 axis);

    protected abstract void setRightOrientation(GridPoint2 axis);

    protected void rotateAnticlockwise() {
        GridPoint2 axis = blocks.get(axisBlockIndex).getCoords();
        switch (orientation) {
            case UP:
                setLeftOrientation(axis);
                break;
            case DOWN:
                setRightOrientation(axis);
                break;
            case LEFT:
                setDownOrientation(axis);
                break;
            case RIGHT:
                setUpOrientation(axis);
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