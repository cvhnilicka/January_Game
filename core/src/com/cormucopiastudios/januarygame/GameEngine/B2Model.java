package com.cormucopiastudios.januarygame.GameEngine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.cormucopiastudios.januarygame.GameEngine.Controller.KeyboardController;
import com.cormucopiastudios.januarygame.GameEngine.Factories.BodyFactory;

import java.util.Random;

public class B2Model {
    public World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    private Body bodyd;
    private Body bodys;
    private Body player;

    private Asteroid[] roids = new Asteroid[3];

    private PlayScreen parent;

    private KeyboardController controller;


    public B2Model(PlayScreen parent) {
        this.parent = parent;
        this.world = new World(new Vector2(0,-10), true);
        this.controller = parent.getController();
        this.camera = parent.getGamecam();
        createWalls();
        createObject();
        createMovingObject();

        BodyFactory bFact = BodyFactory.getInstance(world);

        createAsteroids();
//        bFact.makeCirclePolyBody(1,1,2,BodyFactory.FIXTURE_TYPE.RUBBER);
//        bFact.makeCirclePolyBody(4,1,2,BodyFactory.FIXTURE_TYPE.STEEL);
//        bFact.makeCirclePolyBody(-4,2,2,BodyFactory.FIXTURE_TYPE.STONE);
//        bFact.makeCirclePolyBody(-2,1.75f,2,BodyFactory.FIXTURE_TYPE.RUBBER);
//        bFact.makeCirclePolyBody(9,3f,2,BodyFactory.FIXTURE_TYPE.STEEL);
//        bFact.makeCirclePolyBody(-4,-1,2,BodyFactory.FIXTURE_TYPE.STONE);
//
//        bFact.makeBoxPolyBody(-5,3,1,1, BodyFactory.FIXTURE_TYPE.STONE, BodyDef.BodyType.DynamicBody);
//        bFact.makeBoxPolyBody(5,3,1,1, BodyFactory.FIXTURE_TYPE.STONE, BodyDef.BodyType.DynamicBody);
    }


    public void logicStep(float dt) {

        if (controller.right) {
            player.applyForceToCenter(new Vector2(10,0),true);
        } else if (controller.left) {
            player.applyForceToCenter(new Vector2(-10,0),true);
        } else if (controller.up) {
            player.applyForceToCenter(new Vector2(0,10),true);
        } else if (controller.down) {
            player.applyForceToCenter(new Vector2(0,-10),true);
        }

        for(Asteroid as : this.roids) {
            as.updateY();
        }

        world.step(dt,3,3);
    }

    public OrthographicCamera getGamecam() { return this.camera; }

    private void createAsteroids() {
        float leftBound = -(this.parent.getGamecam().viewportWidth/2)+.5f;
        float rightBound = (this.parent.getGamecam().viewportWidth/2)-.5f;
        Random ran = new Random();
        float xPos;
        float yPos;

        for (int i = 0; i < this.roids.length; i++) {
            xPos = ran.nextFloat() * (rightBound - leftBound + 1.0f) + leftBound;
            yPos = ran.nextFloat() * (10 - 5 + 1.0f) + 5;
            roids[i] = new Asteroid(this, xPos, yPos);
        }
    }

    private void createObject() {
        // create a new body def
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set(-7,0);

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


    private void createWalls() {
        leftWall();
        rightWall();
    }

    private void rightWall() {
        // create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((parent.getGamecam().viewportWidth/2), 0);
        // add it to the world
        bodys = world.createBody(bodyDef);
        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1, 50);
        // create the physical object in our body)
        // without this our body would just be data in the world
        bodys.createFixture(shape, 0.0f);
        // we no longer use the shape object here so dispose of it.
        shape.dispose();
    }

    private void leftWall() {
        // create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(-(parent.getGamecam().viewportWidth/2), 0);
        // add it to the world
        bodys = world.createBody(bodyDef);
        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1, 50);
        // create the physical object in our body)
        // without this our body would just be data in the world
        bodys.createFixture(shape, 0.0f);
        // we no longer use the shape object here so dispose of it.
        shape.dispose();
    }

    public OrthographicCamera getCamera() {
        return this.camera;
    }

    private void createMovingObject(){

        //create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0,-12);


        // add it to the world
        player = world.createBody(bodyDef);
        player.setGravityScale(0.0f);

        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);

        // set the properties of the object ( shape, weight, restitution(bouncyness)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        // create the physical object in our body)
        // without this our body would just be data in the world
        player.createFixture(shape, 0.0f);

        // we no longer use the shape object here so dispose of it.
        shape.dispose();

        player.setLinearVelocity(0, 0);
    }
}
