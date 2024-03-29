package com.mygdx.tetris.logic;

import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by up201505145 on 22/05/2017.
 */
public class PieceFactory {
    private final List<Piece> pieces;
    private final Random random;
    private GridPoint2 spawnPos;

    /**
     * Initializes the Piece factory.
     * @param spawnPos Position where the pieces will spawn.
     */
    public PieceFactory(GridPoint2 spawnPos) {
        this.spawnPos = spawnPos;
        pieces = Arrays.asList(new StraightPiece(spawnPos), new SquarePiece(spawnPos), new TPiece(spawnPos),
                new SPiece(spawnPos), new ZPiece(spawnPos), new JPiece(spawnPos), new LPiece(spawnPos));
        random = new Random();
    }

    /**
     * Returns a Piece at the spawn position. Pieces are reused.
     * @param map GameMap being used.
     * @return New Piece.
     */
    public Piece makePiece(GameMap map) {
        Piece piece = pieces.get(random.nextInt(pieces.size()));
        piece.setPos(spawnPos);
        piece.setMap(map);
        return piece;
    }
}
