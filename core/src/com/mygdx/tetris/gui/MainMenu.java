package com.mygdx.tetris.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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

    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 70;
    private TetrisGame game;

    private Stage stage;
    private Table table;
    private TextButton playButton;
    private BitmapFont font;
    private Skin buttonSkin;
    private TextureAtlas atlas;

    private static MainMenu instance = null;

    private MainMenu(TetrisGame tetrisGame) {
        game = tetrisGame;
        stage = new Stage();
        table = new Table();
        table.setFillParent(true);
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        buttonSkin = new Skin();
        atlas = new TextureAtlas(Gdx.files.internal("tetris_images.pack"));
        buttonSkin.addRegions(atlas);

        setupButtons();

        stage.addActor(table);
    }

    private void setupButtons() {
        setupPlayButton();
        addBlankColumn();
        setupNewGameButton();
        addBlankColumn();
        setupFBLoginButton();
    }

    private void addBlankColumn() {
        table.add(new Image()).height(40);
        table.row();
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

        table.add(playButton).width(BUTTON_WIDTH).height(BUTTON_HEIGHT);
        table.row();
    }

    private void setupNewGameButton() {
        TextButtonStyle newButtonStyle = new TextButtonStyle();
        newButtonStyle.up = buttonSkin.getDrawable("greyButton");
        newButtonStyle.down = buttonSkin.getDrawable("greyDarkButton");
        newButtonStyle.font = font;

        TextButton newGameButton = new TextButton("New Game", newButtonStyle);
        newGameButton.getLabel().setFontScale(2f);
        newGameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setNewBoardScreen();
            }
        });

        table.add(newGameButton).width(BUTTON_WIDTH).height(BUTTON_HEIGHT);
        table.row();
    }

    private void setupFBLoginButton() {
        TextButtonStyle fbLoginButtonStyle = new TextButtonStyle();
        fbLoginButtonStyle.up = buttonSkin.getDrawable("greyButton");
        fbLoginButtonStyle.down = buttonSkin.getDrawable("greyDarkButton");
        fbLoginButtonStyle.font = font;

        TextButton fbLoginButton = new TextButton("Login with FB", fbLoginButtonStyle);
        fbLoginButton.getLabel().setFontScale(2f);
        fbLoginButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.getFacebook().login();
            }
        });

        table.add(fbLoginButton).width(BUTTON_WIDTH).height(BUTTON_HEIGHT);
        table.row();
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
        atlas.dispose();
    }
}
