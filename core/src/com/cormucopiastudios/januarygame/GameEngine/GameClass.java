package com.cormucopiastudios.januarygame.GameEngine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cormucopiastudios.januarygame.JanuaryGame;

public class GameClass extends Game {

    public static final int V_WITDH = 400;    //virtual dimensions of the game
    public static final int V_HEIGHT = 208;
    public static final float PPM = 100;
    public SpriteBatch batch;  // thhis allows all screens to access it
    public static final short PLATFORM_BIT = 1;

    private JanuaryGame parent;

    public GameClass(JanuaryGame parent) {
        this.parent = parent;
//        parent.setScreen(); // use this to set the game to the playscreen
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
    }

    public void dispose() {
        batch.dispose();
    }
}
