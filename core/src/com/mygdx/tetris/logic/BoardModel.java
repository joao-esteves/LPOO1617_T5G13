package com.mygdx.tetris.logic;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Created by joaof on 12/05/2017.
 */

public class BoardModel {

    private static BoardModel instance = null;
    private static final Point spawnPos = new Point(3, 10);
    private ArrayList<Piece> pieces;

    private BoardModel() {

    }

    public static BoardModel getInstance() {
        if (instance == null) {
            instance = new BoardModel();
        }
        return instance;
    }
}
