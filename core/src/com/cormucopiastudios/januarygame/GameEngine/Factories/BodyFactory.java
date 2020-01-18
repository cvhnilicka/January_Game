package com.cormucopiastudios.januarygame.GameEngine.Factories;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.cormucopiastudios.januarygame.GameEngine.GameClass;

public class BodyFactory {

    public enum FIXTURE_TYPE {STEEL, WOOD, RUBBER, STONE}

    private World world;
    private static BodyFactory thisInstance;


    private final float DEGTORAD = 0.0174533f;


    private BodyFactory(World world) {
        this.world = world;
    }

    // singleton dawg
    public static BodyFactory getInstance(World world) {
        if (thisInstance == null)
            thisInstance = new BodyFactory(world);
        return thisInstance;
    }


    static public FixtureDef makeFixture(FIXTURE_TYPE type, Shape shape) {
        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        switch (type) {
            case STEEL:
                fdef.density = 1f;
                fdef.friction = 0.3f;
                fdef.restitution = 0.1f;
                fdef.filter.categoryBits = GameClass.PLAYER_BIT;
                fdef.filter.maskBits = GameClass.ASTEROID_BIT | GameClass.PLATFORM_BIT;
                break;
            case WOOD:
                fdef.density = 0.5f;
                fdef.friction = 0.7f;
                fdef.restitution = 0.3f;
                break;
            case RUBBER:
                fdef.density = 1f;
                fdef.friction = 0f;
                fdef.restitution = 1f;
                break;
            case STONE:
                fdef.density = 1f;
                fdef.friction = 0.9f;
                fdef.restitution = 0.01f;
                fdef.filter.categoryBits = GameClass.ASTEROID_BIT;
                fdef.filter.maskBits = GameClass.ASTEROID_BIT | GameClass.PLATFORM_BIT | GameClass.PLAYER_BIT;
            default:
                fdef.density = 7f;
                fdef.friction = 0.5f;
                fdef.restitution = 0.3f;
        }
        return fdef;
    }


    /**
     *
     * Begin Circular Bodies
     * */
    public Body makeCirclePolyBody(float posx, float posy, float radius, FIXTURE_TYPE mat,
                                   BodyDef.BodyType bodyType, boolean fixedRotation) {
        BodyDef bdef = new BodyDef();
        bdef.type = bodyType;
        bdef.position.x = posx;
        bdef.position.y = posy;
        bdef.fixedRotation = fixedRotation;

        // create body for the def yo
        Body newBod = world.createBody(bdef);
        CircleShape shape = new CircleShape();
        shape.setRadius(radius / 2);
        newBod.createFixture(makeFixture(mat, shape));
        shape.dispose();
        return newBod;
    }

    public Body makeCirclePolyBody(float posx, float posy, float radius, FIXTURE_TYPE mat,
                                   BodyDef.BodyType bodyType) {
        return makeCirclePolyBody(posx,posy,radius,mat,bodyType,false);
    }

    public Body makeCirclePolyBody(float posx, float posy, float radius, FIXTURE_TYPE mat) {
        return makeCirclePolyBody(posx, posy, radius, mat, BodyDef.BodyType.DynamicBody);
    }

    /**
     *
     * Begin Rectangle Bodies
     * */
    public Body makeBoxPolyBody(float posx, float posy, float width, float height, FIXTURE_TYPE mat,
                                BodyDef.BodyType bodyType, boolean fixedRotation) {
        // create bdef
        BodyDef bdef = new BodyDef();
        bdef.type = bodyType;
        bdef.position.x = posx;
        bdef.position.y = posy;
        bdef.fixedRotation = fixedRotation;

        // create new bod to attach bdefff
        Body box = world.createBody(bdef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width/2,height/2);
        box.createFixture(makeFixture(mat,shape));
        shape.dispose();
        return box;

    }
    public Body makeBoxPolyBody(float posx, float posy, float width, float height,FIXTURE_TYPE mat,
                                BodyDef.BodyType bodyType){
        return makeBoxPolyBody(posx, posy, width, height, mat, bodyType, false);
    }


    /**
     *
     * Begin Poly Bodies
     * */

    /**
     * This can make convex polygons
     * */
    public Body makePolygonShapeBody(Vector2[] vertices, float posx, float posy, FIXTURE_TYPE mat,
                                     BodyDef.BodyType bodyType) {
        BodyDef bdef = new BodyDef();
        bdef.type = bodyType;
        bdef.position.x = posx;
        bdef.position.y = posy;
        Body polyBody = world.createBody(bdef);

        PolygonShape polygon = new PolygonShape();
        polygon.set(vertices);
        polyBody.createFixture(makeFixture(mat,polygon));
        polygon.dispose();

        return polyBody;
    }

    /**
     * This can make cone shaped bodys. This is useful to use as a sensor to act as an enemies
     * field of view.
     * */
    public void makeConeSensor(Body body, float size) {
        FixtureDef fdef = new FixtureDef();
        fdef.isSensor = true; // will uncomment later

        PolygonShape polygon = new PolygonShape();
        float radius = size;
        Vector2[] vertices = new Vector2[5];
        vertices[0] = new Vector2(0,0);
        for (int i = 2; i < 6; i++) {
            float angle = (float)(i/6.0*145*DEGTORAD); // convert to radians
            vertices[i-1] = new Vector2(radius*((float)Math.cos(angle)),
                    radius*((float)Math.sin(angle)));
        }

        polygon.set(vertices);
        fdef.shape = polygon;
        body.createFixture(fdef);
        polygon.dispose();

    }


    public void makeAllFixturesSensors(Body bod) {
        for (Fixture fix : bod.getFixtureList()) {
            fix.setSensor(true);
        }
    }





}
