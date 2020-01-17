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

    // numbers
    public final String one = "Numbers/one.png";
    public final String two = "Numbers/two.png";
    public final String three = "Numbers/three.png";
    public final String four = "Numbers/four.png";
    public final String five = "Numbers/five.png";
    public final String six = "Numbers/six.png";
    public final String seven = "Numbers/seven.png";
    public final String eight = "Numbers/eight.png";
    public final String nine = "Numbers/nine.png";
    public final String zero = "Numbers/zero.png";


    public void queueAddImages() {
        manager.load(player, Texture.class);
        manager.load(asteroid, Texture.class);
        manager.load(leftWall, Texture.class);
        manager.load(rightWall, Texture.class);
        manager.load(background, Texture.class);
        manager.load(flameAsteroid, Texture.class);

        manager.load(one, Texture.class);
        manager.load(two, Texture.class);
        manager.load(three, Texture.class);
        manager.load(four, Texture.class);
        manager.load(five, Texture.class);
        manager.load(six, Texture.class);
        manager.load(seven, Texture.class);
        manager.load(eight, Texture.class);
        manager.load(nine, Texture.class);
        manager.load(zero, Texture.class);
    }

}
