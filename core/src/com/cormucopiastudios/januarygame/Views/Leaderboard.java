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
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.cormucopiastudios.januarygame.GameEngine.Controller.DataController;
import com.cormucopiastudios.januarygame.GameEngine.GameClass;
import com.cormucopiastudios.januarygame.JanuaryGame;

public class Leaderboard implements Screen {

    private JanuaryGame parent;
    private Stage stage;
    private Skin skin;
    private Image backgroundImage;

    public Leaderboard(JanuaryGame parent) {
        this.parent = parent;
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("skin/shade/uiskin.json"));
    }

    @Override
    public void show() {
        stage.clear();
        Gdx.input.setInputProcessor(stage);
        backgroundSetUp();
        ImageButton returnButton = new ImageButton(new TextureRegionDrawable(
                new TextureRegion((Texture)parent.assMan.manager.get(parent.assMan.returnButton))));
        returnButton.setDebug(true);
        returnButton.setWidth(stage.getWidth()/6);
        returnButton.setHeight(stage.getHeight()/6);
        returnButton.top();
        returnButton.setBounds(0,stage.getHeight()-returnButton.getHeight(),returnButton.getWidth(),returnButton.getHeight());


        stage.addActor(returnButton);




        // create table menu
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);
        ScrollPane scrollPane = new ScrollPane(table);

        Table addTable = new Table();
        addTable.setFillParent(true);
        addTable.setDebug(true);
        addTable.add(scrollPane).fill().expand();
        stage.addActor(addTable);





        returnButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.log("Leaderboard", "return");
                parent.changeScreen(JanuaryGame.MENU);
            }
        });


        addEntries(table);

    }

    private void backgroundSetUp() {
        backgroundImage = new Image((Texture)parent.assMan.manager.get(parent.assMan.plain_background));
        backgroundImage.setBounds(0,0,stage.getWidth(),stage.getHeight());
        stage.addActor(backgroundImage);
    }

    private void addEntries(Table table) {

        // comment/uncomment for stylaized
        for(String key: DataController.getInstance().getLeaderBoard().get().keySet()){
            Label n = new Label(key + "  ::  " + DataController.getInstance().getLeaderBoard().getString(key), skin);
            table.add(n);
            table.row();
        }
        // Need to update this to have a stylized table entry


    }

    private void createRow(Table rowTable, String[] scoreDigits) {
        for (String digit : scoreDigits) {
            switch (Integer.parseInt(digit)) {
                case 1: rowTable.add(new Image( (Texture)
                        parent.assMan.manager.get(parent.assMan.one)
                ));
                break;
                    case 2: rowTable.add(new Image( (Texture)
                            parent.assMan.manager.get(parent.assMan.two)
                    ));
                        break;
                case 3: rowTable.add(new Image( (Texture)
                        parent.assMan.manager.get(parent.assMan.three)
                ));
                    break;
                case 4: rowTable.add(new Image( (Texture)
                        parent.assMan.manager.get(parent.assMan.four)
                ));
                    break;
                case 5: rowTable.add(new Image( (Texture)
                        parent.assMan.manager.get(parent.assMan.five)
                ));
                    break;
                case 6: rowTable.add(new Image( (Texture)
                        parent.assMan.manager.get(parent.assMan.six)
                ));
                    break;
                case 7: rowTable.add(new Image( (Texture)
                        parent.assMan.manager.get(parent.assMan.seven)
                ));
                    break;
                case 8: rowTable.add(new Image( (Texture)
                        parent.assMan.manager.get(parent.assMan.eight)
                ));
                    break;
                    case 9: rowTable.add(new Image( (Texture)
                            parent.assMan.manager.get(parent.assMan.nine)
                    ));
                        break;
                case 0: rowTable.add(new Image( (Texture)
                        parent.assMan.manager.get(parent.assMan.zero)
                ));
                    break;

            }
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
