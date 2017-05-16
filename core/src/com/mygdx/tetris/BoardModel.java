package com.mygdx.tetris;

/**
 * Created by joaof on 12/05/2017.
 */

class BoardModel {

    private static BoardModel instance = null;

    private BoardModel() {

    }

    public static BoardModel getInstance() {
        if (instance == null) {
            instance = new BoardModel();
        }
        return instance;
    }
}
