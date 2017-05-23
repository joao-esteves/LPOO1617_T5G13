package com.mygdx.tetris.logic;

/**
 * Created by joaof on 23/05/2017.
 */

public class GameMap {
    private static final char blockSymbol = 'X';
    private char[][] map;

    public GameMap(int columns, int lines) {
        map = new char[columns][lines];
    }
}
