package at.tugraz.mobileapps.tournamentplanner;

import java.util.HashMap;

/**
 * Created by fiona on 11.06.15.
 */
public class Player implements Comparable<Player> {

    private String name;
    private HashMap<Integer, Integer> wins;

    public Player(String name) {
        this.name = name;
        wins = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Player player) {
        return name.compareTo(player.name);
    }

    public void changeScore(int gameId, int difference) {
        if (wins.containsKey(gameId)) {
            wins.put(gameId, wins.get(gameId) + difference);
        }
        else {
            wins.put(gameId, difference);
        }
    }

    public int getScoreInGame(int gameId) {
        if (wins.containsKey(gameId)) {
            return wins.get(gameId);
        }
        return 0;
    }
}
