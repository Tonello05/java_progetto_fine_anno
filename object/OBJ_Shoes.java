package object;

import javax.imageio.ImageIO;

/**
 * OBJ_Shoes
 */
public class OBJ_Shoes extends SuperObject{
    
    public OBJ_Shoes(){

        name = "shoes";     //nome della classe

        try {   //ottiene immagine dell'oggetto
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/Shoes.png")); 
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}