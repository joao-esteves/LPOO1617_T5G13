package com.mygdx.tetris.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mygdx.tetris.TetrisGame;
import com.mygdx.tetris.logic.CorruptedCell;
import com.mygdx.tetris.logic.Direction;
import com.mygdx.tetris.logic.GameModel;

import static com.mygdx.tetris.logic.GameStatus.ONGOING;
import static java.lang.Math.min;

/**
 * Created by joaof on 12/05/2017.
 */

public class GameView implements Screen {

    private static GameView instance = null;

    private int squareSize;
    private int widthOffset;
    private int heightOffset;
    private float accumulatedDelta;

    private int score;

    private TetrisGame game;
    private GameModel model;

    private OrthographicCamera camera;
    private AssetManager assets;
    private SpriteBatch batch;
    private Sprite[] blockSprites;

    private Stage stage;
    private Table table;

    private TextButton.TextButtonStyle buttonStyle;
    private TextButton menuButton;
    private TextButton downButton;
    private TextButton leftButton;
    private TextButton rightButton;

    private Label scoreLabel;
    private Label scoreValue;

    private Dialog endGamePopup;

    private BitmapFont font;
    private Skin buttonSkin;
    private TextureAtlas atlas;

    private GameView(TetrisGame tetrisGame, GameModel model) {
        this.game = tetrisGame;
        this.model = model;

        int buttonMargin = 100;
        squareSize = min(Gdx.graphics.getWidth() / game.getColumns(), (Gdx.graphics.getHeight() - buttonMargin) / game.getLines());
        widthOffset = squareSize * game.getColumns() / 2;
        heightOffset = Gdx.graphics.getHeight() / 2;
        accumulatedDelta = 0;

        score = 0;

        assets = new AssetManager();
        assets.load("tetris_images.pack", TextureAtlas.class);
        assets.finishLoading();

        atlas = assets.get("tetris_images.pack");

        batch = new SpriteBatch();
        blockSprites = new Sprite[ColorEnum.values().length];
        initSprites(blockSprites, atlas);

        stage = new Stage();
        table = new Table();
        table.setFillParent(true);
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        buttonSkin = new Skin();
        buttonSkin.addRegions(atlas);

        setupCamera();
        setupButtons();
        setupMovementInput();
        setupScore();
        setupEndGamePopup();

        stage.addActor(table);
    }

    private void setupEndGamePopup() {
        Window.WindowStyle popupStyle = new Window.WindowStyle();
        popupStyle.titleFont = font;
        popupStyle.titleFontColor = new Color(0, 0, 0, 1);
        Skin popupSkin = new Skin(atlas);
        popupSkin.add("popup_style", popupStyle);
        endGamePopup = new Dialog("End Game", popupSkin, "popup_style") {
            @Override
            public void result(Object obj) {
                PopupOptions option = (PopupOptions) obj;
                switch (option) {
                    case RESTART:
                        model.restart();
                        break;
                    case FB_SHARE:

                        break;
                }
            }
        };
        endGamePopup.button("Restart", PopupOptions.RESTART, buttonStyle);
        endGamePopup.button("Share to FB", PopupOptions.FB_SHARE, buttonStyle);
    }

    private void setupScore() {
        Label.LabelStyle scoreLabelStyle = new Label.LabelStyle();
        scoreLabelStyle.font = font;
        scoreLabelStyle.fontColor = new Color(0, 0, 0, 1);
        scoreLabel = new Label("Score: ", scoreLabelStyle);
        scoreValue = new Label("" + score, scoreLabelStyle);

        table.row();
        table.add(scoreLabel);
        table.add(scoreValue);
    }

    private void setupButtons() {
        buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.up = buttonSkin.getDrawable("greyButton");
        buttonStyle.down = buttonSkin.getDrawable("greyDarkButton");
        buttonStyle.font = font;

        setupMenuButton();
        setupMovementButtons();
    }

    // TODO: Keyboard and possibly gyroscope
    private void setupMovementInput() {
    }

    private void setupMovementButtons() {
        leftButton = new TextButton("Left", buttonStyle);
        leftButton.getLabel().setFontScale(2f);
        leftButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                try {
                    model.nextCycle(Direction.LEFT);
                    //model.nextCycle(Direction.ROT_ANTICLOCKWISE);
                } catch (CorruptedCell corruptedCell) {
                    corruptedCell.printStackTrace();
                }
            }
        });

        downButton = new TextButton("Down", buttonStyle);
        downButton.getLabel().setFontScale(2f);
        downButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                try {
                    model.nextCycle(Direction.DOWN);
                } catch (CorruptedCell corruptedCell) {
                    corruptedCell.printStackTrace();
                }
            }
        });

        rightButton = new TextButton("Right", buttonStyle);
        rightButton.getLabel().setFontScale(2f);
        rightButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                try {
                    model.nextCycle(Direction.RIGHT);
                    //model.nextCycle(Direction.ROT_CLOCKWISE);
                } catch (CorruptedCell corruptedCell) {
                    corruptedCell.printStackTrace();
                }
            }
        });

        table.top();
        table.add(leftButton).width(150).height(100);
        table.add(downButton).width(150).height(100);
        table.add(rightButton).width(150).height(100);
    }

    private void initSprites(Sprite[] sprites, TextureAtlas atlas) {
        for (ColorEnum color : ColorEnum.values()) {
            sprites[color.val] = new Sprite(atlas.findRegion(color.imgName));
            sprites[color.val].setSize(squareSize, squareSize);
        }
    }

    private void setupCamera() {
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
    }

    private void setupMenuButton() {
        menuButton = new TextButton("Menu", buttonStyle);
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
        updateLogic(delta);

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        drawGame();
        stage.act(delta);
        if (model.getStatus() != ONGOING && endGamePopup.getStage() == null) {
            endGamePopup.show(stage);
        }
        stage.draw();


    }

    private void updateLogic(float delta) {
        accumulatedDelta += delta;
        /*if (downButton.isPressed()) {
            try {
                model.nextCycle(Direction.DOWN);
            } catch (CorruptedCell corruptedCell) {
                corruptedCell.printStackTrace();
            }
        }*/
        try {
            if (accumulatedDelta >= 1) {
                model.nextCycle(Direction.DOWN);
                score = model.getCompletedLines();
                scoreValue.setText("" + score);
                accumulatedDelta = 0;
            }
        } catch (CorruptedCell corruptedCell) {
            corruptedCell.printStackTrace();
        }
    }

    private void drawGame() {
        char[][] map = model.getMap().getRepresentation();
        batch.begin();
        for (int column = 0; column < map.length; column++) {
            for (int line = 0; line < map[column].length; line++) {
                ColorEnum color = ColorEnum.getColor(map[column][line]);
                if (color == null) {
                    continue;
                }
                blockSprites[color.val].setPosition(column * squareSize - widthOffset, line * squareSize - heightOffset);
                blockSprites[color.val].draw(batch);
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
