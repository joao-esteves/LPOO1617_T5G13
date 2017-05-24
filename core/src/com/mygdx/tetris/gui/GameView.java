package com.mygdx.tetris.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.tetris.TetrisGame;
import com.mygdx.tetris.logic.GameModel;

import static com.badlogic.gdx.graphics.Color.RED;

/**
 * Created by joaof on 12/05/2017.
 */

public class GameView implements Screen {

    private static GameView instance = null;

    private int squareSize;

    private TetrisGame game;
    private GameModel model;

    private OrthographicCamera camera;
    private AssetManager assets;
    private SpriteBatch batch;
    private Sprite[] blockSprites;

    private Stage stage;
    private Table table;
    private TextButton menuButton;
    private BitmapFont font;
    private Skin buttonSkin;
    private TextureAtlas atlas;

    private GameView(TetrisGame tetrisGame, GameModel model) {
        this.game = tetrisGame;
        this.model = model;

        squareSize = Gdx.graphics.getWidth() / game.getColumns();

        assets = new AssetManager();
        assets.load("tetris_images.pack", TextureAtlas.class);
        assets.finishLoading();

        atlas = assets.get("tetris_images.pack");

        batch = new SpriteBatch();
        initSprites(blockSprites, atlas);


        stage = new Stage();
        table = new Table();
        table.setFillParent(true);
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        buttonSkin = new Skin();
  //      atlas = new TextureAtlas(Gdx.files.internal("deprecated.buttons/playButton.pack"));
        buttonSkin.addRegions(atlas);

        setupCamera();
        setupMenuButton();

        stage.addActor(table);
    }

    private void initSprites(Sprite[] sprites, TextureAtlas atlas) {
        sprites = new Sprite[Colors.values().length];

        for (Colors color : Colors.values()) {
            blockSprites[color.val] = new Sprite(atlas.findRegion(color.imgName));
            blockSprites[color.val].setSize(squareSize, squareSize);
        }
    }

    private void setupCamera() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
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

        batch.setProjectionMatrix(camera.combined);

        stage.act(delta);
        stage.draw();

        drawGame();
    }

    private void drawGame() {
        char[][] map = model.getMap().getRepresentation();
        batch.begin();
        for (int column = 0; column < map.length; column++) {
            for (int line = 0; line < map[column].length; line++) {
                //if ()
            }
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    //    camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

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
