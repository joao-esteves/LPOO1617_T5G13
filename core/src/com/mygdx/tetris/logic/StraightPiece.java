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

    @Override
    public void setPos(GridPoint2 pos) {
        blocks.set(0, new Block(pos.x, pos.y, symbol));
        blocks.set(1, new Block(pos.x, pos.y-1, symbol));
        blocks.set(2, new Block(pos.x, pos.y-2, symbol));
        blocks.set(3, new Block(pos.x, pos.y-3, symbol));
    }


}
