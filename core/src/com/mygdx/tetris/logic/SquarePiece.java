package com.mygdx.tetris.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by up201505145 on 22/05/2017.
 */
public class SquarePiece extends Piece {
    public SquarePiece() {
        blocks = Arrays.asList(new Block(0,0, map), new Block(1,0, map), new Block(0,1, map), new Block(1,1, map));
    }
}
