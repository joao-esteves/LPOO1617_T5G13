package com.mygdx.tetris.logic;

import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.List;

import static com.mygdx.tetris.logic.Direction.DOWN;
import static com.mygdx.tetris.logic.Direction.LEFT;
import static com.mygdx.tetris.logic.Direction.UP;

/**
 * Created by up201505145 on 22/05/2017.
 */
public abstract class Piece {
    protected List<Block> blocks;
    int axisBlockIndex = -1;
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

    /**
     * Assigns a GameMap to the Piece. Used to check if movements are possible.
     * @param map GameMap to assign.
     */
    public void setMap(GameMap map) {
        this.map = map;
    }

    /**
     * Moves the Piece in one of the 4 directions or rotates it in one of the 2 ways.
     * @param direction Wanted movement direction or rotation.
     */
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
        GridPoint2 newCoords[] = new GridPoint2[4];
        newCoords[axisBlockIndex] = axis;
        switch (orientation) {
            case UP:
                getRightOrientation(newCoords, axis);
                orientation = Direction.RIGHT;
                break;
            case DOWN:
                getLeftOrientation(newCoords, axis);
                orientation = LEFT;
                break;
            case LEFT:
                getUpOrientation(newCoords, axis);
                orientation = UP;
                break;
            case RIGHT:
                getDownOrientation(newCoords, axis);
                orientation = DOWN;
                break;
        }
        if (!map.canDrawAt(newCoords)) {
            return;
        }
        applyNewCoords(newCoords);
    }

    protected void rotateAnticlockwise() {
        GridPoint2 axis = blocks.get(axisBlockIndex).getCoords();
        GridPoint2 newCoords[] = new GridPoint2[4];
        newCoords[axisBlockIndex] = axis;
        switch (orientation) {
            case UP:
                getLeftOrientation(newCoords, axis);
                orientation = LEFT;
                break;
            case DOWN:
                getRightOrientation(newCoords, axis);
                orientation = Direction.RIGHT;
                break;
            case LEFT:
                getDownOrientation(newCoords, axis);
                orientation = Direction.DOWN;
                break;
            case RIGHT:
                getUpOrientation(newCoords, axis);
                orientation = UP;
                break;
        }
        if (!map.canDrawAt(newCoords)) {
            return;
        }
        applyNewCoords(newCoords);
    }

    private void applyNewCoords(GridPoint2[] newCoords) {
        for (int i = 0; i < newCoords.length; i++) {
            blocks.get(i).setCoords(newCoords[i]);
        }
    }

    protected abstract void getDownOrientation(GridPoint2[] newCoords, GridPoint2 axis);

    protected abstract void getUpOrientation(GridPoint2[] newCoords, GridPoint2 axis);

    protected abstract void getRightOrientation(GridPoint2[] newCoords, GridPoint2 axis);

    protected abstract void getLeftOrientation(GridPoint2[] newCoords, GridPoint2 axis);

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