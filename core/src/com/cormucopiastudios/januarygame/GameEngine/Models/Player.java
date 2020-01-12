package com.cormucopiastudios.januarygame.GameEngine.Models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.cormucopiastudios.januarygame.GameEngine.B2Model;
import com.cormucopiastudios.januarygame.GameEngine.Factories.BodyFactory;

public class Player extends Sprite {

    private B2Model parent;
    public Body b2body;

    public Player(B2Model parent) {
        this.parent = parent;
        BodyFactory bFact = BodyFactory.getInstance(parent.world);
        b2body = bFact.makeBoxPolyBody(0,-12,1.5f,3.25f,
                BodyFactory.FIXTURE_TYPE.STEEL, BodyDef.BodyType.DynamicBody);
        b2body.setGravityScale(0f);
    }
}
