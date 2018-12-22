import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener{
  private JButton closeButton;

  public GamePanel () {
    //setBackground(Color.YELLOW);
    setPreferredSize(new Dimension(1080, 850));
    setBounds(0, 0, 1000, 1000);


    closeButton = new JButton("Close");
    closeButton.addActionListener(this);

    //add(closeButton);
  }

  public void actionPerformed (ActionEvent event) {
    Object eventHolder = event.getSource();

    if (eventHolder == closeButton){
      System.exit(0);
    }

  }
}

//System.out.println()
