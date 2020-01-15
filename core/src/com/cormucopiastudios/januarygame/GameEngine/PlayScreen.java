package com.cormucopiastudios.januarygame.GameEngine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
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


    private Sprite[] backgroundSprites;

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
        backgroundSetUp();
        playerTex = assMan.manager.get(assMan.player, Texture.class);
        model = new B2Model(this);
        debugRenderer = new Box2DDebugRenderer(true,true,true,true,true,true);
    }

    private void backgroundSetUp() {
        backgroundSprites = new Sprite[3];
        backgroundSprites[0] = new Sprite((Texture)assMan.manager.get(assMan.background));
        backgroundSprites[0].setBounds(-gamecam.viewportWidth/2,-gamecam.viewportHeight/2,gamecam.viewportWidth,gamecam.viewportHeight);
        backgroundSprites[0].setPosition(-gamecam.viewportWidth/2,-gamecam.viewportHeight/2);

        backgroundSprites[1] = new Sprite((Texture)assMan.manager.get(assMan.background));
        backgroundSprites[1].setBounds(-gamecam.viewportWidth/2,-gamecam.viewportHeight/2,gamecam.viewportWidth,gamecam.viewportHeight);
        backgroundSprites[1].setPosition(-gamecam.viewportWidth/2,backgroundSprites[0].getY()+gamecam.viewportHeight);

        backgroundSprites[2] = new Sprite((Texture)assMan.manager.get(assMan.background));
        backgroundSprites[2].setBounds(-gamecam.viewportWidth/2,-gamecam.viewportHeight/2,gamecam.viewportWidth,gamecam.viewportHeight);
        backgroundSprites[2].setPosition(-gamecam.viewportWidth/2,backgroundSprites[1].getY()+gamecam.viewportHeight);
    }

    private void drawBackground(Batch batch){
        Gdx.app.log("Background Sprite[2] : POS", String.valueOf(backgroundSprites[2].getY()));
        if (backgroundSprites[0].getY() < -(3*gamecam.viewportHeight/2)) {
            backgroundSprites[0].setPosition(backgroundSprites[0].getX(), backgroundSprites[2].getY()+gamecam.viewportHeight);
            Gdx.app.log("Background Sprite[0] : Switch", String.valueOf(backgroundSprites[0].getY()));
        }
        if (backgroundSprites[1].getY() < -(3*gamecam.viewportHeight/2)) {
            backgroundSprites[1].setPosition(backgroundSprites[1].getX(), backgroundSprites[0].getY()+gamecam.viewportHeight);
            Gdx.app.log("Background Sprite[1] : Switch", String.valueOf(backgroundSprites[1].getY()));
        }
        if (backgroundSprites[2].getY() < -(3*gamecam.viewportHeight/2)) {
            backgroundSprites[2].setPosition(backgroundSprites[2].getX(), backgroundSprites[1].getY()+gamecam.viewportHeight);
            Gdx.app.log("Background Sprite[2] : Switch", String.valueOf(backgroundSprites[2].getY()));
        }
        for (int i = 0; i < backgroundSprites.length; i++) {
            backgroundSprites[i].draw(batch);
            backgroundSprites[i].setPosition(backgroundSprites[i].getX(), backgroundSprites[i].getY()-.33f);
        }

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
        batch.begin();
        drawBackground(batch);
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
