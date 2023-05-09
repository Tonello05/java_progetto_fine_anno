package object;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_stampella extends SuperObject{
    
	GamePanel gp;

    public OBJ_stampella(GamePanel gp){
        this.gp=gp;
		type = 1;
        name = "stampella";
        description = "[" + name + "]" + "\nabbiamo visto che sei morto\nforse questa ti\npuo' servire";
        attackAttribute = 6;
        
		try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/stampella.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

		

    }

}
