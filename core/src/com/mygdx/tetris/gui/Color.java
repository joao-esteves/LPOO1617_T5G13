package com.mygdx.tetris.gui;

/**
 * Created by up201505145 on 24/05/2017.
 */

// TODO: Color besides red
public enum Color {
    CYAN (0, "red_block", 'I'),
    BLUE (1, "red_block", 'J'),
    GREEN (2, "red_block", 'S'),
    YELLOW (3, "red_block", 'O'),
    ORANGE (4, "red_block", 'L'),
    RED (5, "red_block", 'Z');

    public int val;
    public String imgName;
    public char symbol;

    private Color(int value, String imgName, char symbol) {
        this.val = value;
        this.imgName = imgName;
        this.symbol = symbol;
    }

    public static Color getColor(char symbol) {
        for (Color color : Color.values()) {
            if (symbol == color.symbol) {
                return color;
            }
        }
        return null;
    }
}
