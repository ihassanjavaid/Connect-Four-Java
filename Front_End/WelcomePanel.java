package Front_End;

import Back_End.Filing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;


public class WelcomePanel extends JPanel implements ActionListener{
  //Class attributes
  private JButton playButton, closeButton, leaderBoardButton;
  private GameFrame parent;
  private Image backgroundImage;
  private Filing files;
  private boolean resetFlag;

  //No-args constructor
  public WelcomePanel () {
    //Set panel properties
    setBounds(0, 0, 1005, 845);
    setLayout(null);

    //Initialise components
    backgroundImage = new ImageIcon("Assests/Main Screen.png").getImage();
    ImageIcon play = new ImageIcon("Assests/Play button.png");
    ImageIcon exit = new ImageIcon("Assests/Exit button.png");
    ImageIcon leaderBoard = new ImageIcon("Assests\\Leaderboard_icon.png");

    playButton = new JButton(play);
    playButton.setOpaque(false);
    playButton.setBorderPainted(false);
    playButton.setContentAreaFilled(false);
    playButton.setFocusPainted(false);
    playButton.addActionListener(this);

    closeButton = new JButton(exit);
    closeButton.setOpaque(false);
    closeButton.setBorderPainted(false);
    closeButton.setContentAreaFilled(false);
    closeButton.setFocusPainted(false);
    closeButton.addActionListener(this);

    leaderBoardButton = new JButton(leaderBoard);
    leaderBoardButton.setOpaque(false);
    leaderBoardButton.setBorderPainted(false);
    leaderBoardButton.setContentAreaFilled(false);
    leaderBoardButton.setFocusPainted(false);
    leaderBoardButton.addActionListener(this);

    //Add components
    add(playButton).setBounds(370, 380, 290, 130);
    add(closeButton).setBounds(370, 510, 290, 130);
    add(leaderBoardButton).setBounds(900, 0, 100, 100);

    //Add Filing
    files = new Filing();

    // Set reset flag to false i.e. never resetted in current gameplay
    resetFlag = false;
  }

  protected void setParent (GameFrame parent) {
    this.parent = parent;
  }

  public void paintComponent (Graphics g) {
    g.drawImage(backgroundImage, 0, 0, 1000, 815, this);
  }

  public void actionPerformed(ActionEvent event) {
    Object eventHolder = event.getSource();

    if (eventHolder == closeButton) {
      Music.stopWelcomeScreenMusic();
      System.exit(0);
    }
    else if (eventHolder == playButton) {
      try {
        parent.showGameScreen();
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
    else if (eventHolder == leaderBoardButton) {

      if ( !resetFlag ) {
        showLeaderboard();
      }

      else{
        noRecordsFound();
      }

    }
    else if (eventHolder == playButton) {
        parent.showGameScreen();
    }

  }
  private void showLeaderboard(){
    Object[] options = {"<html><b>OK</b></html>", "<html><i>Reset</i></html>"};

    int optionsInt = JOptionPane.showOptionDialog(this, files.getLastRecord(), "Leaderboard!",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
            null, options, options[1]);

    if ( optionsInt == 1 ){ // if user presses reset

      files.flushRecords();
      resetFlag = true;
      noRecordsFound();
    }
  }

  private void noRecordsFound(){
    JOptionPane.showMessageDialog(this, "Records have been cleared!");
  }
  
}
