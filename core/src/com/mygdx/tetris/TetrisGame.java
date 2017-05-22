package com.mygdx.tetris;

import com.badlogic.gdx.Game;
import com.mygdx.tetris.logic.BoardModel;

public class TetrisGame extends Game {

	@Override
	public void create () {
		this.setScreen(MainMenu.getInstance(this));
	}

	public void setBoardScreen() {
		BoardModel model = BoardModel.getInstance();
		this.setScreen(BoardView.getInstance(this, model));
	}

	/*
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
	*/
}
