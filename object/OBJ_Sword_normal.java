package object;

import javax.imageio.ImageIO;

public class OBJ_Sword_normal extends SuperObject{
    
    public OBJ_Sword_normal(){
        type = 1;
        name = "normal sword";
        description = "[" + name + "]" + "\nspada comprata dal marocchino\nin spiaggia, non fa' molto danno\nma e' resistente come i\nbracciletti dei wucumpra'";
        attackAttribute = 1;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/sword_normal.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
