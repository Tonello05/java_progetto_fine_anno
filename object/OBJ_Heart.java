package object;

import javax.imageio.ImageIO;

import Main.UtilityTool;

public class OBJ_Heart extends SuperObject{

    public OBJ_Heart(){

        UtilityTool ut = new UtilityTool();

        name = "heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/heartFull.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/res/objects/heartHalf.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/res/objects/heartBlank.png"));
            
            image = ut.scaleImage(image, 16, 16);
            image2 = ut.scaleImage(image2, 16, 16);
            image3 = ut.scaleImage(image3, 16, 16);

        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;

    } 
}
