package Front_End;
import javax.swing.*;
import java.awt.*;

public class GameContainer extends JLayeredPane {
  private WelcomePanel welcomeScreen;
  private GamePanel gameScreen;
  private WinnerScreen winnerScreen;
  private GameFrame parent;

  public GameContainer () {
    //Set properties
    setLayout(null);
    setBounds(0, 0, 1000, 1000);

    //Initialise components
    welcomeScreen = new WelcomePanel();
    //gameScreen = new GamePanel();

    //Add components
    add(welcomeScreen, new Integer(1));
    //add(gameScreen, new Integer(0));
  }

  protected void setParent (GameFrame parent) {
    welcomeScreen.setParent(parent);
    this.parent = parent;
  }

  protected void showGameScreen () {
      gameScreen = new GamePanel();
      gameScreen.setParent(this.parent);
      add(gameScreen, new Integer(0));

      moveToBack(welcomeScreen);
      moveToFront(gameScreen);
      gameScreen.setVisible(true);
      welcomeScreen.setVisible(false);
      gameScreen.initialiseGame();
  }

  protected void showWelcomeScreen () {
    moveToBack(gameScreen);
    moveToFront(welcomeScreen);
    gameScreen.setVisible(false);
    welcomeScreen.setVisible(true);
  }

  protected void showWinnerScreen () {
      winnerScreen = new WinnerScreen(gameScreen.getPlayerMarker());
      add(winnerScreen, new Integer(0));

      moveToBack(gameScreen);
      moveToFront(winnerScreen);
      winnerScreen.setVisible(true);
      gameScreen.setVisible(false);
      super.repaint();
      sleep(8000);
      //Play music
      moveToFront(welcomeScreen);
      welcomeScreen.setVisible(true);
      winnerScreen.setVisible(false);
      gameScreen.reset();

  }

    private void sleep (int time) {
        try {
            Thread.sleep(time);
        }
        catch (Exception e) {}
    }

}
