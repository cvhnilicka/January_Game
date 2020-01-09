package com.cormucopiastudios.januarygame.GameEngine.Factories;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class BodyFactory {

    public enum FIXTURE_TYPE {STEEL, WOOD, RUBBER, STONE}

    private World world;
    private static BodyFactory thisInstance;


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



}
