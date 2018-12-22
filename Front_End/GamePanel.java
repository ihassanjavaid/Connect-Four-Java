import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener{
  private GameFrame parent;
  private JButton closeButton;
  private JPanel leftPanel, rightPanel;

  public GamePanel () {
    //Set panel properties
    setBackground(Color.YELLOW);
    setBounds(0, 0, 1005, 845);
    setLayout(new BorderLayout());

    //Initialise components

    closeButton = new JButton("Back");
    closeButton.addActionListener(this);

    //Add components
    add(closeButton, BorderLayout.NORTH);

  }

  protected void setParent (GameFrame parent) {
    this.parent = parent;
  }

  public void actionPerformed (ActionEvent event) {
    Object eventHolder = event.getSource();

    if (eventHolder == closeButton){
      parent.showWelcomeScreen();
    }

  }
}

//System.out.println()
