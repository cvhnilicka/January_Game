package com.cormucopiastudios.januarygame.GameEngine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.cormucopiastudios.januarygame.GameEngine.Factories.BodyFactory;

public class Asteroid extends Sprite {

    public Body b2body;
    private B2Model parent;

    public Asteroid(B2Model parent, float posx, float posy) {
        this.parent = parent;
        BodyFactory bFact = BodyFactory.getInstance(parent.world);
        this.b2body = bFact.makeCirclePolyBody(posx,posy,2,BodyFactory.FIXTURE_TYPE.STONE);
    }

    public boolean updateY() {
        if (this.b2body.getPosition().y < -15) {
            return true;
        }
        return false;
    }

    public void update(float dt) {

    }

//    private void defineAsteroid() {
//        this.b2body = bfact.makeCirclePolyBody(-4,2,2,BodyFactory.FIXTURE_TYPE.STONE);
//    }
}
