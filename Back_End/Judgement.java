package Back_End;



public class Judgement {
    String winDirection, playerMarker;
    boolean victoryFlag;
    private int [] winList;
    private int counter;

    public Judgement (){
        victoryFlag = false;
        winDirection = "";
        playerMarker = "";
        counter = 0;
    }

    public void setVictoryFlag (boolean victoryFlag){
        this.victoryFlag = victoryFlag;
    }

    public void setWinDirection (String winDirection) {
        this.winDirection = winDirection;
    }
    public void setPlayerMarker (String playerMarker) {
        this.playerMarker = playerMarker;
    }

    public void initialiseWinList (int num) {
        winList = new int[num];
    }

    public void addToWinList (int num){
        winList[counter] = num;
        counter++;
    }

    public void flushWinList () {
        initialiseWinList(0);
    }

    public boolean getVictoryFlag () {
        return this.victoryFlag;
    }

    public int [] getWinList () {
        return this.winList;
    }

    public  String getWinDirection () {
        return this.winDirection;
    }
}
