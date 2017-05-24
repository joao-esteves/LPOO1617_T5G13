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
        symbol = 'O';
        blocks = Arrays.asList(new Block(pos.x, pos.y, map, symbol), new Block(pos.x+1, pos.y, map, symbol),
                            new Block(pos.x, pos.y-1, map, symbol), new Block(pos.x+1, pos.y-1, map, symbol));
    }

    @Override
    public void setPos(GridPoint2 pos) {
        blocks.get(0).setCoords(pos.x, pos.y);
        blocks.get(1).setCoords(pos.x+1, pos.y);
        blocks.get(2).setCoords(pos.x, pos.y-1);
        blocks.get(3).setCoords(pos.x+1, pos.y-1);
    }
}
