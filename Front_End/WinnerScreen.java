package Front_End;

import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class WinnerScreen extends JPanel {
    private Image backGround;

    public WinnerScreen (String playerMarker) {
        //Set panel properties
        setBounds(0, 0, 1005, 845);
        setLayout(new BorderLayout());
        setVisible(false);

        //Initialise components
        backGround = new ImageIcon("D:\\University\\Object Oriented Programming\\Semester Project\\src\\Assests\\WinnerBG.png").getImage();

        //Set winner image
        JLabel winnerImageLabel = new JLabel();
        setLabel(playerMarker, winnerImageLabel);

        //Set music file and play winner music
        playMusic();

        //Add components
        add(winnerImageLabel, BorderLayout.EAST);
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        g.drawImage(backGround, 0, 0, this);
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

    private void playMusic () {
        String soundFile = "D:\\University\\Object Oriented Programming\\Semester Project\\src\\Assests\\W!nner.wav";
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFile).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
        catch (Exception e) {}
    }

}
