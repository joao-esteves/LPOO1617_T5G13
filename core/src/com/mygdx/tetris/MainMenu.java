package com.mygdx.tetris;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

/**
 * Created by joaof on 05/05/2017.
 */

class MainMenu extends ScreenAdapter {

    private TetrisGame game;

    private Stage stage;
    private TextButton playButton;
    private BitmapFont font;
    private Skin buttonSkin;
    private TextureAtlas buttonAtlas;


    public MainMenu(TetrisGame tetrisGame) {
        game = tetrisGame;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        buttonSkin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/playButton.pack"));
        buttonSkin.addRegions(buttonAtlas);

        TextButtonStyle playButtonStyle = new TextButtonStyle();
        playButtonStyle.up = buttonSkin.getDrawable("greyButton");
        playButtonStyle.down = buttonSkin.getDrawable("greyDarkButton");
        playButtonStyle.font = font;

        playButton = new TextButton("Play", playButtonStyle);
        playButton.setPosition(100,100);
        stage.addActor(playButton);
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
