
import javax.swing.*;
import java.awt.*;

public class GameFrame {
  //Class attributes
  private JFrame gameFrame;
  //private WelcomePanel welcomeScreen;
  //private GamePanel gameScreen;
  private GameContainer game;

  //No-args constructor
  public GameFrame () {
    //Initialise game frame
    gameFrame = new JFrame("Connect-4");

    //Set frame properties
    gameFrame.setPreferredSize(new Dimension(1000, 1000));
    gameFrame.setLocation(500, 100);
    gameFrame.setLayout(new BorderLayout());
    gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Initialise components
    game = new GameContainer();
    game.setParent(this);

    //Add components
    gameFrame.add(game, BorderLayout.CENTER);

    //Show frame
    gameFrame.pack();
    gameFrame.setVisible(true);

  }

  protected void showGameScreen () {
    game.showGameScreen();
  }


  public static void main (String [] args) {
    new GameFrame();
  }

}
