public class Player {
    protected String PlayerName;
    protected String PlayerMarker;
    protected int TurnCounter;
    protected int PlayerRow;
    protected int PlayerColumn;





    public Player( String name, String marker ){
        this.PlayerName = name;
        this.PlayerMarker = marker;
        this.TurnCounter = 0;
    }

    public void TurnCounter() {
        TurnCounter = TurnCounter++;
    }
}
