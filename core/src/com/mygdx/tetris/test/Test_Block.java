package com.mygdx.tetris.test;

import com.badlogic.gdx.math.GridPoint2;
import com.mygdx.tetris.logic.Block;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by up201505145 on 25/05/2017.
 */

public class Test_Block extends GameTest {
    @Test
    public void creation() {
        Block gridBlock = new Block(new GridPoint2(1, 2), 'A');
        assertEquals(new GridPoint2(1, 2), gridBlock.getCoords());
        assertEquals('A', gridBlock.getSymbol());
        Block intBlock = new Block(2, 1, 'B');
        assertEquals(new GridPoint2(2, 1), intBlock.getCoords());
        assertEquals('B', intBlock.getSymbol());
    }

    @Test
    public void setCoords() {
        Block block = new Block(0, 0, 'A');
        block.setCoords(2, 9);
        assertEquals(new GridPoint2(2, 9), block.getCoords());
        block.setCoords(new GridPoint2(8, 5));
        assertEquals(new GridPoint2(8, 5), block.getCoords());
    }

    @Test
    public void cloneBlock() {
        Block block = new Block(1, 1, 'C');
        Block clonedBlock = (Block) block.clone();
        block.setCoords(2, 2);
        assertEquals(new GridPoint2(2, 2), block.getCoords());
        assertEquals(new GridPoint2(1, 1), clonedBlock.getCoords());
    }

    @Test
    public void compare() {
        Block lowerBlock = new Block(0, 0, 'A');
        Block higherBlock = new Block(0, 1, 'B');
        Block anotherHigherBlock = new Block(0, 1, 'D');

        assertEquals(1, higherBlock.compareTo(lowerBlock));
        assertEquals(0, higherBlock.compareTo(anotherHigherBlock));
        assertEquals(-1, lowerBlock.compareTo(higherBlock));
    }
}
