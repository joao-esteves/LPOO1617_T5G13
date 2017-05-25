package com.mygdx.tetris.logic;

import com.badlogic.gdx.math.GridPoint2;

import java.util.Arrays;
import java.util.List;

/**
 * Created by up201505145 on 22/05/2017.
 */
public class StraightPiece extends Piece {
    public StraightPiece(GridPoint2 pos) {
        symbol = 'I';
        blocks = Arrays.asList(new Block(pos.x, pos.y, symbol), new Block(pos.x, pos.y-1, symbol),
                            new Block(pos.x, pos.y-2, symbol), new Block(pos.x, pos.y-3, symbol));
    }

    @Override
    public void setPos(GridPoint2 pos) {
        blocks.set(0, new Block(pos.x, pos.y, symbol));
        blocks.set(1, new Block(pos.x, pos.y-1, symbol));
        blocks.set(2, new Block(pos.x, pos.y-2, symbol));
        blocks.set(3, new Block(pos.x, pos.y-3, symbol));
    }
}
