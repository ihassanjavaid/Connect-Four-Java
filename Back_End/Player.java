package Back_End;

public class Player {
    private String playerName, playerMarker;
    private int playerRow, playerColumn;

    public Player (String playerName, String playerMarker) {
        this.playerName = playerName;
        this.playerMarker = playerMarker;
    }

    public void setPlayerCoordinates (int playerRow, int playerColumn) {
        this.playerRow = playerRow;
        this.playerColumn = playerColumn;
    }


    public String getPlayerMarker () {
        return this.playerMarker;
    }

    public int getPlayerRow () {
        return this.playerRow;
    }

    public int getPlayerColumn () {
        return this.playerColumn;
    }

    public String toString () {
        return "Player name: " + playerName + "\nPlayer marker: " + playerMarker;
    }
}
