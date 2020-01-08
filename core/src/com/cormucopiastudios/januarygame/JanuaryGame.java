package com.cormucopiastudios.januarygame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cormucopiastudios.januarygame.Views.LoadingScreen;
import com.cormucopiastudios.januarygame.Views.Menu;

public class JanuaryGame extends Game {


	private Menu menu;
	public final static int MENU = 0;

	private LoadingScreen loadingScreen;
	public final static int LOADING = -1;
	
	@Override
	public void create () {
		loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
	}


	/*********************************************************************************************
	 * BEGIN CUSTOM METHODS
	 * ******************************************************************************************/


	public void changeScreen(int screen) {
		switch (screen) {
			case MENU: if (menu == null) menu = new Menu(this);
						this.setScreen(menu);
						break;
		}
	}

	/*********************************************************************************************
	 * END CUSTOM METHODS
	 * ******************************************************************************************/





	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

	}
}
