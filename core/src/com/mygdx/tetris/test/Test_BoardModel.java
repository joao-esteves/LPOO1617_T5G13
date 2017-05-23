package com.mygdx.tetris.test;

import com.mygdx.tetris.logic.CorruptedCell;
import com.mygdx.tetris.logic.GameModel;

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
        GameModel game = GameModel.getInstance(1, 1);
        assertNotEquals(game, null);
    }

    @Test// (timeout=1000)
    public void pieceReachesGround() throws CorruptedCell {
        GameModel game = GameModel.getInstance(6, 6);
        int pieceBlocks = game.getCurrentPiece().getBlocks().size();
        while (game.getBlocks().isEmpty()) {
            game.nextCycle('S');
        }
        assertTrue(game.getBlocks().size() == pieceBlocks);
    }
}