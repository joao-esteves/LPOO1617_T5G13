package com.mygdx.tetris.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.tetris.TetrisGame;

/**
 * Created by joaof on 05/05/2017.
 */

public class MainMenu extends ScreenAdapter {

    private TetrisGame game;

    private Stage stage;
    private Table table;
    private TextButton playButton;
    private BitmapFont font;
    private Skin buttonSkin;
    private TextureAtlas buttonAtlas;

    private static MainMenu instance = null;

    private MainMenu(TetrisGame tetrisGame) {
        game = tetrisGame;
        stage = new Stage();
        table = new Table();
        table.setFillParent(true);
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        buttonSkin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/playButton.pack"));
        buttonSkin.addRegions(buttonAtlas);

        setupPlayButton();

        stage.addActor(table);
    }

    private void setupPlayButton() {
        TextButtonStyle playButtonStyle = new TextButtonStyle();
        playButtonStyle.up = buttonSkin.getDrawable("greyButton");
        playButtonStyle.down = buttonSkin.getDrawable("greyDarkButton");
        playButtonStyle.font = font;

        playButton = new TextButton("Play", playButtonStyle);
        playButton.getLabel().setFontScale(2f);
        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setBoardScreen();
            }
        });

        table.add(playButton).width(150).height(100);
    }

    public static MainMenu getInstance(TetrisGame game) {
        if (instance == null) {
            instance = new MainMenu(game);
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
