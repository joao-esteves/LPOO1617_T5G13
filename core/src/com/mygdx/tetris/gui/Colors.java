package com.mygdx.tetris.gui;

/**
 * Created by up201505145 on 24/05/2017.
 */

// TODO: Colors besides red
public enum Colors {
    CYAN(0, "red_block"),
    BLUE(1, "red_block"),
    GREEN(2, "red_block"),
    YELLOW(3, "red_block"),
    ORANGE(4, "red_block"),
    RED(5, "red_block");

    public int val;
    public String imgName;

    private Colors(int value, String imgName) {
        this.val = value;
        this.imgName = imgName;
    }
}
