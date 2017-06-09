package com.mygdx.tetris.logic;

import com.badlogic.gdx.math.GridPoint2;

import java.util.Arrays;

import static com.mygdx.tetris.logic.Direction.DOWN;
import static com.mygdx.tetris.logic.Direction.LEFT;
import static com.mygdx.tetris.logic.Direction.RIGHT;
import static com.mygdx.tetris.logic.Direction.UP;

/**
 * Created by up201505145 on 05/06/2017.
 */
public class TPiece extends Piece {
    public TPiece(GridPoint2 pos) {
        orientation = UP;
        symbol = 'T';
        blocks = Arrays.asList(new Block(pos.x+1, pos.y, symbol), new Block(pos.x, pos.y-1, symbol),
                            new Block(pos.x+1, pos.y-1, symbol), new Block(pos.x+2, pos.y-1, symbol));
        axisBlockIndex = 2;
    }

//    @Override
//    protected void rotateClockwise() {
//        GridPoint2 axis = blocks.get(2).getCoords();
//        switch (orientation) {
//            case UP:
//                setRightOrientation(axis);
//                break;
//            case DOWN:
//                setLeftOrientation(axis);
//                break;
//            case LEFT:
//                setUpOrientation(axis);
//                break;
//            case RIGHT:
//                setDownOrientation(axis);
//                break;
//        }
//    }

//    @Override
//    protected void rotateAnticlockwise() {
//        GridPoint2 axis = blocks.get(2).getCoords();
//        switch (orientation) {
//            case UP:
//                setLeftOrientation(axis);
//                break;
//            case DOWN:
//                setRightOrientation(axis);
//                break;
//            case LEFT:
//                setDownOrientation(axis);
//                break;
//            case RIGHT:
//                setUpOrientation(axis);
//                break;
//        }
//    }

    @Override
    protected void setDownOrientation(GridPoint2 axis) {
        GridPoint2[] newCoords = new GridPoint2[3];
        newCoords[0] = new GridPoint2(axis.x, axis.y-1);
        newCoords[1] = new GridPoint2(axis.x+1, axis.y);
        newCoords[2] = new GridPoint2(axis.x-1, axis.y);

        if (!map.canDrawAt(newCoords)) {
            return;
        }

        blocks.get(0).setCoords(newCoords[0]);
        blocks.get(1).setCoords(newCoords[1]);
        blocks.get(3).setCoords(newCoords[2]);
        orientation = DOWN;
    }

    @Override
    protected void setUpOrientation(GridPoint2 axis) {
        GridPoint2[] newCoords = new GridPoint2[3];
        newCoords[0] = new GridPoint2(axis.x, axis.y+1);
        newCoords[1] = new GridPoint2(axis.x-1, axis.y);
        newCoords[2] = new GridPoint2(axis.x+1, axis.y);

        if (!map.canDrawAt(newCoords)) {
            return;
        }

        blocks.get(0).setCoords(newCoords[0]);
        blocks.get(1).setCoords(newCoords[1]);
        blocks.get(3).setCoords(newCoords[2]);
        orientation = UP;
    }

    @Override
    protected void setLeftOrientation(GridPoint2 axis) {
        GridPoint2[] newCoords = new GridPoint2[3];
        newCoords[0] = new GridPoint2(axis.x-1, axis.y);
        newCoords[1] = new GridPoint2(axis.x, axis.y-1);
        newCoords[2] = new GridPoint2(axis.x, axis.y+1);

        if (!map.canDrawAt(newCoords)) {
            return;
        }

        blocks.get(0).setCoords(newCoords[0]);
        blocks.get(1).setCoords(newCoords[1]);
        blocks.get(3).setCoords(newCoords[2]);
        orientation = LEFT;
    }

    @Override
    protected void setRightOrientation(GridPoint2 axis) {
        GridPoint2[] newCoords = new GridPoint2[3];
        newCoords[0] = new GridPoint2(axis.x+1, axis.y);
        newCoords[1] = new GridPoint2(axis.x, axis.y+1);
        newCoords[2] = new GridPoint2(axis.x, axis.y-1);

        if (!map.canDrawAt(newCoords)) {
            return;
        }

        blocks.get(0).setCoords(newCoords[0]);
        blocks.get(1).setCoords(newCoords[1]);
        blocks.get(3).setCoords(newCoords[2]);
        orientation = RIGHT;
    }

    @Override
    public void setPos(GridPoint2 pos) {
        blocks.set(0, new Block(pos.x+1, pos.y, symbol));
        blocks.set(1, new Block(pos.x, pos.y-1, symbol));
        blocks.set(2, new Block(pos.x+1, pos.y-1, symbol));
        blocks.set(3, new Block(pos.x+2, pos.y-1, symbol));
    }
}
