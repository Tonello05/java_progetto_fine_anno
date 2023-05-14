package Main;
/*
 * Classe che gestisce l'audio
 */


import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    
    private Clip clip;  //classe che gestisce l'audio
    private URL soundURL[] = new URL[30];   //array di audio

    public Sound(){ //inizializzazione degli audio

        soundURL[0] = getClass().getResource("/res/sounds/BlueBoyAdventure.wav");
        soundURL[1] = getClass().getResource("/res/sounds/coin.wav");
        soundURL[2] = getClass().getResource("/res/sounds/powerup.wav");
        soundURL[3] = getClass().getResource("/res/sounds/unlock.wav");
        soundURL[4] = getClass().getResource("/res/sounds/fanfare.wav");
        soundURL[5] = getClass().getResource("/res/sounds/cursor.wav");
        soundURL[6] = getClass().getResource("/res/sounds/menuTheme.wav");
        soundURL[7] = getClass().getResource("/res/sounds/receivedamage.wav");
        soundURL[8] = getClass().getResource("/res/sounds/hitmonster.wav");
        soundURL[9] = getClass().getResource("/res/sounds/swingSword.wav");

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
