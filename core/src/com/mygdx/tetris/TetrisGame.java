package com.mygdx.tetris;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.mygdx.tetris.facebook.FB;
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
	private FB facebook;

	@Override
	public void create () {
		facebook = new FB();
		facebook.login();
		this.setScreen(MainMenu.getInstance(this));
	}

	public void setBoardScreen() {
		GameModel model = new GameModel(columns, lines);
		this.setScreen(GameView.getInstance(this, model));
	}

	public void setNewBoardScreen() {
		GameModel model = new GameModel(columns, lines);
		GameView gameView = GameView.getInstance(this, model);
		gameView.getModel().restart();
		this.setScreen(gameView);
	}

	public FB getFacebook() {
		return facebook;
	}

	/*
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
	*/
}
