package com.mygdx.tetris;

import com.badlogic.gdx.Game;
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
//	private final String fbAppId = "1333570703393561";
//	private final String fbRedirectUri = "https://www.facebook.com/connect/login_success.html";
//	private final String fbAppSecret = "a86d5fd7a1503aefc9656dc204ec60f2";
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

//	public String getFbAppId() {
//		return fbAppId;
//	}
//
//	public String getFbRedirectUri() {
//		return fbRedirectUri;
//	}
//
//	public String getFbAppSecret() {
//		return fbAppSecret;
//	}

//	public void shareScore(int score) {
//		facebook.login();
//	//	facebook.shareScore(score);
//	}

	/*
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
	*/
}
