import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener{
  private GameFrame parent;
  private JButton closeButton;
  private JPanel leftPanel, rightPanel;
  private Image gameImg;
  private JLabel [] player1, player2;

  public GamePanel () {
    //Set panel properties
    setBounds(0, 0, 1005, 845);
    setLayout(null);

    //Initialise components
    closeButton = new JButton("Back");
    closeButton.setBounds(900, 785, 100, 25);
    closeButton.addActionListener(this);

    gameImg = new ImageIcon("D:/University/Object Oriented Programming/Semester Project/Connect-Four-Java/Assests/GameBG.png").getImage();

    //Add components
    add(closeButton);

  }

  protected void setParent (GameFrame parent) {
    this.parent = parent;
  }

  public void paintComponent (Graphics g) {
    g.drawImage(gameImg, 0, 0, this);
  }

  public void actionPerformed (ActionEvent event) {
    Object eventHolder = event.getSource();

    if (eventHolder == closeButton){
      parent.showWelcomeScreen();
    }

  }

  private class PlayerInfo extends JPanel {
    public PlayerInfo () {

    }
  }
}

//System.out.println()
