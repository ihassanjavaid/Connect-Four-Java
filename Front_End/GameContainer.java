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
    add(welcomeScreen);
    add(gameScreen);
  }

  protected void setParent (GameFrame parent) {
    welcomeScreen.setParent(parent);
  }

  protected void showGameScreen () {
    moveToFront(gameScreen);
    welcomeScreen.setVisible(false);

  }



}
