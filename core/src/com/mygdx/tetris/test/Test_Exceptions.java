package com.mygdx.tetris.test;

import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.tetris.logic.CorruptedCell;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by up201505145 on 25/05/2017.
 */

public class Test_Exceptions extends GameTest {
    @Test
    public void corruptedCell() {
        try {
            throw new CorruptedCell(new GridPoint2(1, 2));
        } catch (CorruptedCell c) {
            assertEquals(new GridPoint2(1, 2), c.getCoords());
        }
    }
}
