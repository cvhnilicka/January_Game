package com.cormucopiastudios.januarygame.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.cormucopiastudios.januarygame.JanuaryGame;

public class PreferencesScreen implements Screen {

    private JanuaryGame parent;
    private Stage stage;
    private Skin skin;

    public PreferencesScreen(JanuaryGame parent) {
        this.parent = parent;
        stage = new Stage(new ScreenViewport());
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        setUpShips(table);
        table.row();

        stage.addActor(table);

    }

    private void setUpShips(Table table) {
        Table shipTable = new Table();

        Image redShip = new Image((Texture)parent.assMan.manager.get(parent.assMan.redShip));
        Image whiteShip = new Image((Texture)parent.assMan.manager.get(parent.assMan.whiteShip));

        shipTable.add(redShip).height(Value.percentHeight(0.1f,table)).width(Value.percentWidth(0.1f,table));
        shipTable.add(whiteShip).height(Value.percentHeight(0.1f,table)).width(Value.percentWidth(0.1f,table));
        shipTable.row();




        table.add(shipTable);
    }

    @Override
    public void render(float delta) {
        // first, we should clear the image before drawing the next one,
        // this is to prevent 'flickering' or 'ghosting'
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Actually render the scene you described in the show() method above.
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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
