package com.example.juegoaviones;

import com.example.juegoaviones.db.PlayerDAO;
import com.example.juegoaviones.hud.HUD;
import com.example.juegoaviones.model.PlayerScore;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;


public class GameApp extends SimpleApplication {

    private Node planeNode;
    private float pitch = 0f;
    private float speed = 10f;
    private HUD hud;
    private PlayerDAO playerDAO;
    private long startTime;
    private int score = 0;

    private static final String ACTION_PITCH_UP = "PitchUp";
    private static final String ACTION_PITCH_DOWN = "PitchDown";

    @Override
    public void simpleInitApp() {}
}
