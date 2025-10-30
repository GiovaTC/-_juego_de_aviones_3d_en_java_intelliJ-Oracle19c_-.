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

/**
 * 🚀 GameApp — Juego de Aviones 3D con jMonkeyEngine + Oracle JDBC.
 *
 * Controla un avión simple en 3D, muestra tiempo y puntuación en pantalla,
 * y guarda los resultados en una base de datos Oracle 19c.
 *
 * Controles:
 *   ↑  : Subir avión
 *   ↓  : Bajar avión
 *
 * Autor: Giovanny Tapiero
 * Versión: 1.0
 */
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
    public void simpleInitApp() {
        // === Crear avión como cubo azul ===
        Box box = new Box(1, 0.3f, 3);
        Geometry geom = new Geometry("Plane", box);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);

        planeNode = new Node("planeNode");
        planeNode.attachChild(geom);
        planeNode.setLocalTranslation(0, 5, 0);
        rootNode.attachChild(planeNode);

        // === Luz ===
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(-0.5f, -1f, -0.5f));
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);

        // === Cámara inicial ===
        cam.setLocation(new Vector3f(0, 8, 16));
        cam.lookAt(planeNode.getLocalTranslation(), Vector3f.UNIT_Y);

        // === Controles de teclado ===
        inputManager.addMapping(ACTION_PITCH_UP, new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addMapping(ACTION_PITCH_DOWN, new KeyTrigger(KeyInput.KEY_DOWN));
        inputManager.addListener(actionListener, ACTION_PITCH_UP, ACTION_PITCH_DOWN);

        // === HUD y conexión BD ===
        if (guiFont == null) {
            guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        }

        hud = new HUD(guiNode, guiFont);
        playerDAO = new PlayerDAO();

        startTime = System.currentTimeMillis();
        hud.updateHUD(0, score);
        hud.showMessage("✈️ Bienvenido, piloto. ¡Usa ↑ / ↓ para volar!");
    }

    private final ActionListener actionListener = new ActionListener() {
        public void onAction(String name, boolean isPressed, float tpf) {
            if (name.equals(ACTION_PITCH_UP))
                pitch = isPressed ? 0.8f : 0f;
            if (name.equals(ACTION_PITCH_DOWN))
                pitch = isPressed ? -0.8f : 0f;
        }
    };

    @Override
    public void simpleUpdate(float tpf) {
        // Movimiento del avión
        Vector3f loc = planeNode.getLocalTranslation();
        loc.addLocal(0, pitch * tpf * 5f, -speed * tpf);
        planeNode.setLocalTranslation(loc);

        // Actualizar cámara detrás del avión
        cam.setLocation(new Vector3f(loc.x, loc.y + 6f, loc.z + 12f));
        cam.lookAt(planeNode.getLocalTranslation(), Vector3f.UNIT_Y);

        // Actualizar HUD
        long elapsedSec = (System.currentTimeMillis() - startTime) / 1000;
        hud.updateHUD((int) elapsedSec, score);

        // Fin del recorrido
        if (loc.z < -1000) endGame();
    }

    private void endGame() {
        long elapsedSec = (System.currentTimeMillis() - startTime) / 1000;
        PlayerScore ps = new PlayerScore("JugadorLocal", score, (int) elapsedSec);

        try {
            playerDAO.insertScore(ps);
            hud.showMessage("✅ Juego terminado. Puntuación guardada: " + score);
        } catch (Exception e) {
            hud.showMessage("⚠️ Error guardando en BD: " + e.getMessage());
        }

        stop();
    }
}