package com.cormucopiastudios.januarygame.GameEngine.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.cormucopiastudios.januarygame.GameEngine.B2Model;
import com.cormucopiastudios.januarygame.GameEngine.GameClass;
import com.cormucopiastudios.januarygame.GameEngine.Models.Player;

public class B2ContactListener implements ContactListener {

    private B2Model parent;

    public B2ContactListener(B2Model parent) {
        this.parent = parent;
    }

    @Override
    public void beginContact(Contact contact) {
        // begin to collide
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;
        switch (cDef) {
            case GameClass
                    .PLAYER_BIT | GameClass.ASTEROID_BIT:
                // here i basically want to set a rebound force and apply it to the player

                // also want to save data
                this.parent.saveScore();


        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
