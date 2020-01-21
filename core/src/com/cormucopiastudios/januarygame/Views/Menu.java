package com.cormucopiastudios.januarygame.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.cormucopiastudios.januarygame.GameEngine.GameClass;
import com.cormucopiastudios.januarygame.JanuaryGame;

public class Menu implements Screen {
    private JanuaryGame parent;
    private Stage stage;
    private Skin skin;

    public Menu(JanuaryGame parent) {
        this.parent = parent;
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        // set input
        Gdx.input.setInputProcessor(stage);

        // create table menu
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        table.setBounds(0,0, GameClass.V_WITDH, GameClass.V_HEIGHT/2);
        stage.addActor(table);

//        skin = new Skin(Gdx.files.internal("skin/glassyui/glassy-ui.json"));
        skin = new Skin(Gdx.files.internal("skin/shade/uiskin.json"));
//        skin = new Skin(Gdx.files.internal("skin/neutralizer/neutralizer-ui.json"));

        // create and add buttons
//        TextButton newGame = new TextButton("New Game", skin);
//        TextButton leaderboard = new TextButton("Leaderboard", skin);
//        TextButton exit = new TextButton("Exit", skin);

        ImageButton newGame = new ImageButton(new TextureRegionDrawable(new TextureRegion((Texture)parent.assMan.manager.get(parent.assMan.newgame))) );
        ImageButton leaderboard = new ImageButton(new TextureRegionDrawable(new TextureRegion((Texture)parent.assMan.manager.get(parent.assMan.leaderboard))) );
        ImageButton exit = new ImageButton(new TextureRegionDrawable(new TextureRegion((Texture)parent.assMan.manager.get(parent.assMan.exit))) );
//        newGame.setScale(0.25f);
//        leaderboard.setScale(0.25f);
//        exit.setScale(0.25f);
//

        table.add(newGame).height(Value.percentHeight(0.30f, table)).fillX().uniform();
        table.row().pad(10,0,10,0);
        table.add(leaderboard).height(Value.percentHeight(0.30f, table)).fillX().uniform();
        table.row().pad(10,0,10,0);
        table.add(exit).height(Value.percentHeight(0.30f, table)).fillX().uniform();


        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(JanuaryGame.GAME);
            }
        });

        leaderboard.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(JanuaryGame.LEADERBOARD);
            }
        });

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

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
        // update the stage (scene) described above to fit the new resized window
        stage.getViewport().update(width,height, true);
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
