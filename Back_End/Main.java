import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        int ColumnButton;
        GamePlay Game= new GamePlay();
        while (Game.judge.victoryFlag == false){
            ColumnButton = Integer.parseInt(JOptionPane.showInputDialog(null,Game.PlayerArray[Game.i].PlayerName+"  Choose Column number from 1 - 7"));
            Game.PlayerChosenColumn(ColumnButton-1);
            Game.displayArray();
            Game.CheckWin();
            Game.SwapPlayers();
        }
    }
}
