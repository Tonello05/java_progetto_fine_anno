package object;
import javax.imageio.ImageIO;

import Main.GamePanel;
import entity.Entity;

public class OBJ_blue_pot extends SuperObject{
    
    public int value = 4; 
    GamePanel gp;
    public OBJ_blue_pot(GamePanel gp){
        type = 3;
        this.gp = gp;
        name = "blue potion";
        description = "[" + name + "]" + "\nuna semplice pozione curativa\nal 100% legale\ncura di " + value + "hp";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/blue_pot.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
    @Override
    public void use(Entity entity){
        if(entity.life + value < entity.maxLife){
            entity.life += value;
            gp.ui.showMessage("ti sei curato di " + value + "hp");
        }else if ( entity.life + value >= entity.maxLife){
            entity.life = entity.maxLife;
            gp.ui.showMessage("ti sei curato completamente");
        }
        gp.playSE(2);
    }

}
