package Back_End;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Judgement {
    private Player player;
    private String winDirection;
    private int [] winList;
    private int counter;
    private long startTime, gameTime;
    private Leaderboard leaderBoard;

    public Judgement () {
        winDirection = "";
        counter = 0;
    }

    public void setPlayer (Player player) {
        this.player = player;
    }

    public void setStartTime (long startTime) {
        this.startTime = startTime;
    }

    public void setLeaderBoard (Leaderboard leaderBoard) {
        this.leaderBoard = leaderBoard;
    }

    public void markGameTime () {
        long endTime = System.currentTimeMillis();
        gameTime = endTime - startTime;
    }

    public void setWinDirection (String winDirection) {
        this.winDirection = winDirection;
    }

    public void initialiseWinList (int num) {
        winList = new int[num];
        counter = 0;
    }

    public void addToWinList (int num){
        winList[counter] = num;
        counter++;
    }

    public void flushWinList () {
        initialiseWinList(0);
        counter = 0;
    }

    public Player getPlayer () {
        return this.player;
    }

    public int [] getWinList () {
        return this.winList;
    }

    public  String getWinDirection () {
        return this.winDirection;
    }

    public int getPlayerRow () {
        return player.getPlayerRow();
    }

    public String getPlayerMarker () {
        return player.getPlayerMarker();
    }

    public String getGameTime () {
        String timeElasped;
        long totalSeconds = TimeUnit.MILLISECONDS.toSeconds(this.gameTime);
        int minutes = (int)(totalSeconds/ 60);
        int seconds = (int)(totalSeconds % 60);
        if (minutes > 0)
            timeElasped = "Game time: " + minutes + " Minutes " + seconds + " seconds";
        else
            timeElasped = "Game time: " + seconds + " seconds";
        return timeElasped;
    }

    public Leaderboard getLeaderBoard () {
        return leaderBoard;
    }

}
