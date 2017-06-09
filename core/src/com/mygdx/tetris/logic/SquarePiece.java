package com.mygdx.tetris.logic;

import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by up201505145 on 22/05/2017.
 */
public class SquarePiece extends Piece {
    public SquarePiece(GridPoint2 pos) {
        orientation = Direction.UP;
        symbol = 'O';
        blocks = Arrays.asList(new Block(pos.x, pos.y, symbol), new Block(pos.x+1, pos.y, symbol),
                            new Block(pos.x, pos.y-1, symbol), new Block(pos.x+1, pos.y-1, symbol));
    }

    @Override
    protected void setDownOrientation(GridPoint2 axis) {

    }

    @Override
    protected void setUpOrientation(GridPoint2 axis) {

    }

    @Override
    protected void setLeftOrientation(GridPoint2 axis) {

    }

    @Override
    protected void setRightOrientation(GridPoint2 axis) {

    }

    @Override
    public void setPos(GridPoint2 pos) {
        blocks.set(0, new Block(pos.x, pos.y, symbol));
        blocks.set(1, new Block(pos.x+1, pos.y, symbol));
        blocks.set(2, new Block(pos.x, pos.y-1, symbol));
        blocks.set(3, new Block(pos.x+1, pos.y-1, symbol));
    }
}
