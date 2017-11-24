package com.example.pedestrian.bigbaby.Fragments;

/**
 * Fragment containing the ServoView.
 *
 * Created by Zhipeng Dong on 10/30/16.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pedestrian.bigbaby.Core.ControlMode;
import com.example.pedestrian.bigbaby.R;
import com.example.pedestrian.bigbaby.Views.ServoView;


public class ServoFragment extends Fragment {
    private ServoView virtualJoystick;
    private View view;
    private ControlMode controlMode = ControlMode.Joystick;

    /**
     * Default Constructor.
     */
    public ServoFragment() {
    }

    /**
     * Create this Fragments View.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the view if needed
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_servo_view, container, false);

            // Grab the JoystickView and set its topic
            virtualJoystick = (ServoView) view.findViewById(R.id.servo_view);
        }

        return view;
    }

    /**
     * Returns the ServoView
     * @return The ServoView
     */
    public ServoView getJoystickView() {
        return virtualJoystick;
    }

    /**
     * Get the currently active ControlMode.
     * @return The current ControlMode
     */
    public ControlMode getControlMode() {
        return controlMode;
    }

    /**
     * Set the ControlMode for controlling the Joystick.
     * @param controlMode The new ControlMode
     */
    public void setControlMode(ControlMode controlMode) {
        this.controlMode = controlMode;
        this.invalidate();
    }

    /**
     * Tests whether the Joystick supports accelerometer control.
     * @return True if the Joystick supports accelerometer control, false otherwise
     */
    @SuppressWarnings("unused") // Maybe later...
    public boolean hasAccelerometer() {
        return virtualJoystick.hasAccelerometer();
    }

    /**
     * Invalidate the Fragment, updating the visibility of the Joystick based on the ControlMode.
     *
     */
    public void invalidate() {

        switch (controlMode) {
            case Joystick:
                show();
                break;

            case Tilt:
                show();
                break;

            default:
                hide();
                break;
        }

        virtualJoystick.setControlMode(controlMode);
        virtualJoystick.controlSchemeChanged();
    }

    /**
     * Stops the ServoFragment.
     */
    public void stop() {
        virtualJoystick.stop();
    }

    /**
     * Shows the ServoFragment.
     */
    public void show(){
        getFragmentManager()
                .beginTransaction()
                .show(this)
                .commit();
    }

    /**
     * Hides the ServoFragment.
     */
    public void hide(){
        getFragmentManager()
                .beginTransaction()
                .hide(this)
                .commit();
    }
}
