package Front_End;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Back_End.GamePlay;
import Back_End.Judgement;

public class GamePanel extends JPanel implements ActionListener, Playable {
  private GameFrame parent;
  private JButton backButton;
  private Image gameImg;
  private ImageIcon playerPiece;
  private JLabel [][] playerLabel;
  private JButton [] moveButtons;
  private int playerColumn, playerRow;
  private Judgement judgement;
  private Playable gamePlay;

  //No-args constructor
  public GamePanel () {
    judgement = new Judgement();
    moveButtons = new JButton[7]; //Seven game column
    playerLabel = new JLabel[6][7]; //Forth two total places on the board
    setMoveButtons();
    initialisePlayerLabels();

    //Set panel properties
    setBounds(0, 0, 1005, 845);
    setLayout(null);

    //Initialise components
    backButton = new JButton("Back");
    backButton.setBounds(900, 785, 100, 25);
    backButton.addActionListener(this);

    gameImg = new ImageIcon("D:/University/Object Oriented Programming/Semester Project/Connect-Four-Java/Assests/GameBG.png").getImage();

    //Add components
    add(backButton);
  }

  protected void initialiseGame () {
    gamePlay = new GamePlay(judgement);
  }

  //Method to initialise and set move buttons
  private void setMoveButtons () {
    int x_pos = 175;
    ImageIcon [] buttonImg = new ImageIcon [7];
    String loc;
    for (int i = 0; i < 7; i++) {
      loc = "D:/University/Object Oriented Programming/Semester Project/Connect-Four-Java/Assests/Column Button-" + (i+1) + ".png";
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
    int [] row_pos = {167, 267, 367, 467, 568, 668, 770};
    int [] column_pos = {65, 165, 265, 367, 467, 567};

    for (int row = 0; row < 6; row++){
      for (int column = 0; column < 7; column++){
        playerLabel[row][column] = new JLabel();
        //playerLabel[row][column].setVisible(false);
        playerLabel[row][column].setBounds(row_pos[column], column_pos[row], 90, 90);
        add(playerLabel[row][column]);
      }
    }
  }

  protected void setParent (GameFrame parent) {
    this.parent = parent;
  }

  public void paintComponent (Graphics g) {
    g.drawImage(gameImg, 0, 0, this);
  }

  public void actionPerformed (ActionEvent event) {
    Object eventHolder = event.getSource();

    if (eventHolder == backButton){
      parent.showWelcomeScreen();
      reset();
    }
    else{
      for (int i = 0; i < 7; i++) {
        if (eventHolder == moveButtons[i]){
          playerColumn = i;
          makeMove(i);
          //setVictoryBar("Horizontal", new int[]{0,1,2,3,5});
        }
      }
    }
  }

  //Method to reset game screen
  protected void reset () {
    playerLabel = new JLabel[6][7];
    initialisePlayerLabels();
  }

  //Method decides the image of the piece as per current player
  private void setPlayerPiece () {
    if (judgement.getPlayerMarker().equals("o"))
      this.playerPiece = new ImageIcon("D:/University/Object Oriented Programming/Semester Project/Connect-Four-Java/Assests/Player 1.png");
    else
      this.playerPiece = new ImageIcon("D:/University/Object Oriented Programming/Semester Project/Connect-Four-Java/Assests/Player 2.png");
  }

  //This method simple label image and displays the label
  private void updateBoard () {
    playerLabel[playerRow][playerColumn].setIcon(playerPiece);
    playerLabel[playerRow][playerColumn].setVisible(true);
  }

  private void setVictoryBar (String winDirection, int[] winList) {
    System.out.println("In set victory bar!");
    int smallest = 0;
    int [] row = {65, 165, 265, 367, 467, 567};
    int [] column = {167, 267, 367, 467, 568, 668, 770};
    ImageIcon victoryBar;
    JLabel victoryLabel;

    victoryLabel = new JLabel();
    victoryLabel.setOpaque(false);
    add(victoryLabel);

    if (winDirection != null){
      switch (winDirection){
        case "Horizontal":
          System.out.println("In horizontal");
          victoryBar = new ImageIcon("\"D:/University/Object Oriented Programming/Semester Project/Connect-Four-Java/Assests/Horizontal cross.png");
          smallest = winList[0];
          for (int i = 1; i < 4; i++){
            if (winList[i] < smallest)
              smallest = winList[i];
          }
          victoryLabel.setBounds(row[smallest], column[winList[4]], 100, 100);
          System.out.println("Smallest: " + smallest);
          victoryLabel.setIcon(victoryBar);
          victoryLabel.setVisible(true);
          System.out.println(victoryLabel.getBounds());
          break;
        case "Vertical":
          victoryBar = new ImageIcon("\"D:/University/Object Oriented Programming/Semester Project/Connect-Four-Java/Assests/Vertical cross.png");
          break;
        case "Left Diagonal":
          victoryBar = new ImageIcon("\"D:/University/Object Oriented Programming/Semester Project/Connect-Four-Java/Assests/Left_DC.png");
          break;
        case "Righ Diagonal":
          victoryBar = new ImageIcon("\"D:/University/Object Oriented Programming/Semester Project/Connect-Four-Java/Assests/Right_DC.png");
          break;
      }

    }

  }

  public void makeMove (int columnNumber) {
    System.out.println(columnNumber);
    gamePlay.makeMove(columnNumber);
    if (judgement.getPlayerRow() == 0){
      moveButtons[columnNumber].setEnabled(false);
    }
    playerRow = judgement.getPlayerRow();
    setPlayerPiece();
    updateBoard();

  }


}
