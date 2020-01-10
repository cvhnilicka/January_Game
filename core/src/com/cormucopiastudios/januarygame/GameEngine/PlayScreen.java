package com.cormucopiastudios.januarygame.GameEngine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;

public class PlayScreen implements Screen {

    private GameClass game;

    // camera shit
    private OrthographicCamera gamecam;
    private Viewport viewport;
    private Box2DDebugRenderer debugRenderer;

    private B2Model model;

    public PlayScreen() {
        gamecam = new OrthographicCamera(32,24);
        model = new B2Model(this);
        debugRenderer = new Box2DDebugRenderer(true,true,true,true,true,true);
    }

    public OrthographicCamera getGamecam() { return gamecam; }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        model.logicStep(delta);
        Gdx.gl.glClearColor(0f,0f,0f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        debugRenderer.render(model.world, gamecam.combined);

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
