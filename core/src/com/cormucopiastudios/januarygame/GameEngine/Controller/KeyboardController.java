package com.cormucopiastudios.januarygame.GameEngine.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class KeyboardController implements InputProcessor {

    public boolean left, right, up, down;

    public Vector2 mouseLoc = new Vector2(0,-12);


    @Override
    public boolean keyDown(int keycode) {

        Gdx.app.log("Keyboard Controller", "Keydown");

        boolean keyProcessed = false;
        switch (keycode) {
            case Input.Keys
                    .LEFT:
                left=true;
            keyProcessed = true;
            break;
            case Input.Keys
                    .RIGHT:
                right = true;
                keyProcessed = true;
                break;
            case Input.Keys.UP:
                up = true;
                keyProcessed = true;
                break;
            case Input.Keys.DOWN:
                down = true;
                keyProcessed = true;
                break;
        }
        return keyProcessed;
    }

    @Override
    public boolean keyUp(int keycode) {
        boolean keyProcessed = false;
        switch (keycode) {
            case Input.Keys
                    .LEFT:
                left=false;
                keyProcessed = true;
                break;
            case Input.Keys
                    .RIGHT:
                right = false;
                keyProcessed = true;
                break;
            case Input.Keys.UP:
                up = false;
                keyProcessed = true;
                break;
            case Input.Keys.DOWN:
                down = false;
                keyProcessed = true;
                break;
        }
        return keyProcessed;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mouseLoc.x = screenX;
        mouseLoc.y = screenY;
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
