package object;

import javax.imageio.ImageIO;

public class OBJ_sgabello extends SuperObject{
    
    public OBJ_sgabello(){

        name = "sgabello";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/sgabello.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = false;

    }

}
