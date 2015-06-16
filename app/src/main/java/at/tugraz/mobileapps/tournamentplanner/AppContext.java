package at.tugraz.mobileapps.tournamentplanner;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by fiona on 15.06.15.
 */
public class AppContext {

    private static AppContext instance;


    private ArrayList<Game> games;

    private AppContext() {
        games = new ArrayList<>();
    }

    public static synchronized AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public int getGameCount() {
        return games.size();
    }
}
