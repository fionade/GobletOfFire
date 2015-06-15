package at.tugraz.mobileapps.tournamentplanner;

/**
 * Created by fiona on 15.06.15.
 */
public class Encounter {

    public static final int PLAYER_1_WINS = 0;
    public static final int PLAYER_2_WINS = 2;
    public static final int TIE = 1;

    private Player player1;
    private Player player2;

    private int score;
    private boolean finished;

    public Encounter(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

        this.score = 1;
        this.finished = false;
    }

    public void setScore(int score) {
        this.score = score;
        this.finished = true;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
