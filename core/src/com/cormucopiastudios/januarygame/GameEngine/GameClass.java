package com.cormucopiastudios.januarygame.GameEngine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cormucopiastudios.januarygame.JanuaryGame;

public class GameClass extends Game {

    public static final int V_WITDH = 32;    //virtual dimensions of the game
    public static final int V_HEIGHT = 24;
    public static final float PPM = 100;
    public SpriteBatch batch;  // thhis allows all screens to access it
    public static final short PLATFORM_BIT = 1;

    private JanuaryGame parent;

    public GameClass(JanuaryGame parent) {
        this.parent = parent;
        batch = new SpriteBatch();
        parent.setScreen(new PlayScreen(this)); // use this to set the game to the playscreen
    }

    public JanuaryGame getParent() {
        return parent;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
    }

    public void dispose() {
        batch.dispose();
    }
}
