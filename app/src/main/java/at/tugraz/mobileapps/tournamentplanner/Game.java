package at.tugraz.mobileapps.tournamentplanner;

import java.util.ArrayList;

/**
 * Created by fiona on 14.06.15.
 */
public class Game {

    // TODO enum tournament style

    private ArrayList<Player> players;
    private int id;

    public Game(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

}
