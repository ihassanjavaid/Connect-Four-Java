import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePanel extends JPanel implements ActionListener{
  //Class attributes
  private JPanel mainPanel;
  private JButton playButton, closeButton;
  private GameFrame parent;

  //No-args constructor
  public WelcomePanel () {
    //Set propersties
    setBackground(Color.green);
    setPreferredSize(new Dimension(1000, 1000));
    setBounds(0, 0, 1000, 1000);
    setOpaque(true);
    setLayout(null);

    //Initialise components

    playButton = new JButton("Play");
    playButton.addActionListener(this);

    closeButton = new JButton("Close");
    closeButton.addActionListener(this);

    //Add components
    add(playButton).setBounds(450, 500, 100, 35);
    add(closeButton).setBounds(450, 535, 100, 35);

    setVisible(true);

  }

  protected void setParent (GameFrame parent) {
    this.parent = parent;
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
