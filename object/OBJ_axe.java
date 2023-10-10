package object;

import javax.imageio.ImageIO;

public class OBJ_axe extends SuperObject{
    
    public OBJ_axe(){
        type = 1;
        name = "ascia";
        description = "[" + name + "]" + "\nIn minecraft l'ascia fa' piu' danno\ne a noi piace minecraft";
        attackAttribute = 2;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/axe.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
