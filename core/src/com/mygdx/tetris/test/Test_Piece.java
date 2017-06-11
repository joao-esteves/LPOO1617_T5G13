package com.mygdx.tetris.test;

import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.tetris.logic.Direction;
import com.mygdx.tetris.logic.GameMap;
import com.mygdx.tetris.logic.JPiece;
import com.mygdx.tetris.logic.Piece;
import com.mygdx.tetris.logic.PieceFactory;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by up201505145 on 22/05/2017.
 */

public class Test_Piece {

    @Test
    public void movePiece() {
        GameMap map = new GameMap(10,10);
        GridPoint2 spawnPos = new GridPoint2(3, 9);
        PieceFactory pieceFactory = new PieceFactory(spawnPos);
        Piece piece = pieceFactory.makePiece(map);
        final ArrayList<GridPoint2> oldCoords = piece.getCoords();
        ArrayList<GridPoint2> newCoords;

        // test move left
        piece.move(Direction.LEFT);
        newCoords = piece.getCoords();
        for (int i = 0; i < oldCoords.size(); i++) {
            GridPoint2 expectedPoint = new GridPoint2(oldCoords.get(i));
            expectedPoint.add(-1,0);
            GridPoint2 newPoint = newCoords.get(i);
            assertEquals(expectedPoint, newPoint);
        }
        piece.move(Direction.RIGHT);

        // test move right
        piece.move(Direction.RIGHT);
        newCoords = piece.getCoords();
        for (int i = 0; i < oldCoords.size(); i++) {
            GridPoint2 expectedPoint = new GridPoint2(oldCoords.get(i));
            expectedPoint.add(1,0);
            GridPoint2 newPoint = newCoords.get(i);
            assertEquals(expectedPoint, newPoint);
        }
        piece.move(Direction.LEFT);

        // test move down
        piece.move(Direction.DOWN);
        newCoords = piece.getCoords();
        for (int i = 0; i < oldCoords.size(); i++) {
            GridPoint2 expectedPoint = new GridPoint2(oldCoords.get(i));
            expectedPoint.add(0,-1);
            GridPoint2 newPoint = newCoords.get(i);
            assertEquals(expectedPoint, newPoint);
        }
    }
}
