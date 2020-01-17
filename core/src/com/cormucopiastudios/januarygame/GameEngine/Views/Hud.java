package com.cormucopiastudios.januarygame.GameEngine.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cormucopiastudios.januarygame.GameEngine.GameClass;
import com.cormucopiastudios.januarygame.GameEngine.PlayScreen;
import com.cormucopiastudios.januarygame.JanuaryGame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Hud implements Disposable {

    public Stage stage;
    private Viewport viewport;

    private PlayScreen parent;

    private int score;
    Image[] scoreImages;


    public Hud(SpriteBatch sb, PlayScreen parent) {
        this.parent = parent;
        viewport = new FitViewport(parent.getGamecam().viewportWidth,
                parent.getGamecam().viewportHeight, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);  // this table now is the size of the page (parent)
        scoreSetup();
        table.add(scoreImages[3]).expandX().padTop(5);
        table.add(scoreImages[2]).expandX().padTop(5);
        table.add(scoreImages[1]).expandX().padTop(5);
        table.add(scoreImages[0]).expandX().padTop(5);

        stage.addActor(table);
    }

    private void scoreSetup() {
        scoreImages = new Image[4];
        scoreImages[0] = new Image();
        scoreImages[1] = new Image();
        scoreImages[2] = new Image();
        scoreImages[3] = new Image();
        this.score = 0;
        this.updateScore(1264);
    }

    public void updateScore(int score) {
        Gdx.app.log("HUD : Update Score", String.valueOf(score));
        this.score = score;
        LinkedList<Integer> digits = new LinkedList<>();
        while(score > 0) {
            digits.add(score%10);
            score/=10;
        }

        int pad = 4-digits.size();
        if (digits.size() < 4) {
            Gdx.app.log("HUD : Update Score pad", String.valueOf(pad));
            for (int j = 0; j < pad; j++) {
                Gdx.app.log("Loop: ", String.valueOf(j));
                scoreImages[3-j] = new Image((Texture)parent.getAssMan()
                        .manager.get(parent.getAssMan().zero));
            }
        }
        Gdx.app.log("Digits size", String.valueOf(digits.size()));
        Gdx.app.log("Pad size", String.valueOf(pad));
        int i = 4-pad;
        Gdx.app.log("digits", String.valueOf(digits));
        while(!digits.isEmpty()) {
            switch (digits.pop()) {

                case 1: scoreImages[i-1]= new Image((Texture)parent.getAssMan()
                        .manager.get(parent.getAssMan().one));
                    break;
                case 2: scoreImages[i-1]= new Image((Texture)parent.getAssMan()
                        .manager.get(parent.getAssMan().two));
                    break;
                case 3: scoreImages[i-1]= new Image((Texture)parent.getAssMan()
                        .manager.get(parent.getAssMan().three));
                    break;
                case 4: scoreImages[i-1]= new Image((Texture)parent.getAssMan()
                        .manager.get(parent.getAssMan().four));
                    break;
                case 5: scoreImages[i-1]= new Image((Texture)parent.getAssMan()
                        .manager.get(parent.getAssMan().five));
                    break;
                case 6: scoreImages[i-1]= new Image((Texture)parent.getAssMan()
                        .manager.get(parent.getAssMan().six));
                    break;
                case 7: scoreImages[i-1]= new Image((Texture)parent.getAssMan()
                        .manager.get(parent.getAssMan().seven));
                    break;
                case 8: scoreImages[i-1]= new Image((Texture)parent.getAssMan()
                        .manager.get(parent.getAssMan().eight));
                    break;
                case 9: scoreImages[i-1]= new Image((Texture)parent.getAssMan()
                        .manager.get(parent.getAssMan().nine));
                    break;
                    default: scoreImages[i-1] = new Image((Texture)parent.getAssMan()
                            .manager.get(parent.getAssMan().zero));
            }
            i--;
        }

    }


    @Override
    public void dispose() {
        stage.dispose();
    }
}
