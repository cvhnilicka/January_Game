package com.cormucopiastudios.januarygame.GameEngine.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.cormucopiastudios.januarygame.GameEngine.B2Model;

public class Wall extends Sprite {
    private B2Model parent;
    public Body b2body;

    private Texture texture;

    public Wall(B2Model parent, boolean rightWall, String texture) {
        super((Texture)parent.getAss().manager.get(texture));
        this.parent = parent;
        this.texture = parent.getAss().manager.get(texture);
        createWall(rightWall);

    }


    private void createWall(boolean rightWall) {
        // create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        if (rightWall) {
            bodyDef.position.set((parent.getCamera().viewportWidth / 2), 0);

        } else {
            bodyDef.position.set(-(parent.getCamera().viewportWidth / 2), 0);

        }
        // add it to the world
        b2body = parent.world.createBody(bodyDef);
        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1, parent.getCamera().viewportHeight);
        // create the physical object in our body)
        // without this our body would just be data in the world
        b2body.createFixture(shape, 0.0f);
        // we no longer use the shape object here so dispose of it.
        shape.dispose();

        if (rightWall) {
            setBounds(b2body.getPosition().x, -(parent.getCamera().viewportHeight/2), 7,parent.getCamera().viewportHeight);
            setPosition((parent.getCamera().viewportWidth / 2)-7f, -(parent.getCamera().viewportHeight/2));
        } else {
            setBounds(b2body.getPosition().x, -(parent.getCamera().viewportHeight/2), 5,parent.getCamera().viewportHeight);
            setPosition(b2body.getPosition().x, -(parent.getCamera().viewportHeight/2));
        }
    }

}
