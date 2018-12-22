import javax.swing.*;
import java.util.Arrays;

class GamePlay implements Bridge {
    protected int i;
    protected Player P1;
    protected Player P2;
    Judge judge;
    public boolean win;
    GameBoard GB;
    Player PlayerArray[];

    public GamePlay() {
        GB = new GameBoard();
        GB.InitializeGameBoard();
        Player P1 = new Player(JOptionPane.showInputDialog("Enter Player 1 Name:  "), JOptionPane.showInputDialog("Enter Player 1 Marker:  "));
        Player P2 = new Player(JOptionPane.showInputDialog("Enter Player 2 Name:  "), JOptionPane.showInputDialog("Enter Player 2 Marker:   "));
        Judge Judge = new Judge(GB);
        PlayerArray = new Player[2];
        PlayerArray[0] = P1;
        PlayerArray[1] = P2;
        i = 0;
        this.judge = new Judge(GB);
        win = false;
    }

    //Finalizes the move
    public void PlayerChosenColumn(int ColumnButton) {
        int row = CheckColumnAvailability(ColumnButton);
        System.out.print(row);
        System.out.println("  ,"+ColumnButton);
        MarkPosition(row, ColumnButton);
        PlayerArray[i].TurnCounter();
        PlayerArray[i].PlayerColumn = ColumnButton;
        PlayerArray[i].PlayerRow = row;
    }

    // Checks if column is available and returns row for available row
    public int CheckColumnAvailability(int ColumnButton) { //Check if column is full
        int row = 6;
        while (row >= 0) {
            row--;
            if (GB.getString(row, ColumnButton) == "-") {
                return row;
            }
        }
        if (row == 0) {
            JOptionPane.showMessageDialog(null, "Column is already Full!");
            ColumnButton = Integer.parseInt(JOptionPane.showInputDialog("Choose another Column: "));
            row = 5;
            while (row >= 6) {
                row--;
                if (GB.getString(row, ColumnButton) == "-") {
                    return row;
                }
            }
        }
        return row;
    }

    // marks the marker onto the gameboard
    public void MarkPosition(int row, int ColumnButton) {
        GB.setString(PlayerArray[i].PlayerMarker, row, ColumnButton);
    }

    // swaps player with each turn
    public void SwapPlayers() {
        if (i == 0) {
            i++;
        } else
            i--;
    }
    //Checks if anyone won
    public void CheckWin() {
        judge.HorizontalJudge(PlayerArray[i].PlayerMarker, PlayerArray[i].PlayerRow, PlayerArray[i].PlayerColumn);
        judge.VerticalJudge(PlayerArray[i].PlayerMarker, PlayerArray[i].PlayerRow, PlayerArray[i].PlayerColumn);
        judge.DiagonalJudge(PlayerArray[i].PlayerMarker, PlayerArray[i].PlayerRow, PlayerArray[i].PlayerColumn);

        if (judge.victoryFlag){
            win = true;
            System.out.println(PlayerArray[i].PlayerName+ "  IS THE WINNER!");
            }
        }

    void displayArray() {
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 7; column++) {
                System.out.print(GB.getString( row,column) + "     ");
            }
            System.out.println();
        }
    }
}