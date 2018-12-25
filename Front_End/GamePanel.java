package Front_End;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
  private Clip clip;

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

    //Set background image
    ImageIcon backGroundImage = new ImageIcon("D:/University/Object Oriented Programming/Semester Project/Connect-Four-Java/Assests/GameBG.png");
    JLabel backgroundLabel = new JLabel(backGroundImage);
    backgroundLabel.setBounds(0, 0, 1005, 845);

    //Set fill image
    ImageIcon fillImage = new ImageIcon("D:\\University\\Object Oriented Programming\\Semester Project\\src\\Assests\\BG_Fill.png");
    JLabel fillLabel = new JLabel(fillImage);
    fillLabel.setBounds(0, 770, 1000, 60);

    //Set files paths
    gameImg = new ImageIcon("D:/University/Object Oriented Programming/Semester Project/Connect-Four-Java/Assests/GameBG.png").getImage();

    //Add components
    add(backButton);
    add(fillLabel);

    //Play background music
    playMusic("Background music");
  }

  protected void initialiseGame () {
    gamePlay = new GamePlay(judgement);
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
        clip.stop();
        parent.showWelcomeScreen();
        reset();
      }
    }
    else{
      for (int i = 0; i < 7; i++) {
        if (eventHolder == moveButtons[i]){
          playMusic("Piece move");
          playerColumn = i;
          makeMove(i);
        }
      }
    }
  }

  public String getPlayerMarker () {
    return judgement.getPlayerMarker();
  }

  //Method connecting back end to front end
  public void makeMove (int columnNumber) {
    System.out.println(columnNumber);
    gamePlay.makeMove(columnNumber);
    if (judgement.getPlayerRow() == 0){
      moveButtons[columnNumber].setEnabled(false);
    }
    playerRow = judgement.getPlayerRow();
    setPlayerPiece();
    updateBoard();
    setVictoryBar();
  }

  //Method to reset game screen
  protected void reset () {
    playerLabel = new JLabel[6][7];
    initialisePlayerLabels();
  }

  //Method to initialise and set move buttons
  private void setMoveButtons () {
    int x_pos = 155;
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

  //Method to disable all column buttons
  private void disableAllBtns () {
      for (JButton btn:moveButtons){
        btn.setEnabled(false);
      }
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

  //Method to connect four similar pieces
  private void setVictoryBar (){
    boolean victoryFlag = false;
    String winDirection = judgement.getWinDirection();
    int [] winList = judgement.getWinList();

    //Fixed locations for placing the victory bar
    int [] horizontal_x = {210, 310, 410, 510, 510, 510, 510};
    int [] horizontal_y = {90, 190, 290, 390, 490, 590};

    int [] vertical_x = {205, 305, 405, 505, 605, 705, 805};
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
          victoryBar = new ImageIcon("D:\\University\\Object Oriented Programming\\Semester Project\\Connect-Four-Java\\Assests\\Horizontal cross.png");
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
          victoryBar = new ImageIcon("D:\\University\\Object Oriented Programming\\Semester Project\\Connect-Four-Java\\Assests\\Vertical cross.png");
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
          victoryBar = new ImageIcon("D:\\University\\Object Oriented Programming\\Semester Project\\Connect-Four-Java\\Assests\\Right_DC.png");
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
          victoryBar = new ImageIcon("D:\\University\\Object Oriented Programming\\Semester Project\\Connect-Four-Java\\Assests\\Left_DC.png");
          victoryLabel.setBounds(diagonal_x[smallest_x], diagonal_y[smallest_y], 400, 400);
          victoryLabel.setIcon(victoryBar);
          victoryLabel.setVisible(true);
          break;
        }
      }
    }
    if (victoryFlag) {
      Timer timer = new Timer(3000, this);
      disableAllBtns();
      clip.stop();
      playMusic("Win informer");
      timer.start();
      parent.showWinnerScreen();

    }
  }

  private void sleep (int time) {
    try {
      Thread.sleep(time);
    }
    catch (Exception e) {}
  }

  private void playMusic (String type) {
    switch (type) {
      case "Background music": {
        String soundFile = "D:\\University\\Object Oriented Programming\\Semester Project\\src\\Assests\\In_game.wav";
        try {
          AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFile).getAbsoluteFile());
          clip = AudioSystem.getClip();
          clip.open(audioInputStream);
          clip.start();
        } catch (Exception e) {
        }
        break;
      }

      case "Piece move": {
        String soundFile = "D:\\University\\Object Oriented Programming\\Semester Project\\src\\Assests\\Move.wav";
        try {
          AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFile).getAbsoluteFile());
          Clip clip1 = AudioSystem.getClip();
          clip1.open(audioInputStream);
          clip1.start();
        } catch (Exception e) {
        }
        break;
      }

      case "Win informer": {
        String soundFile = "D:\\University\\Object Oriented Programming\\Semester Project\\src\\Assests\\Win_Sound.wav";
        try {
          AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFile).getAbsoluteFile());
          Clip clip1 = AudioSystem.getClip();
          clip1.open(audioInputStream);
          clip1.start();
        } catch (Exception e) {
        }
        break;
      }
    }
  }

}
