package com.cormucopiastudios.januarygame.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.cormucopiastudios.januarygame.GameEngine.Controller.DataController;
import com.cormucopiastudios.januarygame.JanuaryGame;

public class Leaderboard implements Screen {

    private JanuaryGame parent;
    private Stage stage;
    private Skin skin;

    public Leaderboard(JanuaryGame parent) {
        this.parent = parent;
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {

        // create table menu
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        stage.addActor(table);
        skin = new Skin(Gdx.files.internal("skin/shade/uiskin.json"));
        addEntries(table);

    }

    private void addEntries(Table table) {
        for(String key: DataController.getInstance().getLeaderBoard().get().keySet()){
            TextButton n = new TextButton(key + "  ::  " + DataController.getInstance().getLeaderBoard().getString(key), skin);
            table.add(n);
            table.row();
        }
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
        stage.dispose();
    }
}
