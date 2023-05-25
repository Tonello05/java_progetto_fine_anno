package object;

import javax.imageio.ImageIO;

public class OBJ_fire_sword extends SuperObject{
    
    public OBJ_fire_sword(){
        type = 1;
        name = "fire sword";
        description = "[" + name + "]" + "\nla spada più forte del reame";
        attackAttribute = 4;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/sword_fire.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
