package com.mygdx.tetris.logic;

import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * Main logic component. Holds the blocks, score and others.
 */

public class GameModel {

    private GridPoint2 spawnPos;
    private PieceFactory pieceFactory;
    private GameMap map;
    private TreeSet<Block> blocks;
    private Piece currentPiece;
    private GameStatus status;
    private int completedLines;

    /**
     * Returns the piece being controlled by the user.
     * @return
     */
    public Piece getCurrentPiece() {
        return currentPiece;
    }

    /**
     * Returns the blocks currently laid down, all the blocks which aren't in the current piece.
     * @return Blocks.
     */
    public TreeSet<Block> getBlocks() {
        return blocks;
    }

    /**
     * Returns the map, a 2D matrix of symbols.
     * @return Map.
     */
    public GameMap getMap() {
        return map;
    }


    public int getCompletedLines() {
        return completedLines;
    }

    /**
     * Initializes a game with user-defined dimensions.
     * @param columns Map columns (width).
     * @param lines Map lines (height).
     */
    public GameModel(int columns, int lines) {
        spawnPos = new GridPoint2(columns/2 - 1, lines - 1);
        map = new GameMap(columns, lines);
        pieceFactory = new PieceFactory(spawnPos);
        blocks = new TreeSet<Block>();
        currentPiece = pieceFactory.makePiece(map);
        map.drawPiece(currentPiece);
        status = GameStatus.ONGOING;
        completedLines = 0;
    }

    /**
     * Advances the game's flow.
     * Each cycle will move the current piece in the wanted direction and will check for line completion.
     * @param direction Direction to move the current piece in (left, right or down).
     * @throws CorruptedCell
     */
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

    /**
     * Returns the current game status.
     * @return Status indicating if the game is in progress or ended.
     */
    public GameStatus getStatus() {
        return status;
    }

    /**
     * Restarts the game.
     * Resets score to 0, eliminates blocks and map contents and initializes and draws a new piece.
     */
    public void restart() {
        blocks.clear();
        map.clearMap();
        completedLines = 0;
        currentPiece = pieceFactory.makePiece(map);
        map.drawPiece(currentPiece);
        status = GameStatus.ONGOING;
    }

    private void checkLineCompletion(List<Block> blocks) {
        ArrayList<Integer> linesToRemove = new ArrayList<Integer>();
        for (Block block : blocks) {
            if (!linesToRemove.contains(block.getCoords().y) && map.lineIsCompleted(block.getCoords().y)) {
                linesToRemove.add(block.getCoords().y);
            }
        }
        completedLines += linesToRemove.size();
        int lineAboveCleared = 0;
        for (int line : linesToRemove) {
            map.eraseLine(line);
            removeLineBlocks(line);
            if (line > lineAboveCleared) {
                lineAboveCleared = line;
            }
        }
        dropBlocksFrom(lineAboveCleared, linesToRemove.size());
    }

    private void dropBlocksFrom(int line, int dropLength) {
        for (Object o : this.blocks.toArray()) {
            Block block = (Block) o;
            if (block.getCoords().y < line) {
                continue;
            }
            map.clearCell(block.getCoords());
            for (int i = 0; i < dropLength; i++) {
                block.moveDown();
            }
            map.drawCell(block.getCoords(), block.getSymbol());
        }
    }

    // Assuming this.blocks is ordered vertically.
    private void removeLineBlocks(int line) {
        for (Object o : this.blocks.toArray()) {
            Block block = (Block) o;
            if (block.getCoords().y < line) {
                continue;
            }
            if (block.getCoords().y > line) {
                break;
            }
            this.blocks.remove(block);
        }
    }

    private void loseGame() {
        status = status.DEFEAT;
    }
}
