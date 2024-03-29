package com.mygdx.tetris.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.GridPoint3;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by up201505145 on 06/06/2017.
 */
public class AccelManager {

    private static final float SHAKE_TRESHOLD = 50f;
    private static final double MIN_ANGLE = 25.0; //degrees
    private float accumulatedDelta = 0;
    private float tiltLeftDelta = 0;
    private float tiltRightDelta = 0;
    private boolean wasUpright = true;
    private Vector3 lastAccel = new Vector3();
    private Vector3 currentAccel = new Vector3();

    public boolean shookZ(float delta) {
        accumulatedDelta += delta;

        if (accumulatedDelta >= 0.1) {
            currentAccel.set(Gdx.input.getAccelerometerX(), Gdx.input.getAccelerometerY(), Gdx.input.getAccelerometerZ());
            float val = Math.abs(currentAccel.z - lastAccel.z) / accumulatedDelta;

            lastAccel.set(currentAccel);
            accumulatedDelta = 0;

            return (val > SHAKE_TRESHOLD);
        }

        return false;
    }

    public boolean tiltedLeft(float delta) {
        tiltLeftDelta += delta;

        if (tiltLeftDelta >= 0.2) {
            tiltLeftDelta = 0;
            if (tiltingLeft()) {
                if (wasUpright) {
                    wasUpright = false;
                    return true;
                }
            } else if (!tiltingRight()) {
                wasUpright = true;
            }
        }

        return false;
    }

    public boolean tiltedRight(float delta) {
        tiltRightDelta += delta;

        if (tiltRightDelta >= 0.2) {
            tiltRightDelta = 0;
            if (tiltingRight()) {
                if (wasUpright) {
                    wasUpright = false;
                    return true;
                }
            } else if (!tiltingLeft()) {
                wasUpright = true;
            }
        }

        return false;
    }

    public boolean tiltingLeft() {
        Vector3 accel = new Vector3(Gdx.input.getAccelerometerX(), Gdx.input.getAccelerometerY(), Gdx.input.getAccelerometerZ());
        return (calcXYTiltAngle(accel) > MIN_ANGLE);
    }

    public boolean tiltingRight() {
        Vector3 accel = new Vector3(Gdx.input.getAccelerometerX(), Gdx.input.getAccelerometerY(), Gdx.input.getAccelerometerZ());
        return (calcXYTiltAngle(accel) < -MIN_ANGLE);
    }

    /**
     * Angle along the XY plane between the upright and current directions.
     * @return Angle in degrees. Positive means angling right relative to the viewer, negative means angling right.
     */
    private double calcXYTiltAngle(Vector3 accel) {
        return Math.atan2(accel.x, accel.y) * 180/Math.PI;
    }
}
