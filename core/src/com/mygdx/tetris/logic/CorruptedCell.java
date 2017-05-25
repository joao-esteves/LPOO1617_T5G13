package com.mygdx.tetris.logic;

import com.badlogic.gdx.math.GridPoint2;

/**
 * Created by up201505145 on 23/05/2017.
 */
public class CorruptedCell extends Throwable {
    private GridPoint2 coords;

    public CorruptedCell(GridPoint2 coords) {
        this.coords = coords;
    }

    public GridPoint2 getCoords() {
        return coords;
    }
}
