package com.mygdx.tetris.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.GridPoint3;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by up201505145 on 06/06/2017.
 */
public class AccelManager {

    private static final float SHAKE_TRESHOLD = 50f;
    private float accumulatedDelta = 0;
    Vector3 lastAccel = new Vector3();
    Vector3 currentAccel = new Vector3();

    public boolean shook(float delta) {
        accumulatedDelta += delta;

        if (accumulatedDelta >= 0.1) {
            currentAccel.set(Gdx.input.getAccelerometerX(), Gdx.input.getAccelerometerY(), Gdx.input.getAccelerometerZ());
            float val = Math.abs(currentAccel.x + currentAccel.y + currentAccel.z - lastAccel.x - lastAccel.y - lastAccel.z)
                    / accumulatedDelta;

            lastAccel.set(currentAccel);
            accumulatedDelta = 0;

            return (val > SHAKE_TRESHOLD);
        }

        return false;
    }
}
