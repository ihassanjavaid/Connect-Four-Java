package Back_End;

public class Judgement {
    private Player player;
    private String winDirection;
    private int [] winList;
    private int counter;

    public Judgement (){
        winDirection = "";
        counter = 0;
    }

    public void setPlayer (Player player) {
        this.player = player;
    }


    public void setWinDirection (String winDirection) {
        this.winDirection = winDirection;
    }

    public void initialiseWinList (int num) {
        winList = new int[num];
        counter = 0;
    }

    public void addToWinList (int num){
        System.out.println(counter);
        try {
            winList[counter] = num;
        }
        catch (Exception e){}
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

    public int getCounter () {
        return this.counter;
    }

    public int getPlayerRow () {
        return player.getPlayerRow();
    }

    public String getPlayerMarker () {
        return player.getPlayerMarker();
    }
}
