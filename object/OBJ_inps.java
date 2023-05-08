package object;

import javax.imageio.ImageIO;

public class OBJ_inps extends SuperObject{
    
    public OBJ_inps(){
        name = "inps";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/inps.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;

    }

}
