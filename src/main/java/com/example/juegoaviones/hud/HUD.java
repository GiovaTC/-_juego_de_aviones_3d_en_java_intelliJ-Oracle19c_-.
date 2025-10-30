package com.example.juegoaviones.hud;

import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.scene.Node;

public class HUD {
    private Node guiNode;
    private BitmapFont guiFont;
    private BitmapText hudText;
    private BitmapText messageText;

    public HUD(Node guiNode, BitmapFont guiFont) {
        this.guiNode = guiNode;
        this.guiFont = guiFont;
        init();
    }

    private void init() {
        hudText = new BitmapText(guiFont, false);
        hudText.setSize(guiFont.getCharSet().getRenderedSize());
        hudText.setLocalTranslation(10, messageText.getLineHeight() * 2, 0);
        guiNode.attachChild(messageText);
    }

    public void updateHUD(int seconds, int score) {
        hudText.setText(String.format("Tiempo: %ds | Puntuacion: %d", seconds, score));
    }

    public void showMessage(String msg) {
        messageText.setText(msg);
    }
}
