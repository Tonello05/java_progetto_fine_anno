package object;

import javax.imageio.ImageIO;

public class OBJ_Shield_wood extends SuperObject{
    
    public OBJ_Shield_wood(){
        type = 2;
        name = "shield_wood";
        description = "[" + name + "]" + "\nun vecchio scudo";
        defenceAttribute = 1;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/shield_wood.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
