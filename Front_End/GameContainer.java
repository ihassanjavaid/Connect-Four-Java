import javax.swing.*;
import java.awt.*;

public class GameContainer extends JLayeredPane {
  private WelcomePanel welcomeScreen;
  private GamePanel gameScreen;

  public GameContainer () {
    //Set properties
    setLayout(null);
    setBounds(0, 0, 1000, 1000);

    //Initialise components
    welcomeScreen = new WelcomePanel();
    gameScreen = new GamePanel();

    //Add components
    add(welcomeScreen, new Integer(1));
    add(gameScreen, new Integer(0));
  }

  protected void setParent (GameFrame parent) {
    welcomeScreen.setParent(parent);
    gameScreen.setParent(parent);
  }

  protected void showGameScreen () {
    moveToBack(welcomeScreen);
    moveToFront(gameScreen);
    gameScreen.setVisible(true);
    welcomeScreen.setVisible(false);
  }

  protected void showWelcomeScreen () {
    moveToBack(gameScreen);
    moveToFront(welcomeScreen);
    gameScreen.setVisible(false);
    welcomeScreen.setVisible(true);
  }



}
