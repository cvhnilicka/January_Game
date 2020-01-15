package com.cormucopiastudios.januarygame.GameEngine.Loader;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class B2AssetManager {

    public final AssetManager manager = new AssetManager();

    // player
    public final String player = "shiptotal.png";
    public final String asteroid = "asteroid.png";
    public final String leftWall = "LeftRocks.png";
    public final String rightWall = "RightRocks.png";
    public final String background = "star_background1.png";
    public final String flameAsteroid = "flaming_asteroid.png";


    public void queueAddImages() {
        manager.load(player, Texture.class);
        manager.load(asteroid, Texture.class);
        manager.load(leftWall, Texture.class);
        manager.load(rightWall, Texture.class);
        manager.load(background, Texture.class);
        manager.load(flameAsteroid, Texture.class);
    }

}
