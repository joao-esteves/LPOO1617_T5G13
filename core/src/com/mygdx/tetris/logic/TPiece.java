package com.mygdx.tetris.logic;

import com.badlogic.gdx.math.GridPoint2;

import java.util.Arrays;

import static com.mygdx.tetris.logic.Direction.UP;

/**
 * Created by up201505145 on 05/06/2017.
 */
public class TPiece extends Piece {
    public TPiece(GridPoint2 pos) {
        orientation = UP;
        symbol = 'T';
        blocks = Arrays.asList(new Block(pos.x+1, pos.y, symbol), new Block(pos.x, pos.y-1, symbol),
                            new Block(pos.x+1, pos.y-1, symbol), new Block(pos.x+2, pos.y-1, symbol));
        axisBlockIndex = 2;
    }

    @Override
    protected void getDownOrientation(GridPoint2[] newCoords, GridPoint2 axis) {
        newCoords[0] = new GridPoint2(axis.x, axis.y-1);
        newCoords[1] = new GridPoint2(axis.x+1, axis.y);
        newCoords[3] = new GridPoint2(axis.x-1, axis.y);
    }

    @Override
    protected void getUpOrientation(GridPoint2[] newCoords, GridPoint2 axis) {
        newCoords[0] = new GridPoint2(axis.x, axis.y+1);
        newCoords[1] = new GridPoint2(axis.x-1, axis.y);
        newCoords[3] = new GridPoint2(axis.x+1, axis.y);
    }

    @Override
    protected void getRightOrientation(GridPoint2[] newCoords, GridPoint2 axis) {
        newCoords[0] = new GridPoint2(axis.x+1, axis.y);
        newCoords[1] = new GridPoint2(axis.x, axis.y+1);
        newCoords[3] = new GridPoint2(axis.x, axis.y-1);
    }

    @Override
    protected void getLeftOrientation(GridPoint2[] newCoords, GridPoint2 axis) {
        newCoords[0] = new GridPoint2(axis.x-1, axis.y);
        newCoords[1] = new GridPoint2(axis.x, axis.y-1);
        newCoords[3] = new GridPoint2(axis.x, axis.y+1);
    }

    @Override
    public void setPos(GridPoint2 pos) {
        blocks.set(0, new Block(pos.x+1, pos.y, symbol));
        blocks.set(1, new Block(pos.x, pos.y-1, symbol));
        blocks.set(2, new Block(pos.x+1, pos.y-1, symbol));
        blocks.set(3, new Block(pos.x+2, pos.y-1, symbol));
    }
}
