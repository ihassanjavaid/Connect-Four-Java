package Front_End;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Back_End.*;

public class GamePanel extends JPanel implements ActionListener, Playable {
    private GameFrame parent;
    private JButton backButton;
    private Image gameImg;
    private ImageIcon playerPiece;
    private JLabel [][] playerLabel;
    private JLabel turnInformer;
    private JButton [] moveButtons;
    private int playerColumn, playerRow, turnTracker;
    private Judgement judgement;
    private Playable gamePlay;

    //No-args constructor
    public GamePanel () {
        judgement = new Judgement();
        judgement.setStartTime(System.currentTimeMillis());
        turnTracker = 0;
        moveButtons = new JButton[7]; //Seven game column
        playerLabel = new JLabel[6][7]; //Forth two total places on the board
        setMoveButtons();
        initialisePlayerLabels();

        //Set panel properties
        setBounds(0, 0, 1005, 845);
        setLayout(null);

        //Initialise components
        ImageIcon turnBar = new ImageIcon("Assests/Player bar.png");
        turnInformer = new JLabel(turnBar);

        backButton = new JButton("Exit");
        backButton.setBounds(900, 785, 100, 25);
        backButton.addActionListener(this);

        //Set fill image
        ImageIcon fillImage = new ImageIcon("Assests/BG_Fill.png");
        JLabel fillLabel = new JLabel(fillImage);
        fillLabel.setBounds(0, 770, 1000, 60);

        //Set files paths
        gameImg = new ImageIcon("Assests/GameBG.gif").getImage();

        //Add components
        add(turnInformer);
        add(backButton);
        add(fillLabel);

        // Add clock and game time
        this.add(TimeSupport.getSystemTime()).setBounds( 220, 0, 150, 50);
        this.add(TimeSupport.getGameTime()).setBounds(680, 0, 150, 50);

        //Play background music
        Music.playGameBackgroundMusic();

    }

    protected void initialiseGame () throws IOException {
        gamePlay = new GamePlay(judgement);
        setTurnBar();
    }

    public void setParent (GameFrame parent) {
        this.parent = parent;
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        g.drawImage(gameImg, 0, 0, 1000, 776, this);
    }

