package object;

import javax.imageio.ImageIO;

public class OBJ_blue_shield extends SuperObject{
    
    public OBJ_blue_shield(){
        type = 2;
        name = "blue shield";
        description = "[" + name + "]" + "\nscudo edizione limitata\nversione scudetto napoli 2023";
        defenceAttribute = 2;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/shield_blue.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
