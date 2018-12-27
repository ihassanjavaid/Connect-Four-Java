package Front_End;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Music {
    private Clip welcomeScreenMusic, gameBackgroundMusic, victoryMusic, drawMusic;

    public static void playWelcomeScreenMusic () {
        String soundFile = "Assests/welcome.wav";
        try {
          AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFile).getAbsoluteFile());
          welcomeScreenMusic = AudioSystem.getClip();
          welcomeScreenMusic.open(audioInputStream);
          welcomeScreenMusic.start();
        }
        catch (Exception e) {}
    }

    public static void stopWelcomeScreenMusic () {
      welcomeScreenMusic.stop();
    }

    public void playGameBackgroundMusic() {
        String soundFile = "Assests\\In_game.wav";
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFile).getAbsoluteFile());
            gameBackgroundMusic = AudioSystem.getClip();
            gameBackgroundMusic.open(audioInputStream);
            gameBackgroundMusic.start();
        } catch (Exception e) {
        }
    }

    public void stopGameBackgroundMusic() {
        gameBackgroundMusic.stop();
    }

    public void playMoveSoundEffect() {
        String soundFile = "Assests\\Move.wav";
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFile).getAbsoluteFile());
            Clip moveSound = AudioSystem.getClip();
            moveSound.open(audioInputStream);
            moveSound.start();
        } catch (Exception e) {
        }
    }

    public void playVictoryMusic() {
        String soundFile = "Assests\\W!nner.wav";
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFile).getAbsoluteFile());
            victoryMusic = AudioSystem.getClip();
            victoryMusic.open(audioInputStream);
            victoryMusic.start();
        } catch (Exception e) {
        }
    }

    public void stopVictoryMusic() {
        victoryMusic.stop();
    }

    public void playWinSoundEffect () {
      String soundFile = "Assests\\Win_Sound.wav";
      try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFile).getAbsoluteFile());
        Clip winSound = AudioSystem.getClip();
        winSound.open(audioInputStream);
        winSound.start();
      } catch (Exception e) {
      }
    }

    public void playDrawMusic () {
      String soundFile = "Assests/Draw.wav";
      try {
        AudioInputStream audioInputStream = AudioS.getAudioInputStream(new File(soundFile).getAbsoluteFile());
        drawMusic = AudioSystem.getClip();
        drawMusic.open(audioInputStream);
        drawMusic.start();
      }
      catch (Exception e) {}
    }

    public void stopDrawMusic () {
      drawMusic.stop();
    }
}
