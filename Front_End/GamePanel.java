import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GamePanel extends JPanel implements ActionListener, Playable {
  private GameFrame parent;
  private JButton backButton;
  private JPanel leftPanel, rightPanel;
  private Image gameImg;
  private ImageIcon playerPiece;
  private JLabel [][] playerLabel;
  private JButton [] moveButtons;
  private String player = "o";
  private int playerColumn, playerRow;

  public GamePanel () {
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
    int [] column_pos = {167, 267, 367, 467, 568, 668, 770};
    int [] row_pos = {567, 467, 367, 265, 165, 65};

    for (int row = 0; row < 6; row++){
      for (int column = 0; column < 7; column++){
        playerLabel[row][column] = new JLabel();
        playerLabel[row][column].setVisible(false);
        playerLabel[row][column].setBounds(row_pos[row], column_pos[column], 90, 90);
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
      playerLabel = new JLabel[6][7];
      initialisePlayerLabels();
    }
    else{
      for (int i = 0; i < 7; i++) {
        if (eventHolder == moveButtons[i]){
          makeMove(i);
        }
      }
    }
  }

  public void makeMove (int columnNumber) {
    System.out.println(columnNumber);
  }

  public void setCredentials (String player, int row) {
    this.player = player;
    this.playerRow = row;
    setPlayerPiece();
    updateBoard();
  }

  //Method decides the image of the piece as per current player
  private void setPlayerPiece () {
    if (this.player.equals("o"))
      this.playerPiece = new ImageIcon("D:/University/Object Oriented Programming/Semester Project/Connect-Four-Java/Assests/Player 1.png");
    else
      this.playerPiece = new ImageIcon("D:/University/Object Oriented Programming/Semester Project/Connect-Four-Java/Assests/Player 2.png");
  }

  //This method simple label image and displays the label
  private void updateBoard () {
    playerLabel[playerRow][playerColumn].setIcon(playerPiece);
    playerLabel[playerRow][playerColumn].setVisible(true);
  }

}
