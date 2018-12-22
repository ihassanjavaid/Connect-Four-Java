import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePanel extends JPanel implements ActionListener{
  //Class attributes
  private JPanel mainPanel;
  private JButton playButton, closeButton;
  private GameFrame parent;
  private Image backgroundImage;
  private ImageIcon play, exit;

  //No-args constructor
  public WelcomePanel () {
    //Set panel propersties
    setBounds(0, 0, 1005, 845);
    setLayout(null);

    //Initialise components
    backgroundImage = new ImageIcon("D:/University/Object Oriented Programming/Semester Project/Connect-Four-Java/Assests/Main Screen.png").getImage();
    play = new ImageIcon("D:/University/Object Oriented Programming/Semester Project/Connect-Four-Java/Assests/Play button.png");
    exit = new ImageIcon("D:/University/Object Oriented Programming/Semester Project/Connect-Four-Java/Assests/Exit button.png");

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

    //Add components
    add(playButton).setBounds(370, 380, 290, 130);
    add(closeButton).setBounds(370, 510, 290, 130);

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
      System.exit(0);
    }
    else if (eventHolder == playButton) {
        parent.showGameScreen();
    }

  }

}
