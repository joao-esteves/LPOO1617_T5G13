package com.mygdx.tetris;

import com.badlogic.gdx.Game;
import com.mygdx.tetris.gui.GameView;
import com.mygdx.tetris.gui.MainMenu;
import com.mygdx.tetris.logic.GameModel;

public class TetrisGame extends Game {

	public int getColumns() {
		return columns;
	}

	public int getLines() {
		return lines;
	}

	private int columns = 10, lines = 20;

	@Override
	public void create () {
		this.setScreen(MainMenu.getInstance(this));
	}

	public void setBoardScreen() {
		GameModel model = new GameModel(columns, lines);
		this.setScreen(GameView.getInstance(this, model));
	}

	/*
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
	*/
}
