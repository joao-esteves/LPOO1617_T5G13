package com.mygdx.tetris.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by up201505145 on 22/05/2017.
 */
public class PieceFactory {
    private static List<Piece> pieces = Arrays.asList(new StraightPiece(), new SquarePiece());

    public static Piece getPiece() {
        Random random = new Random();
        return pieces.get(random.nextInt(pieces.size()));
    }
}
