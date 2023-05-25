package object;

import javax.imageio.ImageIO;

public class OBJ_iron_sword extends SuperObject{
    
    public OBJ_iron_sword(){
        type = 1;
        name = "iron sword";
        description = "[" + name + "]" + "\nspada di ferro un po' più\nforte delle precedenti";
        attackAttribute = 3;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/sword_iron.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
