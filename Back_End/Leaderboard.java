package Back_End;

public class Leaderboard {
    private String playerOneName, playerTwoName, winner, gameTime;

    public Leaderboard (String playerOneName, String playerTwoName) {
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
        this.winner = "None";
    }

    public void setWinner (String winner) {
        this.winner = winner;
    }

    public void setGameTime (String gameTime) {
        this.gameTime = gameTime;
    }

    public String toString () {
        String leaderBoard = "Player One: " + playerOneName;
        leaderBoard += "\nPlayer two: " + playerTwoName;
        leaderBoard += "\nGame winner: " + winner;
        leaderBoard += "\n" + gameTime;

        return leaderBoard;
    }

}
