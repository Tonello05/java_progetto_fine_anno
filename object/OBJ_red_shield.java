package object;

import javax.imageio.ImageIO;

public class OBJ_red_shield extends SuperObject{
    
    public OBJ_red_shield(){
        type = 2;
        name = "social credit prize";
        description = "[" + name + "]" + "\nscudo rosso\nnon presenta microspie cinesi\n(si consiglia di non guardare\nwinnie the poh)";
        defenceAttribute = 3;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/red_shield.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
