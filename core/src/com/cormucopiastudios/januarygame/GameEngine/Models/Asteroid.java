package com.cormucopiastudios.januarygame.GameEngine.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.cormucopiastudios.januarygame.GameEngine.B2Model;
import com.cormucopiastudios.januarygame.GameEngine.Factories.BodyFactory;

import java.util.Random;

public class Asteroid extends Sprite {

    public Body b2body;
    private B2Model parent;

    public Asteroid(B2Model parent, float posx, float posy) {
        super((Texture)parent.getAss().manager.get(parent.getAss().asteroid));
        this.parent = parent;
        BodyFactory bFact = BodyFactory.getInstance(parent.world);
        this.b2body = bFact.makeCirclePolyBody(posx,posy,2,BodyFactory.FIXTURE_TYPE.STONE);
        setBounds(b2body.getPosition().x, b2body.getPosition().y, 4, 4);
        setPosition(b2body.getPosition().x, b2body.getPosition().y);
    }

    public boolean updateY() {
        float leftBound = -(this.parent.getGamecam().viewportWidth/2)+.5f;
        float rightBound = (this.parent.getGamecam().viewportWidth/2)-.5f;
        Random ran = new Random();
        float xPos;
        float yPos;

        if (this.b2body.getPosition().y < -15) {
            xPos = ran.nextFloat() * (rightBound - leftBound + 1.0f) + leftBound;
            yPos = ran.nextFloat() * (20 - 15 + 1f) + 15;
            this.b2body.setTransform(new Vector2(xPos,yPos),this.b2body.getAngle());
            this.b2body.setLinearVelocity(new Vector2(0,-10));
            return true;
        }
        return false;
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x-getWidth()/2, b2body.getPosition().y-getHeight()/2);
    }

}
