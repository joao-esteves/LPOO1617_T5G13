package com.mygdx.tetris;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.tetris.logic.BoardModel;

/**
 * Created by joaof on 12/05/2017.
 */

class BoardView extends ScreenAdapter {

    private static BoardView instance = null;

    private TetrisGame game;
    private BoardModel model;

    private Stage stage;
    private TextButton menuButton;
    private BitmapFont font;
    private Skin buttonSkin;
    private TextureAtlas buttonAtlas;

    private BoardView(TetrisGame tetrisGame, BoardModel model) {
        this.game = tetrisGame;
        this.model = model;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        buttonSkin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/playButton.pack"));
        buttonSkin.addRegions(buttonAtlas);

        TextButton.TextButtonStyle playButtonStyle = new TextButton.TextButtonStyle();
        playButtonStyle.up = buttonSkin.getDrawable("greyButton");
        playButtonStyle.down = buttonSkin.getDrawable("greyDarkButton");
        playButtonStyle.font = font;

        menuButton = new TextButton("Menu", playButtonStyle);
        menuButton.setPosition(100,100);
        menuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(MainMenu.getInstance(game));
            }
        });
        stage.addActor(menuButton);
    }

    public static BoardView getInstance(TetrisGame game, BoardModel model) {
        if (instance == null) {
            instance = new BoardView(game, model);
        }
        return instance;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        buttonSkin.dispose();
        font.dispose();
        buttonSkin.dispose();
        buttonAtlas.dispose();
    }
}
