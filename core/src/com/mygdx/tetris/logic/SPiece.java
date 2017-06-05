package com.mygdx.tetris.logic;

import com.badlogic.gdx.math.GridPoint2;

import java.util.Arrays;

/**
 * Created by up201505145 on 05/06/2017.
 */
public class SPiece extends Piece {
    public SPiece(GridPoint2 pos) {
        orientation = Direction.UP;
        symbol = 'S';
        blocks = Arrays.asList(new Block(pos.x+1, pos.y, symbol), new Block(pos.x+2, pos.y, symbol),
                            new Block(pos.x, pos.y-1, symbol), new Block(pos.x+1, pos.y-1, symbol));
    }

    @Override
    protected void rotateClockwise() {

    }

    @Override
    protected void rotateAnticlockwise() {

    }

    @Override
    public void setPos(GridPoint2 pos) {
        blocks.set(0, new Block(pos.x+1, pos.y, symbol));
        blocks.set(1, new Block(pos.x+2, pos.y, symbol));
        blocks.set(2, new Block(pos.x, pos.y-1, symbol));
        blocks.set(3, new Block(pos.x+1, pos.y-1, symbol));
    }
}
