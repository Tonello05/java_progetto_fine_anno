package object;

import javax.imageio.ImageIO;

public class OBJ_Shield_wood extends SuperObject{
    
    public OBJ_Shield_wood(){
        type = 2;
        name = "shield_wood";
        description = "[" + name + "]" + "\nThorin usava una quercia\nma noi non siamo poveri\ne usiamo delle assi di quercia";
        defenceAttribute = 1;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/shield_wood.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
