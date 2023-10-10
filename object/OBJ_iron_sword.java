package object;

import javax.imageio.ImageIO;

public class OBJ_iron_sword extends SuperObject{
    
    public OBJ_iron_sword(){
        type = 1;
        name = "spada di ferro MKII";
        description = "[" + name + "]" + "\nuna spada di ferro più grossa\ntanto sappiamo che il\ntuo braccio destro è\npiù forte del sinistro";
        attackAttribute = 3;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/sword_iron.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
