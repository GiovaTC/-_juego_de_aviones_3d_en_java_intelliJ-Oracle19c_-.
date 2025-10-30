package com.example.juegoaviones.hud;

import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.scene.Node;

/**
 * HUD (Head-Up Display)
 * Muestra información del juego como tiempo, puntuación y mensajes en pantalla.
 */
public class HUD {

    private final Node guiNode;
    private final BitmapFont guiFont;
    private BitmapText hudText;
    private BitmapText messageText;

    public HUD(Node guiNode, BitmapFont guiFont) {
        this.guiNode = guiNode;
        this.guiFont = guiFont;
        init();
    }

    private void init() {
        // === Texto principal del HUD (tiempo y puntuación) ===
        hudText = new BitmapText(guiFont, false);
        hudText.setSize(guiFont.getCharSet().getRenderedSize());
        hudText.setText("Tiempo: 0s | Puntuación: 0");
        hudText.setLocalTranslation(10, 580, 0); // esquina superior izquierda
        guiNode.attachChild(hudText);

        // === Texto para mensajes ===
        messageText = new BitmapText(guiFont, false);
        messageText.setSize(guiFont.getCharSet().getRenderedSize());
        messageText.setText("¡Bienvenido, piloto!");
        messageText.setLocalTranslation(10, 550, 0);
        guiNode.attachChild(messageText);
    }

    /**
     * Actualiza el HUD con tiempo y puntuación
     */
    public void updateHUD(int seconds, int score) {
        if (hudText != null) {
            hudText.setText(String.format("Tiempo: %ds | Puntuación: %d", seconds, score));
        }
    }

    /**
     * Muestra un mensaje dinámico en pantalla
     */
    public void showMessage(String msg) {
        if (messageText != null) {
            messageText.setText(msg);
        }
    }

    /**
     * Limpia los elementos del HUD al cerrar o reiniciar el juego
     */
    public void clear() {
        guiNode.detachChild(hudText);
        guiNode.detachChild(messageText);
    }
}
