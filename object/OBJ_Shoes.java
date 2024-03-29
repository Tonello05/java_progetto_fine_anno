package object;

import javax.imageio.ImageIO;

import entity.Entity;

/**
 * OBJ_Shoes
 */
public class OBJ_Shoes extends SuperObject{
    
    public OBJ_Shoes(){
        type = 3;
        name = "scarpe da corsa";     //nome della classe
        description = "[" + name + "]" + "\nle scarpe giallo zafferano\npermettono di raggiungere\nvelocità incredibili";
        try {   //ottiene immagine dell'oggetto
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/Shoes.png")); 
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void use(Entity entity){
        entity.defaultSpeed += 5;
    }

}