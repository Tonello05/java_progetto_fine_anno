package object;

import javax.imageio.ImageIO;

public class OBJ_Sword_normal extends SuperObject{
    
    public OBJ_Sword_normal(){

        name = "normal_sword";
        attackAttribute = 1;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/sword_normal.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
