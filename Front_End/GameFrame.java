//package Front_End;

import javax.swing.*;
import java.awt.*;

public class GameFrame {
  //Class attributes
  private JFrame gameFrame;

  //No-args constructor
  public GameFrame () {
    //Initialise game frame
    gameFrame = new JFrame("Connect-4");

    //Set frame properties
    gameFrame.setSize(1000, 1000);
    gameFrame.setLocation(700, 300)
    gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Show frame
    gameFrame.setVisible(true);

  }


  public static void main (String [] args) {
    new GameFrame();
  }

}
