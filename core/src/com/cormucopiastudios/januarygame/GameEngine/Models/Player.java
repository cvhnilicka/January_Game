package com.cormucopiastudios.januarygame.GameEngine.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.cormucopiastudios.januarygame.GameEngine.B2Model;
import com.cormucopiastudios.januarygame.GameEngine.Controller.DataController;
import com.cormucopiastudios.januarygame.GameEngine.Factories.BodyFactory;

public class Player extends Sprite {

    private B2Model parent;
    public Body b2body;

    private Texture texture;


    public Player(B2Model parent) {
        super((Texture)parent.getAss().manager.get(parent.getAss().whiteShip));
        this.parent = parent;
        texture = setShip();
        this.setTexture(texture);
        BodyFactory bFact = BodyFactory.getInstance(parent.world);
        b2body = bFact.makePlayerBody(0,-12,2.5f,6f,
                BodyFactory.FIXTURE_TYPE.STEEL, BodyDef.BodyType.DynamicBody, true, this);
        b2body.setGravityScale(0f);
        setBounds(b2body.getPosition().x, b2body.getPosition().y, 2.5f, 6);
        setPosition(b2body.getPosition().x, b2body.getPosition().y);

    }

    private Texture setShip() {
        Gdx.app.log("Ship Pref", DataController.getInstance().getShipPref());
        if (DataController.getInstance().getShipPref().equals("whiteShip")) {
            return (Texture)parent.getAss().manager.get(parent.getAss().whiteShip);
        }
        return (Texture)parent.getAss().manager.get(parent.getAss().redShip);
    }

    public void bounceDown() {
        this.b2body.applyLinearImpulse(new Vector2(0,-5), b2body.getWorldCenter(), true);
    }
    public void update(float dt) {
        setPosition(b2body.getPosition().x-getWidth()/2, b2body.getPosition().y-getHeight()/2);
    }

}
