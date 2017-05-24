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
}
