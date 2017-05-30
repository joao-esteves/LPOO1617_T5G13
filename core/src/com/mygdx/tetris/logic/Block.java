package com.mygdx.tetris.logic;

import com.badlogic.gdx.math.GridPoint2;

/**
 * Created by up201505145 on 22/05/2017.
 */
public class Block implements Comparable {
    private GridPoint2 coords;
    private char symbol;

    public Block(int x, int y, char symbol) {
        coords = new GridPoint2(x, y);
        this.symbol = symbol;
    }

    public Block(GridPoint2 coords, char symbol) {
        setCoords(coords);
        this.symbol = symbol;
    }

    public void setCoords(GridPoint2 coords) {
        this.coords = coords;
    }

    public void setCoords(int x, int y) {
        coords = new GridPoint2(x, y);
    }

    public GridPoint2 getCoords() {
        return new GridPoint2(coords);
    }

    public void moveDown() {
        coords.set(coords.x, coords.y - 1);
    }

    public void moveLeft() {
        coords.set(coords.x - 1, coords.y);
    }

    public void moveRight() {
        coords.set(coords.x + 1, coords.y);
    }

    public Object clone() {
        return new Block(coords, symbol);
    }

    public char getSymbol() {
        return symbol;
    }

    // Ordered vertically for priority queue.
    @Override
    public int compareTo(Object o) {
        Block otherBlock = (Block) o;
        if (this.getCoords().y > otherBlock.getCoords().y) {
            return 1;
        } else if (this.getCoords().y == otherBlock.getCoords().y) {
            if (this.getCoords().x > otherBlock.getCoords().x) {
                return 1;
            } else if (this.getCoords().x == otherBlock.getCoords().x) {
                return 0;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    // For priority queue ordering by vertical position.

}
