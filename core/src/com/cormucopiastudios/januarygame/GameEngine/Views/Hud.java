package com.cormucopiastudios.januarygame.GameEngine.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cormucopiastudios.januarygame.GameEngine.GameClass;
import com.cormucopiastudios.januarygame.GameEngine.PlayScreen;
import com.cormucopiastudios.januarygame.JanuaryGame;

import java.util.Collections;
import java.util.LinkedList;

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
        table.setDebug(true);
        table.top();
//        table.setFillParent(true);  // this table now is the size of the page (parent)
        scoreSetup();
        table.setHeight(2f);
        table.add(scoreImages[3]).expandX();
        table.add(scoreImages[2]).expandX();
        table.add(scoreImages[1]).expandX();
        table.add(scoreImages[0]).expandX();
        table.row();
//        Gdx.app.log("HUD - viewport.getWorldHeight()", String.valueOf(viewport.getWorldHeight()));
//        Gdx.app.log("HUD - table.getHeight()", String.valueOf(table.getHeight()));
//        Gdx.app.log("HUD - table.getHeight()", String.valueOf(table.getRowHeight(0)));
//        Gdx.app.log("HUD - scoreImages[0].getHeight()", String.valueOf(scoreImages[0].getHeight()));
//        Gdx.app.log("HUD - viewport.getWorldHeight()-table.getHeight()-scoreImages[0].getHeight()", String.valueOf(viewport.getWorldHeight()-table.getHeight()-scoreImages[0].getHeight()));
        table.setBounds(0,viewport.getWorldHeight()-table.getHeight()-scoreImages[0].getHeight(),
                viewport.getWorldWidth(), 3);
        table.align(Align.center);
//        table.setPosition(0, viewport.getScreenHeight()-table.getHeight());
        stage.addActor(table);
    }

    private void scoreSetup() {
        scoreImages = new Image[4];
        for (int i = 0; i < scoreImages.length; i++) {
            scoreImages[i] = new Image((Texture)parent.getAssMan()
                    .manager.get(parent.getAssMan().zero));
            scoreImages[i].setHeight(3f);
            scoreImages[i].setWidth(1f);
//            scoreImages[i].setScale(.25f);
            scoreImages[i].setDebug(true);
//            scoreImages[i].setBounds(scoreImages[0].getX(),
//                    viewport.getWorldHeight()-4,
//                    4,4);
//            scoreImages[i].
//            scoreImages[i].setScale(0.3f);
        }

        this.score = 0;

    }

    public void updateScore(int score) {
        if (score == 0) {
            return;
        }
//        Gdx.app.log("HUD : Update Score", String.valueOf(score));
        this.score = score;
        LinkedList<Integer> digits = new LinkedList<>();
        while(score > 0) {
            digits.add(score%10);
            score/=10;
        }

        int pad = 4-digits.size();
        Collections.reverse(digits);
//        Gdx.app.log("Digits size", String.valueOf(digits.size()));
//        Gdx.app.log("Pad size", String.valueOf(pad));
        int i = 4-pad;
//        Gdx.app.log("digits", String.valueOf(digits));
        while(!digits.isEmpty()) {
//            Gdx.app.log("Setting Postiion X to POP", String.valueOf(i-1));
            switch (digits.pop()) {
                case 1:
                scoreImages[i-1].setDrawable(new SpriteDrawable(new Sprite((Texture)parent.getAssMan()
                        .manager.get(parent.getAssMan().one))));
                    break;
                case 2: scoreImages[i-1].setDrawable(new SpriteDrawable(new Sprite((Texture)parent.getAssMan()
                        .manager.get(parent.getAssMan().two))));
                    break;
                case 3: scoreImages[i-1].setDrawable(new SpriteDrawable(new Sprite((Texture)parent.getAssMan()
                        .manager.get(parent.getAssMan().three))));
                    break;
                case 4: scoreImages[i-1].setDrawable(new SpriteDrawable(new Sprite((Texture)parent.getAssMan()
                        .manager.get(parent.getAssMan().four))));
                    break;
                case 5: scoreImages[i-1].setDrawable(new SpriteDrawable(new Sprite((Texture)parent.getAssMan()
                        .manager.get(parent.getAssMan().five))));
                    break;
                case 6: scoreImages[i-1].setDrawable(new SpriteDrawable(new Sprite((Texture)parent.getAssMan()
                        .manager.get(parent.getAssMan().six))));
                    break;
                case 7: scoreImages[i-1].setDrawable(new SpriteDrawable(new Sprite((Texture)parent.getAssMan()
                        .manager.get(parent.getAssMan().seven))));
                    break;
                case 8: scoreImages[i-1].setDrawable(new SpriteDrawable(new Sprite((Texture)parent.getAssMan()
                        .manager.get(parent.getAssMan().eight))));
                    break;
                case 9: scoreImages[i-1].setDrawable(new SpriteDrawable(new Sprite((Texture)parent.getAssMan()
                        .manager.get(parent.getAssMan().nine))));
                    break;
                    case 0: scoreImages[i-1].setDrawable(new SpriteDrawable(new Sprite((Texture)parent.getAssMan()
                            .manager.get(parent.getAssMan().zero))));
                    break;
            }
            i--;
        }
        stage.draw();

    }


    @Override
    public void dispose() {
        stage.dispose();
    }
}
