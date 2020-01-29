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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.cormucopiastudios.januarygame.GameEngine.Controller.DataController;
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

        skin = new Skin(Gdx.files.internal("skin/shade/uiskin.json"));

        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        setUpNameInput(table);
        table.row();
        setUpShips(table);
        table.row();



        stage.addActor(table);

    }

    private void setUpShips(Table table) {
        Table shipTable = new Table();

        final Label shipChoice;
        final String prefix = "Current Ship: ";

        if (DataController.getInstance().getShipPref().equals("redShip")) {
            shipChoice = new Label(prefix+"Red Ship", skin);
        } else if (DataController.getInstance().getShipPref().equals("whiteShip")) {
            shipChoice = new Label(prefix+"White Ship", skin);
        } else {
            shipChoice =  new Label("Default Ship: White Ship", skin);
        }


        ImageButton redShip = new ImageButton(new TextureRegionDrawable(new TextureRegion(
                (Texture)parent.assMan.manager.get(parent.assMan.redShip))));

        ImageButton whiteShip = new ImageButton(new TextureRegionDrawable(new TextureRegion(
                (Texture)parent.assMan.manager.get(parent.assMan.whiteShip))));


        shipTable.add(shipChoice);
        shipTable.row();
        shipTable.add(redShip).height(Value.percentHeight(0.1f,table)).width(Value.percentWidth(0.1f,table));
        shipTable.add(whiteShip).height(Value.percentHeight(0.1f,table)).width(Value.percentWidth(0.1f,table));
        shipTable.row();

        redShip.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Ship Pref", "Red Ship");
                DataController.getInstance().saveShipPref("redShip");
                shipChoice.setText(prefix+"Red Ship");
            }
        });

        whiteShip.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Ship Pref", "White Ship");
                DataController.getInstance().saveShipPref("whiteShip");
                shipChoice.setText(prefix+"White Ship");
            }
        });



        table.add(shipTable);
    }

    private void setUpNameInput(Table table) {
        Table nameTable = new Table();

        final Label nameLabel;
        final String prefix = "Name: ";

        if (DataController.getInstance().getNamePref().equals("")) {
            nameLabel = new Label(prefix+"default", skin);
        } else {
            nameLabel = new Label(prefix+DataController.getInstance().getNamePref(),skin);
        }
        final TextField nameInput = new TextField("Enter Name", skin);
        nameInput.setSize(nameLabel.getWidth(),nameLabel.getHeight());

        TextButton saveButton = new TextButton("Save", skin);

        saveButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                DataController.getInstance().saveName(nameInput.getText());
                nameLabel.setText(prefix+nameInput.getText());
            }
        });

        nameTable.add(nameLabel);
        nameTable.row();
        nameTable.add(nameInput);
        nameTable.add(saveButton);
        nameTable.row();

        table.add(nameTable);
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
