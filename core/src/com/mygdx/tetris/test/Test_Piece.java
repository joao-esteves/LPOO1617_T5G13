package com.mygdx.tetris.test;

import com.mygdx.tetris.logic.Piece;
import com.mygdx.tetris.logic.PieceFactory;

import org.junit.Test;

import java.awt.Point;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by up201505145 on 22/05/2017.
 */

public class Test_Piece {
    public void movePiece() {
        Piece piece = PieceFactory.getPiece();
        ArrayList<Point> oldCoords = piece.getCoords();
        ArrayList<Point> newCoords;

        // test move up
        piece.move('W');
        newCoords = piece.getCoords();
        piece.move('S');

        // test move down
        piece.move('S');
        newCoords = piece.getCoords();
        piece.move('W');

        // test move left
        piece.move('A');
        newCoords = piece.getCoords();
        piece.move('D');

        // test move right
        piece.move('D');
        newCoords = piece.getCoords();
        piece.move('A');
    }
}
