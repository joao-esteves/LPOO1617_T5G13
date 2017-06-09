package com.mygdx.tetris.logic;

import com.badlogic.gdx.math.GridPoint2;

import java.util.Arrays;

/**
 * Created by up201505145 on 22/05/2017.
 */
public class StraightPiece extends Piece {
    public StraightPiece(GridPoint2 pos) {
        orientation = Direction.UP;
        symbol = 'I';
        blocks = Arrays.asList(new Block(pos.x, pos.y, symbol), new Block(pos.x, pos.y-1, symbol),
                            new Block(pos.x, pos.y-2, symbol), new Block(pos.x, pos.y-3, symbol));
        axisBlockIndex = 1;
    }

    private void rotate() {
        GridPoint2 axis = blocks.get(1).getCoords();
        switch (orientation) {
            case UP:
            case DOWN:
                setLeftOrientation(axis);
                break;
            case LEFT:
            case RIGHT:
                setUpOrientation(axis);
                break;
        }
    }

    @Override
    protected void getDownOrientation(GridPoint2[] newCoords, GridPoint2 axis) {
        getVerticalOrientation(newCoords, axis);
    }

    @Override
    protected void getUpOrientation(GridPoint2[] newCoords, GridPoint2 axis) {
        getVerticalOrientation(newCoords, axis);
    }

    @Override
    protected void getRightOrientation(GridPoint2[] newCoords, GridPoint2 axis) {
        getHorizontalOrientation(newCoords, axis);
    }

    @Override
    protected void getLeftOrientation(GridPoint2[] newCoords, GridPoint2 axis) {
        getHorizontalOrientation(newCoords, axis);
    }

    private void getVerticalOrientation(GridPoint2[] newCoords, GridPoint2 axis) {
        newCoords[0] = new GridPoint2(axis.x, axis.y + 1);
        newCoords[2] = new GridPoint2(axis.x, axis.y - 1);
        newCoords[3] = new GridPoint2(axis.x, axis.y - 2);
    }

    private void getHorizontalOrientation(GridPoint2[] newCoords, GridPoint2 axis) {
        newCoords[0] = new GridPoint2(axis.x - 1, axis.y);
        newCoords[2] = new GridPoint2(axis.x + 1, axis.y);
        newCoords[3] = new GridPoint2(axis.x + 2, axis.y);
    }


    private void setUpOrientation(GridPoint2 axis) {
        setVerticalOrientation(axis);
        orientation = Direction.UP;
    }

    private void setLeftOrientation(GridPoint2 axis) {
        setHorizontalOrientation(axis);
        orientation = Direction.LEFT;
    }

    private void setVerticalOrientation(GridPoint2 axis) {
        GridPoint2[] newCoords = new GridPoint2[3];
        newCoords[0] = new GridPoint2(axis.x, axis.y + 1);
        newCoords[1] = new GridPoint2(axis.x, axis.y - 1);
        newCoords[2] = new GridPoint2(axis.x, axis.y - 2);

        if (!map.canDrawAt(newCoords)) {
            return;
        }

        blocks.get(0).setCoords(newCoords[0]);
        blocks.get(2).setCoords(newCoords[1]);
        blocks.get(3).setCoords(newCoords[2]);
    }

    private void setHorizontalOrientation(GridPoint2 axis) {
        GridPoint2[] newCoords = new GridPoint2[3];
        newCoords[0] = new GridPoint2(axis.x - 1, axis.y);
        newCoords[1] = new GridPoint2(axis.x + 1, axis.y);
        newCoords[2] = new GridPoint2(axis.x + 2, axis.y);

        if (!map.canDrawAt(newCoords)) {
            return;
        }

        blocks.get(0).setCoords(newCoords[0]);
        blocks.get(2).setCoords(newCoords[1]);
        blocks.get(3).setCoords(newCoords[2]);
    }

    @Override
    public void setPos(GridPoint2 pos) {
        blocks.set(0, new Block(pos.x, pos.y, symbol));
        blocks.set(1, new Block(pos.x, pos.y-1, symbol));
        blocks.set(2, new Block(pos.x, pos.y-2, symbol));
        blocks.set(3, new Block(pos.x, pos.y-3, symbol));
    }


}
