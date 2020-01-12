package com.cormucopiastudios.januarygame.GameEngine.Loader;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class B2AssetManager {

    public final AssetManager manager = new AssetManager();

    // player
    public final String player = "shiptotal.png";


    public void queueAddImages() {
        manager.load(player, Texture.class);
    }

}
