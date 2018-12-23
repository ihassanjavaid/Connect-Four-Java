package Back_End;
import Front_End.Playable;
import Front_End.GamePanel;

import javax.swing.*;

public class GamePlay implements Playable{
    private Player [] players = new Player[2];
    private GameBoard gameBoard;
    private int turnHolder, playerRow;
    private Playable gamePanel;

    public GamePlay () {
        gameBoard = new GameBoard();
        players[0] = new Player(JOptionPane.showInputDialog("Enter Player 1 Name:  "), "o");
        players[1] = new Player(JOptionPane.showInputDialog("Enter Player 2 Name:  "), "x");
        turnHolder = 0;
    }

    private void swapPlayers () {
        if (turnHolder == 0)
            turnHolder = 1;
        else
            turnHolder = 0;
    }

    @Override
    public void makeMove(int columnNumber) {
        playerRow = gameBoard.setPlayerMarker(players[turnHolder].getPlayerMarker(), columnNumber);
        players[turnHolder].setPlayerCoordinates(playerRow, columnNumber);
        Judgement judgement = gameBoard.checkForVictory(players[turnHolder].getPlayerMarker(), players[turnHolder].getPlayerRow(), players[turnHolder].getPlayerColumn());
        setCredentials(players[turnHolder].getPlayerMarker(), playerRow, judgement);
        if (playerRow == 0){
            deactivateButton(columnNumber);
        }
        swapPlayers();
    }

    @Override
    public void setCredentials(String playerMarker, int row, Judgement judgement) {
        gamePanel.setCredentials(playerMarker, row, judgement);
    }

    @Override
    public void deactivateButton(int buttonNumber) {
        gamePanel.deactivateButton(buttonNumber);
    }
}
