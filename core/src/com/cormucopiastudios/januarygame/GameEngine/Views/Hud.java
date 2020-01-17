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
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cormucopiastudios.januarygame.GameEngine.GameClass;
import com.cormucopiastudios.januarygame.GameEngine.PlayScreen;
import com.cormucopiastudios.januarygame.JanuaryGame;

import java.util.ArrayList;
import java.util.Collections;
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
        this.updateScore(0);
    }

    public void updateScore(int score) {

        if (score == 0) {
            scoreImages[0] = new Image((Texture)parent.getAssMan()
                    .manager.get(parent.getAssMan().zero));
            scoreImages[1] = new Image((Texture)parent.getAssMan()
                    .manager.get(parent.getAssMan().zero));
            scoreImages[2] = new Image((Texture)parent.getAssMan()
                    .manager.get(parent.getAssMan().zero));
            scoreImages[3] = new Image((Texture)parent.getAssMan()
                    .manager.get(parent.getAssMan().zero));
            return;
        }
        Gdx.app.log("HUD : Update Score", String.valueOf(score));
        this.score = score;
        LinkedList<Integer> digits = new LinkedList<>();
        while(score > 0) {
            digits.add(score%10);
            score/=10;
        }

        int pad = 4-digits.size();
        Collections.reverse(digits);
//        if (digits.size() < 4) {
//            for (int j = 0; j < pad; j++) {
//                Gdx.app.log("Setting X position to Zero: ", String.valueOf(3-j));
//                scoreImages[3-j] = new Image((Texture)parent.getAssMan()
//                        .manager.get(parent.getAssMan().zero));
//            }
//        }
        Gdx.app.log("Digits size", String.valueOf(digits.size()));
        Gdx.app.log("Pad size", String.valueOf(pad));
        int i = 4-pad;
        Gdx.app.log("digits", String.valueOf(digits));
        while(!digits.isEmpty()) {
            Gdx.app.log("Setting Postiion X to POP", String.valueOf(i-1));
            switch (digits.pop()) {
                case 1:
                scoreImages[i-1].setDrawable(new SpriteDrawable(new Sprite((Texture)parent.getAssMan()
                        .manager.get(parent.getAssMan().one))));
                Gdx.app.log("FUCK", "SET 111111111111");
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
