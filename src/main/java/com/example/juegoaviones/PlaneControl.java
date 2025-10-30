package com.example.juegoaviones;

import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.KeyInput;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

/**
 * PlaneControl
 * Control personalizado del avión.
 * Gestiona el movimiento, rotación e inclinación del modelo 3D.
 */
public class PlaneControl implements ActionListener, AnalogListener {

    private final Spatial plane;
    private float speed = 15f;
    private float rotationSpeed = 2f;
    private float climbSpeed = 10f;

    private boolean pitchUp, pitchDown, rollLeft, rollRight;

    public PlaneControl(Spatial plane) {
        this.plane = plane;
    }

    /**
     * Registra las teclas de control en el gestor de entrada del juego.
     */
    public void registerInput(com.jme3.input.InputManager inputManager) {
        inputManager.addMapping("PitchUp", new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addMapping("PitchDown", new KeyTrigger(KeyInput.KEY_DOWN));
        inputManager.addMapping("RollLeft", new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addMapping("RollRight", new KeyTrigger(KeyInput.KEY_RIGHT));

        inputManager.addListener(this, "PitchUp", "PitchDown", "RollLeft", "RollRight");
    }

    /**
     * Actualiza la posición del avión cada frame.
     *
     * @param tpf tiempo por frame
     */
    public void update(float tpf) {
        if (plane == null) return;

        Vector3f forward = plane.getLocalRotation().mult(Vector3f.UNIT_Z).mult(speed * tpf);
        plane.move(forward);

        if (pitchUp) plane.rotate(-climbSpeed * tpf * FastMath.DEG_TO_RAD, 0, 0);
        if (pitchDown) plane.rotate(climbSpeed * tpf * FastMath.DEG_TO_RAD, 0, 0);
        if (rollLeft) plane.rotate(0, 0, rotationSpeed * tpf * FastMath.DEG_TO_RAD);
        if (rollRight) plane.rotate(0, 0, -rotationSpeed * tpf * FastMath.DEG_TO_RAD);
    }

    @Override
    public void onAction(String name, boolean isPressed, float tpf) {
        switch (name) {
            case "PitchUp" -> pitchUp = isPressed;
            case "PitchDown" -> pitchDown = isPressed;
            case "RollLeft" -> rollLeft = isPressed;
            case "RollRight" -> rollRight = isPressed;
        }
    }

    @Override
    public void onAnalog(String name, float value, float tpf) {
        // No se usa, pero puede servir si deseas movimientos suaves o aceleración progresiva
    }

    /**
     * Permite ajustar la velocidad del avión dinámicamente.
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * Retorna la velocidad actual del avión.
     */
    public float getSpeed() {
        return speed;
    }
}
