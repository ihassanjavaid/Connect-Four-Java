package Back_End;
import Front_End.Playable;


import javax.swing.*;

public class GamePlay implements Playable{
    private Player [] players = new Player[2];
    private GameBoard gameBoard;
    private int turnHolder, playerRow;
    private Judge victoryJudge;

    public GamePlay (Judgement judgement) {
        gameBoard = new GameBoard();
        victoryJudge = new Judge(gameBoard, judgement);
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
    public void makeMove(int playerColumn) {
        playerRow = gameBoard.setPlayerMarker(players[turnHolder].getPlayerMarker(), playerColumn);
        players[turnHolder].setPlayerCoordinates(playerRow, playerColumn);
        victoryJudge.makeJudgement(players[turnHolder]);

        if (playerRow == 0){
            deactivateButton(playerColumn);
        }
        swapPlayers();
    }


    @Override
    public void deactivateButton(int buttonNumber) {
        //gamePanel.deactivateButton(buttonNumber);
    }
}
