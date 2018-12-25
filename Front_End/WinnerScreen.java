package Front_End;

import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class WinnerScreen extends JPanel implements ActionListener {
    private GameFrame parent;
    private JButton exitToMainButton;
    private Image backGround;
    private Clip clip;

    public WinnerScreen (String playerMarker) {
        //Set panel properties
        setBounds(0, 0, 1005, 845);
        setLayout(null);
        setVisible(false);

        //Initialise components
        backGround = new ImageIcon("D:\\University\\Object Oriented Programming\\Semester Project\\src\\Assests\\WinnerBG.png").getImage();

        //Set fill image
        ImageIcon fillImage = new ImageIcon("D:\\University\\Object Oriented Programming\\Semester Project\\src\\Assests\\WinnerBG.png");
        JLabel fillLabel = new JLabel(fillImage);
        fillLabel.setBounds(0, 770, 1000, 60);

        //Set winner message image
        JLabel winnerMessageLabel = new JLabel();
        setLabel(playerMarker, winnerMessageLabel);
        winnerMessageLabel.setBounds(250, 150, 500, 500);

        //Set exit to main button
        exitToMainButton = new JButton("Exit");
        exitToMainButton.setBounds(880, 785, 100, 25);
        exitToMainButton.addActionListener(this);

        //Set music file and play winner music
        playWinMusic();

        //Add components
        add(fillLabel);
        add(winnerMessageLabel);
        add(exitToMainButton);

    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        g.drawImage(backGround, 0, 0, this);
    }

    public void actionPerformed (ActionEvent event) {
        Object eventHolder = event.getSource();

        if (eventHolder == exitToMainButton) {
            clip.stop();
            parent.returnToMain();
        }
    }

    public void setParent (GameFrame parent) {
        this.parent = parent;
    }

    private void setLabel (String playerMarker, JLabel winnerImageLabel) {
        String victoryImage;
        ImageIcon winnerImage;
        if (playerMarker.equals("o")){
            victoryImage = "D:\\University\\Object Oriented Programming\\Semester Project\\src\\Assests\\Congrats P1.png";
            winnerImage = new ImageIcon(victoryImage);
        }
        else{
            victoryImage = "D:\\University\\Object Oriented Programming\\Semester Project\\src\\Assests\\Congrats P2.png";
            winnerImage = new ImageIcon(victoryImage);
        }
        winnerImageLabel.setIcon(winnerImage);
    }

    private void playWinMusic () {
        String soundFile = "D:\\University\\Object Oriented Programming\\Semester Project\\src\\Assests\\W!nner.wav";
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFile).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
        catch (Exception e) {}
    }

}
