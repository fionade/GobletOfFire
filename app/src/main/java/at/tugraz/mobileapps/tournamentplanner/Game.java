package at.tugraz.mobileapps.tournamentplanner;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by fiona on 14.06.15.
 */
public class Game {

    private static final int KO_TOURNAMENT = 0;

    private int gameId;

    private ArrayList<Player> players;

    private ArrayList<ArrayList<Encounter>> encounters;

    public Game(ArrayList<Player> players, int gameId) {
        this.players = players;
        this.gameId = gameId;
        Collections.shuffle(players);

        encounters = new ArrayList<>();
        encounters.add(new ArrayList<Encounter>());
        for (int i = 0; i < players.size() - 1; i += 2) {
            // TODO check logic
            encounters.get(0).add(new Encounter(players.get(i), players.get(i + 1), gameId));
        }

        Collections.sort(players);

        // TODO subsequent rounds
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<ArrayList<Encounter>> getEncounters() {
        return encounters;
    }

}
