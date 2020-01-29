package com.cormucopiastudios.januarygame.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.cormucopiastudios.januarygame.JanuaryGame;

public class GameOver implements Screen {

    private JanuaryGame parent;
    private Stage stage;
    private Skin skin;


    public GameOver(JanuaryGame parent) {
        this.parent = parent;
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("skin/shade/uiskin.json"));
    }

    @Override
    public void show() {
        stage.clear();
        Gdx.input.setInputProcessor(stage);
        Label gameOver = new Label("Game Over", skin);
        Table table = new Table();
        table.setFillParent(true);
        table.add(gameOver);
        table.row();

        // Leaderboard Button
        ImageButton leaderboard = new ImageButton(new TextureRegionDrawable(
                new TextureRegion((Texture)parent.assMan.manager.get(parent.assMan.leaderboard))));
        table.add(leaderboard).height(Value.percentHeight(0.25f, table)).fillX().uniform();
        table.row().pad(10,0,10,0);
        leaderboard.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Game Over", "Leaderboard");
                parent.changeScreen(JanuaryGame.LEADERBOARD);
            }
        });

        // Leaderboard Button
        ImageButton exit = new ImageButton(new TextureRegionDrawable(
                new TextureRegion((Texture)parent.assMan.manager.get(parent.assMan.exit))) );
        table.add(exit).height(Value.percentHeight(0.25f, table)).fillX().uniform();
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });


        // Return Button
        ImageButton returnButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion((Texture)parent.assMan.manager.get(parent.assMan.returnButton))));
        returnButton.setDebug(true);
        returnButton.setWidth(stage.getWidth()/6);
        returnButton.setHeight(stage.getHeight()/6);
        returnButton.top();
        returnButton.setBounds(0,stage.getHeight()-returnButton.getHeight(),returnButton.getWidth(),returnButton.getHeight());
        returnButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(JanuaryGame.MENU);
            }
        });
        stage.addActor(returnButton);



        stage.addActor(table);

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
