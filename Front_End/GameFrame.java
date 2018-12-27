package Front_End;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameFrame {
  //Class attributes
  public JFrame gameFrame;
  private GameContainer game;

  //No-args constructor
  public GameFrame () {
    //Initialise game frame
    gameFrame = new JFrame("Connect-4");

    //Set frame properties
    gameFrame.setPreferredSize(new Dimension(1005, 845));
    gameFrame.setSize(new Dimension(1005, 845));
    gameFrame.setLocation(dim.width/2-gameFrame.getSize().width/2, dim.height/2-gameFrame.getSize().height/2);
    gameFrame.setResizable(false);
    gameFrame.setLayout(new BorderLayout());
    gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Image gameIcon = new ImageIcon("Assests/Icon.png").getImage();
    gameFrame.setIconImage(gameIcon);

    //Initialise components
    game = new GameContainer();
    game.setParent(this);

    //Add components
    gameFrame.add(game, BorderLayout.CENTER);

    //Show frame
    gameFrame.pack();
    gameFrame.setVisible(true);



  }

  protected void showGameScreen () throws Exception {
    game.showGameScreen();
  }

  protected void showWelcomeScreen () {
    game.showWelcomeScreen();
  }

  public static void main (String [] args) {
    new GameFrame();
  }


}
