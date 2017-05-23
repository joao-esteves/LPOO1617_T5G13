package com.mygdx.tetris.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by up201505145 on 22/05/2017.
 */
public class PieceFactory {
    private static final List<Piece> pieces = Arrays.asList(new StraightPiece(), new SquarePiece());
    private static final Random random = new Random();

    public static Piece getPiece(GameMap map) {
        Piece piece = pieces.get(random.nextInt(pieces.size()));
        piece.setMap(map);
        return piece;
    }
}
