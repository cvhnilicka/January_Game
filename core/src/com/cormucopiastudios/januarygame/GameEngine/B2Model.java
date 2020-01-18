package com.cormucopiastudios.januarygame.GameEngine;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.cormucopiastudios.januarygame.GameEngine.Controller.KeyboardController;
import com.cormucopiastudios.januarygame.GameEngine.Factories.BodyFactory;
import com.cormucopiastudios.januarygame.GameEngine.Loader.B2AssetManager;
import com.cormucopiastudios.januarygame.GameEngine.Models.Asteroid;
import com.cormucopiastudios.januarygame.GameEngine.Models.Player;
import com.cormucopiastudios.januarygame.GameEngine.Models.Wall;

import java.util.Random;

public class B2Model {
    public World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    private Wall rightWall;
    private Wall leftWall;
    public Player player;

    private int score;

    private Array<Asteroid> roids;

    public final int STARTING_ASTEROIDS = 3;

    private PlayScreen parent;

    private KeyboardController controller;

    private int addNew;


    public B2Model(PlayScreen parent) {
        addNew = 0;
        score = 0;
        this.parent = parent;
        this.world = new World(new Vector2(0,-10), true);
        this.controller = parent.getController();
        this.camera = parent.getGamecam();
        createWalls();

        BodyFactory bFact = BodyFactory.getInstance(world);

        player = new Player(this);

        createAsteroids();

    }

    public B2AssetManager getAss() {
        return parent.getAssMan();
    }


    public void logicStep(float dt) {

        Vector3 mosPos = new Vector3(controller.mouseLoc,0);
        this.getCamera().unproject(mosPos);
        this.player.b2body.setTransform(mosPos.x,mosPos.y, player.b2body.getAngle());
        this.player.update(dt);


        for(Asteroid as : this.roids) {
            as.update(dt);
            if (as.updateY()) {
                this.score += 1;
                this.parent.updateScore(this.score);
                this.addNew += 1;
                if (addNew == 5) addAsteroid();
            }
        }



        world.step(dt,3,3);
    }

    public void draw(Batch batch) {
        this.player.draw(batch);
        for (Asteroid roid: this.roids) {
            roid.draw(batch);
        }
        this.rightWall.draw(batch);
        this.leftWall.draw(batch);
    }

    private void addAsteroid(){
        float leftBound = -(this.parent.getGamecam().viewportWidth/2)+.5f;
        float rightBound = (this.parent.getGamecam().viewportWidth/2)-.5f;
        Random ran = new Random();
        float xPos = ran.nextFloat() * (rightBound - leftBound + 1.0f) + leftBound;;
        float yPos = 20;
        this.roids.add(new Asteroid(this,xPos,yPos));
        addNew = 0;
    }

    private void createAsteroids() {
        this.roids = new Array<Asteroid>();
        float leftBound = -(this.parent.getGamecam().viewportWidth/2)+.5f;
        float rightBound = (this.parent.getGamecam().viewportWidth/2)-.5f;
        Random ran = new Random();
        float xPos;
        float yPos;

        for (int i = 0; i < STARTING_ASTEROIDS; i++) {
            xPos = ran.nextFloat() * (rightBound - leftBound + 1.0f) + leftBound;
            yPos = ran.nextFloat() * (30 - 15 + 1.0f) + 15;
            roids.add(new Asteroid(this, xPos, yPos));
        }
    }



    private void createWalls() {
        leftWall();
        rightWall();
    }

    private void rightWall() {
        rightWall = new Wall(this,true,parent.getAssMan().rightWall);
    }

    private void leftWall() {
        leftWall = new Wall(this,false,parent.getAssMan().leftWall);
    }

    public OrthographicCamera getCamera() {
        return this.camera;
    }

}
