package Front_End;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Music {
    private Clip gameBackgroundMusic, victoryMusic;

    public void playGameBackgroundMusic() {
        String soundFile = "D:\\University\\Object Oriented Programming\\Semester Project\\src\\Assests\\In_game.wav";
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
        String soundFile = "D:\\University\\Object Oriented Programming\\Semester Project\\src\\Assests\\Move.wav";
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFile).getAbsoluteFile());
            Clip moveSound = AudioSystem.getClip();
            moveSound.open(audioInputStream);
            moveSound.start();
        } catch (Exception e) {
        }
    }

    public void playVictoryMusic() {
        String soundFile = "D:\\University\\Object Oriented Programming\\Semester Project\\src\\Assests\\W!nner.wav";
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
        String soundFile = "D:\\University\\Object Oriented Programming\\Semester Project\\src\\Assests\\Win_Sound.wav";
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFile).getAbsoluteFile());
            Clip winSound = AudioSystem.getClip();
            winSound.open(audioInputStream);
            winSound.start();
        } catch (Exception e) {
        }
    }
}
