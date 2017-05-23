package com.mygdx.tetris;

import com.badlogic.gdx.Game;
import com.mygdx.tetris.gui.GameView;
import com.mygdx.tetris.gui.MainMenu;
import com.mygdx.tetris.logic.GameModel;

public class TetrisGame extends Game {

	private int columns, lines;

	@Override
	public void create () {
		this.setScreen(MainMenu.getInstance(this));
	}

	public void setBoardScreen() {
		GameModel model = GameModel.getInstance(columns, lines);
		this.setScreen(GameView.getInstance(this, model));
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
