package com.mygdx.tetris.logic;

import com.badlogic.gdx.math.GridPoint2;

import java.util.List;

/**
 * Created by joaof on 23/05/2017.
 */

public class GameMap {
    public static final char[] blockSymbols = {'I','J','L','O','S','T','Z'};
    private char[][] map;

    public GameMap(int columns, int lines) {
        map = new char[columns][lines];
    }

    public void drawPiece(Piece piece) {
        for (int i = 0; i < piece.blocks.size(); i++) {
            Block block = piece.blocks.get(i);
            if (!isOcuppied(block.getCoords())) {
                drawCell(block.getCoords(), piece.getSymbol());
            } else {
                for (int j = 0; j < i; j++) {
                    Block currentBlock = piece.blocks.get(j);
                    clearCell(currentBlock.getCoords());
                }
                break;
            }
        }
    }

    public void clearPiece(Piece piece) throws CorruptedCell {
        for (int i = 0; i < piece.blocks.size(); i++) {
            Block block = piece.blocks.get(i);
            if (isOcuppied(block.getCoords())) {
                clearCell(block.getCoords());
            } else {
                throw new CorruptedCell(block.getCoords());
            }
        }
    }

    public void drawBlocks(List<Block> blocks) throws CorruptedCell {
        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i);
            if (!isOcuppied(block.getCoords())) {
                drawCell(block.getCoords(), block.getSymbol());
            } else {
                throw new CorruptedCell(block.getCoords());
            }
        }
    }

    private boolean isOcuppied(GridPoint2 coords) {
        return (map[coords.x][coords.y] != '\0');
    }

    private void clearCell(GridPoint2 coords) {
        map[coords.x][coords.y] = '\0';
    }

    private void drawCell(GridPoint2 coords, char symbol) {
        map[coords.x][coords.y] = symbol;
    }

    public boolean pieceCollidedDownwards(Piece piece) {
        for (Block block : piece.blocks) {
            if (block.getCoords().y == 0
                || isOcuppied(block.getCoords().add(0,-1))) {
                return true;
            }
        }
        return false;
    }
}
