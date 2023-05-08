package object;

import javax.imageio.ImageIO;

/**
 * OBJ_coin
 */
public class OBJ_coin extends SuperObject{

    public OBJ_coin(){

        name = "coin";

        try {
            
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/coin.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}