package com.mygdx.tetris.logic;

import com.badlogic.gdx.math.GridPoint2;

import java.util.Arrays;
import java.util.List;

/**
 * Created by up201505145 on 22/05/2017.
 */
public class StraightPiece extends Piece {
    public StraightPiece(GridPoint2 pos) {
        orientation = Direction.UP;
        symbol = 'I';
        blocks = Arrays.asList(new Block(pos.x, pos.y, symbol), new Block(pos.x, pos.y-1, symbol),
                            new Block(pos.x, pos.y-2, symbol), new Block(pos.x, pos.y-3, symbol));
    }

    @Override
    protected void rotateClockwise() {
        rotate();
    }

    @Override
    protected void rotateAnticlockwise() {
        rotate();
    }

    private void rotate() {
        GridPoint2 axis = blocks.get(1).getCoords();
        switch (orientation) {
            case UP:
            case DOWN:
                orientation = Direction.LEFT;
                blocks.get(0).setCoords(axis.x - 1, axis.y);
                blocks.get(2).setCoords(axis.x + 1, axis.y);
                blocks.get(3).setCoords(axis.x + 2, axis.y);
                break;
            case LEFT:
            case RIGHT:
                orientation = Direction.UP;
                blocks.get(0).setCoords(axis.x, axis.y + 1);
                blocks.get(2).setCoords(axis.x, axis.y - 1);
                blocks.get(3).setCoords(axis.x, axis.y - 2);
                break;
        }
    }

    @Override
    public void setPos(GridPoint2 pos) {
        blocks.set(0, new Block(pos.x, pos.y, symbol));
        blocks.set(1, new Block(pos.x, pos.y-1, symbol));
        blocks.set(2, new Block(pos.x, pos.y-2, symbol));
        blocks.set(3, new Block(pos.x, pos.y-3, symbol));
    }
}
