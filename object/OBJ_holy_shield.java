package object;

import javax.imageio.ImageIO;

public class OBJ_holy_shield extends SuperObject{
    
    public OBJ_holy_shield(){
        type = 2;
        name = "santino di san gennaro";
        description = "[" + name + "]" + "\nuno scudo rimasto sacro\nanche dopo le\nbestemmie";
        defenceAttribute = 4;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/holy_shield.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
