package com.cormucopiastudios.januarygame.GameEngine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.cormucopiastudios.januarygame.GameEngine.Factories.BodyFactory;

public class B2Model {
    public World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    private Body bodyd;
    private Body bodys;
    private Body bodyk;


    public B2Model() {
        this.world = new World(new Vector2(0,-10), true);
        createFloor();
        createObject();
        createMovingObject();

        BodyFactory bFact = BodyFactory.getInstance(world);
        bFact.makeCirclePolyBody(1,1,2,BodyFactory.FIXTURE_TYPE.RUBBER);
        bFact.makeCirclePolyBody(4,1,2,BodyFactory.FIXTURE_TYPE.STEEL);
        bFact.makeCirclePolyBody(-4,1,2,BodyFactory.FIXTURE_TYPE.STONE);

        bFact.makeBoxPolyBody(-5,3,1,1, BodyFactory.FIXTURE_TYPE.STONE, BodyDef.BodyType.DynamicBody);
        bFact.makeBoxPolyBody(5,3,1,1, BodyFactory.FIXTURE_TYPE.STONE, BodyDef.BodyType.DynamicBody);
    }


    public void logicStep(float dt) {
        world.step(dt,3,3);
    }

    private void createObject() {
        // create a new body def
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(0,0);

        // add it to the world
        bodyd = world.createBody(bdef);

        // set the shape
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);

        // set the properties of the object (shape, weight, restitution (bouncyness))
        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = 1f;

        // create the physical object in our body
        // without this our body would just be data in the world, not a literal body
        bodyd.createFixture(fdef); // might need to change later
        shape.dispose();
    }

    private void createFloor() {
        // create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, -10);
        // add it to the world
        bodys = world.createBody(bodyDef);
        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50, 1);
        // create the physical object in our body)
        // without this our body would just be data in the world
        bodys.createFixture(shape, 0.0f);
        // we no longer use the shape object here so dispose of it.
        shape.dispose();
    }

    private void createMovingObject(){

        //create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(0,-12);


        // add it to the world
        bodyk = world.createBody(bodyDef);

        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);

        // set the properties of the object ( shape, weight, restitution(bouncyness)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        // create the physical object in our body)
        // without this our body would just be data in the world
        bodyk.createFixture(shape, 0.0f);

        // we no longer use the shape object here so dispose of it.
        shape.dispose();

        bodyk.setLinearVelocity(0, 0.75f);
    }
}
