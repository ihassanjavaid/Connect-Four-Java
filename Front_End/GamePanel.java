import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GamePanel extends JPanel implements ActionListener, Playable {
  private GameFrame parent;
  private JButton backButton;
  private JPanel leftPanel, rightPanel;
  private Image gameImg;
  private JLabel [][] playerLabel;
  private JButton [] moveButtons;
  private String player;
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

    moveButtons[0].setBounds(175, 670, 90, 70);
    moveButtons[1].setBounds(275, 670, 90, 70);
    moveButtons[2].setBounds(375, 670, 90, 70);
    moveButtons[3].setBounds(475, 670, 90, 70);
    moveButtons[4].setBounds(575, 670, 90, 70);
    moveButtons[5].setBounds(675, 670, 90, 70);
    moveButtons[6].setBounds(775, 670, 90, 70);

    gameImg = new ImageIcon("D:/University/Object Oriented Programming/Semester Project/Connect-Four-Java/Assests/GameBG.png").getImage();

    //Add components
    add(backButton);

  }

  //Method to initialise and set move buttons
  private void setMoveButtons () {
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
      moveButtons[i].addActionListener(this);
      add(moveButtons[i]);

    }
  }

  private void initialisePlayerLabels () {
    for (int row = 0; row < 6; row++){
      for (int column = 0; column < 7; column++){
        playerLabel[row][column] = new JLabel();
        playerLabel[row][column].setVisible(false);
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
    }
    else{
      for (int i = 0; i < 7; i++) {
        if (eventHolder == moveButtons[i]){
          makeMove(i);
          playerLabel[0][0].setBounds(165, 680, 80, 80);
          playerLabel[0][0].setVisible(true);
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
    updateBoard();
  }

  private void updateBoard(){

  }

}

//System.out.println()
