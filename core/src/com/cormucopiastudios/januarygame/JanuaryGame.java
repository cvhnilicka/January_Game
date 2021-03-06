package com.cormucopiastudios.januarygame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cormucopiastudios.januarygame.GameEngine.Factories.BodyFactory;
import com.cormucopiastudios.januarygame.GameEngine.GameClass;
import com.cormucopiastudios.januarygame.GameEngine.Loader.B2AssetManager;
import com.cormucopiastudios.januarygame.GameEngine.PlayScreen;
import com.cormucopiastudios.januarygame.Views.GameOver;
import com.cormucopiastudios.januarygame.Views.Leaderboard;
import com.cormucopiastudios.januarygame.Views.LoadingScreen;
import com.cormucopiastudios.januarygame.Views.Menu;
import com.cormucopiastudios.januarygame.Views.PreferencesScreen;

public class JanuaryGame extends Game {

	public B2AssetManager assMan = new B2AssetManager();


	private Menu menu;
	public final static int MENU = 0;

	private LoadingScreen loadingScreen;
	public final static int LOADING = -1;

	private GameClass game;
	public final static int GAME = 2;

	private Leaderboard leaderboard;
	public final static int LEADERBOARD = 3;

	private PreferencesScreen preferences;
	public final static int PREFERENCES = 4;

	private GameOver gameOver;
	public final static int GAMEOVER = 5;
	
	@Override
	public void create () {
		assMan.queueMenuButtons();
		assMan.manager.finishLoading();
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
			case GAME:
				game = new GameClass(this);
			break;
			case LEADERBOARD: if (leaderboard == null) leaderboard = new Leaderboard(this);
			this.setScreen(leaderboard);
			break;
			case PREFERENCES: if (preferences == null) preferences = new PreferencesScreen(this);
			this.setScreen(preferences);
			break;
			case GAMEOVER: if (gameOver == null) gameOver = new GameOver(this);
			this.setScreen(gameOver);
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
