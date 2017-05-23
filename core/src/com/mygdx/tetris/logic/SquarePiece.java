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
        blocks = Arrays.asList(new Block(pos.x, pos.y, map), new Block(pos.x+1, pos.y, map),
                            new Block(pos.x, pos.y-1, map), new Block(pos.x+1, pos.y-1, map));
    }
}
