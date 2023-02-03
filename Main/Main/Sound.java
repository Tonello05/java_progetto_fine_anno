package Main;
/*
 * Classe che gestisce l'audio
 */


import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound(){

        soundURL[0] = getClass().getResource("/res/sounds/BlueBoyAdventure.wav");
        soundURL[1] = getClass().getResource("/res/sounds/coin.wav");
        soundURL[2] = getClass().getResource("/res/sounds/powerup.wav");
        soundURL[3] = getClass().getResource("/res/sounds/unlock.wav");
        soundURL[4] = getClass().getResource("/res/sounds/fanfare.wav");

    }

    public void setFile(int i){     //ottiene l'audio da riprodurre

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void play(){     //riproduce l'audio una volta

        clip.start();

    }

    public void loop(){     //riproduce l'audio in loop

        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

    public void stop(){     //ferma l'audio

        clip.stop();

    }

}
