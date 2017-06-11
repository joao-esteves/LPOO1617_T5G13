package com.mygdx.tetris.logic;

import com.badlogic.gdx.math.GridPoint2;

/**
 * A single block consisting of coordinates and an unique symbol.
 */
public class Block implements Comparable {
    private GridPoint2 coords;
    private char symbol;

    /**
     * Creates a block with the given coordinates and symbol.
     * @param x Horizontal coordinate. Must be >= 0.
     * @param y Vertical coordinate. Must be >= 0.
     * @param symbol Unique character.
     */
    public Block(int x, int y, char symbol) {
        coords = new GridPoint2(x, y);
        this.symbol = symbol;
    }

    /**
     * Creates a block with the given coordinates and symbol.
     * @param coords Pair of coordinates. Both must be >= 0.
     * @param symbol Unique character.
     */
    public Block(GridPoint2 coords, char symbol) {
        setCoords(coords);
        this.symbol = symbol;
    }

    /**
     * Changes the Block's coordinates.
     * @param coords New pair of coordinates.
     */
    public void setCoords(GridPoint2 coords) {
        this.coords = coords;
    }

    /**
     * Changes the Block's coordinates.
     * @param x New horizontal coordinate.
     * @param y New vertical coordinate.
     */
    public void setCoords(int x, int y) {
        coords = new GridPoint2(x, y);
    }

    /**
     * Returns the Block's coordinates.
     * @return Current pair of coordinates.
     */
    public GridPoint2 getCoords() {
        return new GridPoint2(coords);
    }

    /**
     * Moves the Block down by 1 unit by decreasing y.
     */
    public void moveDown() {
        coords.set(coords.x, coords.y - 1);
    }

    /**
     * Moves the Block left by 1 unit by decreasing x.
     */
    public void moveLeft() {
        coords.set(coords.x - 1, coords.y);
    }

    /**
     * Moves the Block right by 1 unit by increasing x.
     */
    public void moveRight() {
        coords.set(coords.x + 1, coords.y);
    }

    /**
     * Returns a copy of the Block with the same coordinates and symbol.
     * @return New copied Block.
     */
    public Object clone() {
        return new Block(coords, symbol);
    }

    /**
     * Returns the Block's symbol.
     * @return Current symbol.
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Ordered vertically for priority queue.
     * @param o Block to compare to.
     * @return 1 if this block has higher y or higher x with same y, 0 if same coordinates, -1 otherwise.
     */
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
}
