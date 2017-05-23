package com.mygdx.tetris.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.tetris.TetrisGame;
import com.mygdx.tetris.logic.GameModel;

/**
 * Created by joaof on 12/05/2017.
 */

public class GameView extends ScreenAdapter {

    private static GameView instance = null;

    private TetrisGame game;
    private GameModel model;

    private Stage stage;
    private Table table;
    private TextButton menuButton;
    private BitmapFont font;
    private Skin buttonSkin;
    private TextureAtlas buttonAtlas;

    private GameView(TetrisGame tetrisGame, GameModel model) {
        this.game = tetrisGame;
        this.model = model;

        stage = new Stage();
        table = new Table();
        table.setFillParent(true);
      //  table.right();
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        buttonSkin = new Skin();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/playButton.pack"));
        buttonSkin.addRegions(buttonAtlas);

        setupGameViewport();
        setupMenuButton();

        stage.addActor(table);
    }

    private void setupGameViewport() {
        OrthographicCamera camera = new OrthographicCamera();
    //    camera.setToOrtho(false, Gdx.graphics.);
    }

    private void setupMenuButton() {
        TextButton.TextButtonStyle menuButtonStyle = new TextButton.TextButtonStyle();
        menuButtonStyle.up = buttonSkin.getDrawable("greyButton");
        menuButtonStyle.down = buttonSkin.getDrawable("greyDarkButton");
        menuButtonStyle.font = font;

        menuButton = new TextButton("Menu", menuButtonStyle);
        menuButton.getLabel().setFontScale(2f);
        menuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(MainMenu.getInstance(game));
            }
        });

        table.top();
        table.add(menuButton).width(150).height(100);
    }

    public static GameView getInstance(TetrisGame game, GameModel model) {
        if (instance == null) {
            instance = new GameView(game, model);
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
