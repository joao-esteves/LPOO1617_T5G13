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
        blocks = Arrays.asList(new Block(pos.x, pos.y, map, symbol), new Block(pos.x, pos.y-1, map, symbol),
                            new Block(pos.x, pos.y-2, map, symbol), new Block(pos.x, pos.y-3, map, symbol));
    }

    @Override
    public void setPos(GridPoint2 pos) {
        blocks.get(0).setCoords(pos.x, pos.y);
        blocks.get(1).setCoords(pos.x, pos.y-1);
        blocks.get(2).setCoords(pos.x, pos.y-2);
        blocks.get(3).setCoords(pos.x, pos.y-3);
    }
}
