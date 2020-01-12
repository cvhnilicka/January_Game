package com.cormucopiastudios.januarygame.GameEngine.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.cormucopiastudios.januarygame.GameEngine.B2Model;
import com.cormucopiastudios.januarygame.GameEngine.Factories.BodyFactory;

public class Player extends Sprite {

    private B2Model parent;
    public Body b2body;

    private Texture texture;


    public Player(B2Model parent) {
        super((Texture)parent.getAss().manager.get(parent.getAss().player));
        this.parent = parent;
        texture = parent.getAss().manager.get(parent.getAss().player);
        BodyFactory bFact = BodyFactory.getInstance(parent.world);
        b2body = bFact.makeBoxPolyBody(0,-12,2f,4f,
                BodyFactory.FIXTURE_TYPE.STEEL, BodyDef.BodyType.DynamicBody, true);
        b2body.setGravityScale(0f);
        setBounds(b2body.getPosition().x, b2body.getPosition().y, 2, 4);
        setPosition(b2body.getPosition().x, b2body.getPosition().y);

    }
    public void update(float dt) {
        setPosition(b2body.getPosition().x-getWidth()/2, b2body.getPosition().y-getHeight()/2);
    }

}
