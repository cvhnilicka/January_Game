package com.cormucopiastudios.januarygame.GameEngine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cormucopiastudios.januarygame.GameEngine.Controller.KeyboardController;
import com.cormucopiastudios.januarygame.GameEngine.Loader.B2AssetManager;

public class PlayScreen implements Screen {

    private GameClass game;

    // camera shit
    private OrthographicCamera gamecam;
    private Viewport viewport;
    private Box2DDebugRenderer debugRenderer;
    private KeyboardController controller;
    private B2AssetManager assMan;
    private B2Model model;

    private SpriteBatch batch;


    private Sprite backgroundSprite;

    private Texture playerTex;

    public PlayScreen(GameClass game) {
        this.game = game;
        batch = new SpriteBatch();
        gamecam = new OrthographicCamera(32,24);
        batch.setProjectionMatrix(gamecam.combined);
        controller = new KeyboardController();
        this.assMan = this.game.getParent().assMan;
        assMan.queueAddImages();
        assMan.manager.finishLoading();
        backgroundSprite = new Sprite((Texture)assMan.manager.get(assMan.background));
        backgroundSprite.setBounds(-gamecam.viewportWidth/2,-gamecam.viewportHeight/2,gamecam.viewportWidth,gamecam.viewportHeight);
        backgroundSprite.setPosition(-gamecam.viewportWidth/2,-gamecam.viewportHeight/2);
        playerTex = assMan.manager.get(assMan.player, Texture.class);
        model = new B2Model(this);
        debugRenderer = new Box2DDebugRenderer(true,true,true,true,true,true);
    }

    public OrthographicCamera getGamecam() { return gamecam; }

    public KeyboardController getController() { return this.controller; }

    public B2AssetManager getAssMan() {
        return assMan;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(controller);
    }

    @Override
    public void render(float delta) {

        model.logicStep(delta);
        Gdx.gl.glClearColor(0f,0f,0f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        debugRenderer.render(model.world, gamecam.combined);
//        Gdx.app.log("Render", String.valueOf(backgroundSprite.getY()));
//        if (backgroundSprite.getY() < -gamecam.viewportHeight)
//            backgroundSprite.setPosition(-gamecam.viewportWidth/2,-gamecam.viewportHeight/2);
//        else
//            backgroundSprite.setPosition(backgroundSprite.getX(), backgroundSprite.getY()-1);
        batch.begin();
        backgroundSprite.draw(batch);
        model.draw(batch);
        batch.end();

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
        batch.dispose();
    }
}
