package Back_End;

public class GameBoard {
    private String [][] gameBoard;


    public GameBoard () {
        this.gameBoard = new String [6][7];
        initialiseGameBoard();
    }

    private void initialiseGameBoard () {
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 7; column++) {
                gameBoard[row][column] = "-";
            }
        }
    }

    protected void printGameBoard () {
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 7; column++) {
                System.out.print(gameBoard[row][column]);
            }
            System.out.println();
        }
    }

    protected int setPlayerMarker (String playerMarker,int playerColumn) {
        int row = 5;
        while (row >= 0 && !gameBoard[row][playerColumn].equals("-")){
            row--;
        }
        if (row >= 0)
            this.gameBoard[row][playerColumn] = playerMarker;

        return row;
    }

    protected String getPlayerMarker (int row, int column) {
        return this.gameBoard[row][column];
    }


}
