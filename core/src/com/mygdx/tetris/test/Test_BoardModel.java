package com.mygdx.tetris.test;

import com.mygdx.tetris.logic.GameModel;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by up201505145 on 16/05/2017.
 */

public class Test_BoardModel {
    @Test
    public void creation() {
        GameModel game = GameModel.getInstance();
        assertNotEquals(game, null);
    }

    @Test
    public void spawnPiece() {
        GameModel game = GameModel.getInstance();

    }
}