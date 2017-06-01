package com.mygdx.tetris.test;

import com.mygdx.tetris.TetrisGame;
import com.mygdx.tetris.facebook.FB;

import org.junit.Test;

/**
 * Created by joaof on 31/05/2017.
 */

public class Test_Facebook {
    @Test
    public void login() {
        FB facebook = new FB();
        facebook.login();
    }
}
