package com.mygdx.tetris.test;

import com.mygdx.tetris.logic.CorruptedCell;
import com.mygdx.tetris.logic.Direction;
import com.mygdx.tetris.logic.GameModel;
import com.mygdx.tetris.logic.GameStatus;
import com.mygdx.tetris.logic.Piece;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by up201505145 on 16/05/2017.
 */

public class Test_BoardModel {
    @Test
    public void creation() {
        GameModel game = new GameModel(6, 6);
        assertNotEquals(game, null);
        assertEquals(0, game.getCompletedLines());
        assertEquals(GameStatus.ONGOING, game.getStatus());
    }

    @Test
    public void numRowsAndColumns() {
        int numCols = 14, numLines = 42;
        GameModel game = new GameModel(numCols, numLines);
        assertEquals(numCols, game.getMap().getCols());
        assertEquals(numLines, game.getMap().getLines());
    }

    @Test //(timeout=1000)
    public void pieceReachesGround() throws CorruptedCell {
        GameModel game = new GameModel(6, 6);
        int pieceBlocks = game.getCurrentPiece().getBlocks().size();
        while (game.getBlocks().isEmpty()) {
            game.nextCycle(Direction.DOWN);
        }
        assertTrue(game.getBlocks().size() == pieceBlocks);
    }

    @Test //(timeout = 1000)
    public void spawnThreePieces() throws CorruptedCell {
        GameModel game = new GameModel(20, 20);
        Piece firstPiece, secondPiece, thirdPiece;
        int numExpectedBlocks = 0;
        firstPiece = game.getCurrentPiece();
        numExpectedBlocks += firstPiece.getBlocks().size();
        while (game.getBlocks().isEmpty()) {
            game.nextCycle(Direction.DOWN);
        }
        secondPiece = game.getCurrentPiece();
        numExpectedBlocks += secondPiece.getBlocks().size();
        while (game.getBlocks().size() == firstPiece.getBlocks().size()) {
            game.nextCycle(Direction.DOWN);
        }
        thirdPiece = game.getCurrentPiece();
        assertTrue(thirdPiece.getBlocks().size() > 0);
        numExpectedBlocks += thirdPiece.getBlocks().size();
        while (game.getBlocks().size() == firstPiece.getBlocks().size() + secondPiece.getBlocks().size()) {
            game.nextCycle(Direction.DOWN);
        }
        assertEquals(numExpectedBlocks, game.getBlocks().size());
    }
}