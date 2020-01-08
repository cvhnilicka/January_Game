package com.cormucopiastudios.januarygame.Views;

import com.badlogic.gdx.Screen;
import com.cormucopiastudios.januarygame.JanuaryGame;

public class LoadingScreen implements Screen {

    private JanuaryGame parent;


    public LoadingScreen(JanuaryGame parent) {
        this.parent = parent;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        parent.changeScreen(JanuaryGame.MENU);
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
