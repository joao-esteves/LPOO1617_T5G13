package com.mygdx.tetris.logic;

import com.badlogic.gdx.math.GridPoint2;
import java.util.ArrayList;

/**
 * Created by joaof on 12/05/2017.
 */

public class GameModel {

    private static GameModel instance = null;
    private GridPoint2 spawnPos;
    private ArrayList<Piece> pieces;
    private GameMap map;

    private GameModel() {
        spawnPos = new GridPoint2(3, 9);
        map = new GameMap(10,10);
    }

    public static GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
        }
        return instance;
    }
}
