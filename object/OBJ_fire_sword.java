package object;

import javax.imageio.ImageIO;

public class OBJ_fire_sword extends SuperObject{
    
    public OBJ_fire_sword(){
        type = 1;
        name = "spada fire aspect II";
        description = "[" + name + "]" + "\nla spada piu' forte\n(attenzione!, questa spada\n potrebbe causare ustioni\ne colpi di calore al portatore)";
        attackAttribute = 4;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/sword_fire.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
