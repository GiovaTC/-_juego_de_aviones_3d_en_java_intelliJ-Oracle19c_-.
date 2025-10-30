package com.example.juegoaviones.hud;

import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.scene.Node;

public class HUD {
    private Node guiNode;
    private BitmapFont guiFont;
    private BitmapFont hudText;
    private BitmapText messageText;

    public HUD(Node guiNode, BitmapFont guiFont) {
        this.guiNode = guiNode;
        this.guiFont = guiFont;
        init();
    }

    private void init() {
        
    }
}
