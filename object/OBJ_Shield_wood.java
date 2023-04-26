package object;

import javax.imageio.ImageIO;

public class OBJ_Shield_wood extends SuperObject{
    
    public OBJ_Shield_wood(){

        name = "shield_wood";
        defenceAttribute = 1;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/shield_wood.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
