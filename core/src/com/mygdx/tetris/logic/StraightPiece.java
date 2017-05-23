package com.mygdx.tetris.logic;

import java.util.Arrays;
import java.util.List;

/**
 * Created by up201505145 on 22/05/2017.
 */
public class StraightPiece extends Piece {
    public StraightPiece() {
        blocks = Arrays.asList(new Block(0,0, map), new Block(0,1, map), new Block(0,2, map), new Block(0,3, map));
    }
}
