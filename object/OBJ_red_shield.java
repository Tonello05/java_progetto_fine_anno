package object;

import javax.imageio.ImageIO;

public class OBJ_red_shield extends SuperObject{
    
    public OBJ_red_shield(){
        type = 2;
        name = "holy shield";
        description = "[" + name + "]" + "\nuno scudo rosso\nmolto potente";
        defenceAttribute = 3;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/red_shield.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
