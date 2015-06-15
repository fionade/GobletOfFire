package at.tugraz.mobileapps.tournamentplanner;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by fiona on 14.06.15.
 */
public class Game {

    private static final int KO_TOURNAMENT = 0;

    private ArrayList<Player> players;

    private ArrayList<Encounter> encounters;
    private int id;

    public Game(ArrayList<Player> players) {
        this.players = players;
        Collections.shuffle(players);

        encounters = new ArrayList<>();
        for (int i = 0; i < players.size() - 1; i += 2) {
            // TODO check logic
            encounters.add(new Encounter(players.get(i), players.get(i + 1)));
        }

        Collections.sort(players);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Encounter> getEncounters() {
        return encounters;
    }

}
