package object;

import javax.imageio.ImageIO;

import Main.GamePanel;
import entity.Entity;

public class OBJ_focaccia extends SuperObject{
    
    
    public int value = 100; 
    GamePanel gp;
    public OBJ_focaccia(GamePanel gp){
        type = 3;
        this.gp = gp;
        name = "focaccia di kolosiuk";
        description = "[" + name + "]" + "\nla fantasmagorica foccia di kolo,\ntienila da conto poiché\nè rarissima\ncura di " + value + "hp!!!";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/focaccia.png"));
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
