package com.mygdx.tetris;

import com.badlogic.gdx.ScreenAdapter;

/**
 * Created by joaof on 12/05/2017.
 */

class BoardView extends ScreenAdapter {

    private TetrisGame game;
    private BoardModel model;

    public BoardView(TetrisGame tetrisGame, BoardModel model) {
        this.game = tetrisGame;
        this.model = model;
    }

    @Override
    public void render(float delta) {

    }
}