    public void actionPerformed (ActionEvent event) {
        Object eventHolder = event.getSource();

        if (eventHolder == backButton){
            int infoHolder = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "Warning", JOptionPane.YES_NO_OPTION);
            if (infoHolder == JOptionPane.YES_OPTION) {
                Music.stopGameBackgroundMusic();
                parent.showWelcomeScreen();
                reset();
            }
        }
        else{
            for (int i = 0; i < 7; i++) {
                if (eventHolder == moveButtons[i]){
                    Music.playMoveSoundEffect();
                    playerColumn = i;
                    try {
                        makeMove(i);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //Method connecting back end to front end
    public void makeMove (int columnNumber) throws Exception {
      gamePlay.makeMove(columnNumber);
      if (!judgement.isGameDraw()) {
        if (judgement.getPlayerRow() == 0){
            moveButtons[columnNumber].setEnabled(false);
        }
        playerRow = judgement.getPlayerRow();
        setPlayerPiece();
        setTurnBar();
        updateBoard();
        setVictoryBar();
      }
      else {
        Music.stopGameBackgroundMusic();
        Music.playDrawMusic();
        JOptionPane.showMessageDialog(this, "Game draw!", "Draw", JOptionPane.INFORMATION_MESSAGE);
        Music.stopDrawMusic();
        parent.showWelcomeScreen();
      }

    }

    //Method to reset game screen
    protected void reset () {
        playerLabel = new JLabel[6][7];
        initialisePlayerLabels();
    }

    //Method to initialise and set move buttons
    private void setMoveButtons () {
        int x_pos = 158;
        ImageIcon [] buttonImg = new ImageIcon [7];
        String loc;
        for (int i = 0; i < 7; i++) {
            loc = "Assests/Column Button-" + (i+1) + ".png";
            buttonImg[i] = new ImageIcon(loc);
        }

        for (int i = 0; i < 7; i++){
            moveButtons[i] = new JButton(buttonImg[i]);
            moveButtons[i].setOpaque(false);
            moveButtons[i].setBorderPainted(false);
            moveButtons[i].setContentAreaFilled(false);
            moveButtons[i].setFocusPainted(false);
            moveButtons[i].setBounds(x_pos, 670, 90, 70);
            moveButtons[i].addActionListener(this);
            add(moveButtons[i]);
            x_pos += 100;
        }
    }

    private void initialisePlayerLabels () {
        //Fixed position of the game board for the player pieces
        int [] row_pos = {160, 257, 357, 457, 556, 653, 750};
        int [] column_pos = {65, 165, 265, 367, 467, 567};

        for (int row = 0; row < 6; row++){
            for (int column = 0; column < 7; column++){
                playerLabel[row][column] = new JLabel();
                playerLabel[row][column].setVisible(false);
                playerLabel[row][column].setBounds(row_pos[column], column_pos[row], 90, 90);
                add(playerLabel[row][column]);
            }
        }
    }

    private void setTurnBar () {
        if (turnTracker == 0) {
            turnInformer.setBounds(20, 120, 100, 25);
            swapPlayer();
        }
        else {
            turnInformer.setBounds(880, 120, 100, 25);
            swapPlayer();
        }
    }

    //Method to disable all column buttons
    private void disableAllBtns () {
        for (JButton btn:moveButtons){
            btn.setEnabled(false);
        }
    }

    //Method to swap players
    private void swapPlayer () {
        if (turnTracker == 0)
            turnTracker = 1;
        else
            turnTracker = 0;
    }

    //Method decides the image of the piece as per current player
    private void setPlayerPiece () {
        if (judgement.getPlayerMarker().equals("o")) {
            this.playerPiece = new ImageIcon("Assests/Player 1.png");
        }
        else {
            this.playerPiece = new ImageIcon("Assests/Player 2.png");
        }
    }

    //This method simple label image and displays the label
    private void updateBoard () {
        playerLabel[playerRow][playerColumn].setIcon(playerPiece);
        playerLabel[playerRow][playerColumn].setVisible(true);
    }

    //Method to connect four similar pieces
    private void setVictoryBar () {
        boolean victoryFlag = false;
        String winDirection = judgement.getWinDirection();
        int [] winList = judgement.getWinList();

        //Fixed locations for placing the victory bar
        int [] horizontal_x = {210, 310, 410, 510, 510, 510, 510};
        int [] horizontal_y = {90, 190, 290, 390, 490, 590};

        int [] vertical_x = {196, 294, 394, 494, 594, 692, 790};
        int [] vertical_y = {60, 160, 262, 262, 262, 262};

        int [] diagonal_x = {200, 300, 400, 500, 500, 500, 500};
        int [] diagonal_y = {60, 160, 260, 260, 260, 260};

        ImageIcon victoryBar;
        JLabel victoryLabel;

        victoryLabel = new JLabel();

        add(victoryLabel);

        if (!winDirection.equals("")){
            victoryFlag = true;
            switch (winDirection){
                case "Horizontal": {
                    victoryBar = new ImageIcon("Assests/Horizontal cross.png");
                    int smallest = winList[0];
                    for (int i = 1; i < 4; i++) {
                        if (winList[i] < smallest)
                            smallest = winList[i];
                    }
                    victoryLabel.setBounds(horizontal_x[smallest], horizontal_y[winList[4]], 1500, 50);
                    victoryLabel.setIcon(victoryBar);
                    victoryLabel.setVisible(true);
                    break;
                }
                case "Vertical": {
                    int smallest = winList[0];
                    for (int i = 1; i < 4; i++) {
                        if (winList[i] < smallest)
                            smallest = winList[i];
                    }
                    victoryBar = new ImageIcon("Assests/Vertical cross.png");
                    victoryLabel.setBounds(vertical_x[winList[4]], vertical_y[smallest], 50, 400);
                    victoryLabel.setIcon(victoryBar);
                    victoryLabel.setVisible(true);
                    break;
                }
                case "Right Diagonal": {
                    int smallest_x = winList[1];
                    int smallest_y = winList[0];
                    int i = 1;
                    while (i < winList.length){
                        if (winList[i] < smallest_x)
                            smallest_x = winList[i];
                        i = i + 2;
                    }
                    i = 0;
                    while (i < winList.length-1){
                        if (winList[i] < smallest_y)
                            smallest_y = winList[i];
                        i = i + 2;
                    }
                    victoryBar = new ImageIcon("Assests/Right_DC.png");
                    victoryLabel.setBounds(diagonal_x[smallest_x], diagonal_y[smallest_y], 400, 400);
                    victoryLabel.setIcon(victoryBar);
                    victoryLabel.setVisible(true);
                    break;
                }
                case "Left Diagonal": {
                    int smallest_x = winList[1];
                    int smallest_y = winList[0];
                    int i = 1;
                    while (i < winList.length){
                        if (winList[i] < smallest_x)
                            smallest_x = winList[i];
                        i = i + 2;
                    }
                    i = 0;
                    while (i < winList.length-1){
                        if (winList[i] < smallest_y)
                            smallest_y = winList[i];
                        i = i + 2;
                    }
                    victoryBar = new ImageIcon("Assests/Left_DC.png");
                    victoryLabel.setBounds(diagonal_x[smallest_x], diagonal_y[smallest_y], 400, 400);
                    victoryLabel.setIcon(victoryBar);
                    victoryLabel.setVisible(true);
                    break;
                }
            }
        }
        if (victoryFlag) {
            disableAllBtns();
            Music.stopGameBackgroundMusic();
            Music.playWinSoundEffect();
            Music.playVictoryMusic();
            showWinnerMessage();
            JOptionPane.showMessageDialog(this, judgement.getLeaderBoard());

            //Filing
            Filing.saveStats(this.judgement.getLeaderBoard());

            Music.stopVictoryMusic();
            parent.showWelcomeScreen();

        }
    }

    private void showWinnerMessage () {
        String winnerMessage;
        Player player = judgement.getPlayer();

        //stop the timer
        TimeSupport.getTimer().stop();

        winnerMessage = "Congratulations " + player.getPlayerName() + "!\nYou have " +
                "successfully connected four pieces\n";

        //getting game time through time support class
        winnerMessage += "Game time: " + ( TimeSupport.getGameTime().getText() );
        JOptionPane.showMessageDialog(this, winnerMessage);
    }

}
