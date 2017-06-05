package com.mygdx.tetris.gui;

/**
 * Created by up201505145 on 24/05/2017.
 */

// TODO: ColorEnum besides red
public enum ColorEnum {
    CYAN (0, "cyan_block", 'I'),
    BLUE (1, "blue_block", 'J'),
    GREEN (2, "green_block", 'S'),
    YELLOW (3, "yellow_block", 'O'),
    ORANGE (4, "orange_block", 'L'),
    RED (5, "red_block", 'Z'),
    PURPLE (6, "purple_block", 'T');

    public int val;
    public String imgName;
    public char symbol;

    private ColorEnum(int value, String imgName, char symbol) {
        this.val = value;
        this.imgName = imgName;
        this.symbol = symbol;
    }

    public static ColorEnum getColor(char symbol) {
        for (ColorEnum color : ColorEnum.values()) {
            if (symbol == color.symbol) {
                return color;
            }
        }
        return null;
    }
}
