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
    setBounds(0, 0, 1000, 1000);
    setLayout(null);

    //Initialise components
    backgroundImage = new ImageIcon("D:/University/Object Oriented Programming/Semester Project/Connect-Four-Java/Assests/Main Screen.png").getImage();
    play = new ImageIcon("D:/University/Object Oriented Programming/Semester Project/Connect-Four-Java/Assests/Play button.png");

    playButton = new JButton(play);
    playButton.setOpaque(false);
    playButton.addActionListener(this);

    closeButton = new JButton("Exit");
    closeButton.addActionListener(this);

    //Add components
    add(playButton).setBounds(450, 500, 500, 200);
    //add(closeButton).setBounds(450, 535, 100, 35);

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
