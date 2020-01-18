package com.cormucopiastudios.januarygame.GameEngine.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.Map;

public class DataController {

    private static DataController thisInstance;
    private Preferences leaderBoard;
    private Preferences prefs;

    public DataController() {
        prefs = Gdx.app.getPreferences("preferences");
        leaderBoard = Gdx.app.getPreferences("leaderboard");
        Gdx.app.log("DataController", "Created");

    }

    private void printLeaderboard(){
        for (String key: leaderBoard.get().keySet()) {
            Gdx.app.log("LEADERBOARD", "Key: " + key + " Score: " + leaderBoard.getString(key));
        }
    }

    public Preferences getLeaderBoard() { return this.leaderBoard; }

    public Preferences getPrefs() {
        return prefs;
    }

    public static DataController getInstance() {
        if (thisInstance == null) thisInstance = new DataController();
        return thisInstance;
    }

    public void saveScore(int score) {
        int index = this.leaderBoard.get().size();
        leaderBoard.putInteger(String.valueOf(index),score);
        leaderBoard.flush();
        Gdx.app.log("DataController", "Saved " + leaderBoard.getString(String.valueOf(index)));
    }
}
