package com.mygdx.tetris.logic;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Created by up201505145 on 22/05/2017.
 */
public abstract class Piece {
    private ArrayList<Block> blocks;

    public ArrayList<Point> getCoords() {
        ArrayList<Point> coords = new ArrayList<Point>();
        for (Block block : blocks) {
            coords.add(block.getCoords());
        }
        return coords;
    }

    public void move(char direction) {
        switch(direction) {
            case 'W':
                moveUp(direction);
                break;
            case 'S':
                moveDown(direction);
                break;
            case 'A':
                moveLeft(direction);
                break;
            case 'D':
                moveRight(direction);
                break;
        }
    }

    private void moveUp(char direction) {
        for (Block block : blocks) {
            block.moveUp();
        }
    }

    private void moveDown(char direction) {
    for (Block block : blocks) {
            block.moveDown();
        }
    }

    private void moveLeft(char direction) {
        for (Block block : blocks) {
            block.moveLeft();
        }
    }

    private void moveRight(char direction) {
        for (Block block : blocks) {
            block.moveRight();
        }
    }
}