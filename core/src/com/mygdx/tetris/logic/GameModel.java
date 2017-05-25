package com.mygdx.tetris.logic;

import com.badlogic.gdx.math.GridPoint2;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by joaof on 12/05/2017.
 */

public class GameModel {

    private static ArrayList<GameModel> instances;
    private GridPoint2 spawnPos;
    private PieceFactory pieceFactory;
    private GameMap map;
    private PriorityQueue<Block> blocks;
    private Piece currentPiece;
    private GameStatus status;
    private int completedLines;

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public PriorityQueue<Block> getBlocks() {
        return blocks;
    }

    public GameMap getMap() {
        return map;
    }

    public GameModel(int columns, int lines) {
        spawnPos = new GridPoint2(columns/2 - 1, lines - 1);
        map = new GameMap(columns, lines);
        pieceFactory = new PieceFactory(spawnPos);
        blocks = new PriorityQueue<Block>();
        currentPiece = pieceFactory.makePiece(map);
        map.drawPiece(currentPiece);
        status = GameStatus.ONGOING;
        completedLines = 0;
    }

//    public static GameModel getInstance(int columns, int lines) {
//        if (instances == null) {
//            instances = new ArrayList<GameModel>();
//        }
//
//        for (GameModel instance : instances) {
//            if (instance.getMap().getCols() == columns && instance.getMap().getLines() == lines) {
//                return instance;
//            }
//        }
//
//        GameModel newInstance = new GameModel(columns, lines);
//        instances.add(newInstance);
//        return newInstance;
//    }

    public void nextCycle(Direction direction) throws CorruptedCell {
        if (status != GameStatus.ONGOING) {
            return;
        }
        map.clearPiece(currentPiece);
        if (map.pieceCollidedDownwards(currentPiece)) {
            blocks.addAll(currentPiece.getBlocks());
            map.drawBlocks(currentPiece.getBlocks());
            checkLineCompletion(currentPiece.getBlocks());
            currentPiece = pieceFactory.makePiece(map);
            if (map.isOccupied(currentPiece)) {
                loseGame();
            }
        } else {
            currentPiece.move(direction);
        }
        map.drawPiece(currentPiece);
    }

    public void dropFloatingBlocks() {
        for (Block block : blocks) {
            if (!map.blockHasFloor(block)) {
                map.clearCell(block.getCoords());
                block.moveDown();
                map.drawCell(block.getCoords(), block.getSymbol());
            }
        }
    }

    private void checkLineCompletion(List<Block> blocks) {
        for (Block block : blocks) {
            if (map.lineIsCompleted(block.getCoords().y)) {
                completedLines++;
                map.eraseLine(block.getCoords().y);
                this.blocks.removeAll(blocks);
            }
        }
    }

    private void loseGame() {
        status = status.DEFEAT;
    }

    private boolean isAtEdges(Piece piece) {
        for (Block block : piece.getBlocks()) {
            if (block.getCoords().x == 0 || block.getCoords().x == map.getCols() - 1) {
                return true;
            }
        }
        return false;
    }
}
