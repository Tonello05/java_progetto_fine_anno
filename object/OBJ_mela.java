package object;

import javax.imageio.ImageIO;

import Main.GamePanel;
import entity.Entity;

public class OBJ_mela extends SuperObject{
    
    
    public int value = 5; 
    GamePanel gp;
    public OBJ_mela(GamePanel gp){
        type = 3;
        this.gp = gp;
        name = "mela di kolosiuk";
        description = "[" + name + "]" + "\nuna mela di una gustosità\nincredibile, la provenienza\nè sconosciuta, cura di " + value + "hp";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/melaKolo.png"));
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
