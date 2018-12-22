public class GameBoard {
    public String[][] GameBoard;


    public GameBoard(){
        this.GameBoard = new String[6][7];
    }

    protected void InitializeGameBoard() {
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 7; column++) {
                GameBoard[row][column] = "-";
            }
        }
    }
// Getting the Player marker from the relevant location on the gameboard
    public String getString(int i, int j){
        String s = GameBoard[i][j];
        return s;
    }
// Setting the Player marker into the relevant location on the gameboard
    public void setString(String s, int i, int j){
        GameBoard[i][j] = s;
    }





}


