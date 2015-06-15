package at.tugraz.mobileapps.tournamentplanner;

/**
 * Created by fiona on 11.06.15.
 */
public class Player implements Comparable<Player> {

    private String name;

    public Player(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Player player) {
        return name.compareTo(player.name);
    }
}
