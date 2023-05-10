package decorations;

import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import Main.GamePanel;
import object.SuperObject;

public class DEC_sgabello extends SuperObject{
    public int value = 5; 
    GamePanel gp;
    public DEC_sgabello(GamePanel gp){
        this.gp = gp;
        name = "decoration";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/decorations/sgabello.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2, GamePanel gp){  //disegna l'oggetto sullo schermo

    int screenX = worldX - gp.player.worldX + gp.player.screenX;
    int screenY = worldY - gp.player.worldY + gp.player.screenY;

    if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
        worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
        worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
        worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                g2.drawImage(image, screenX, screenY,  gp.tileSize, gp.tileSize, null);            
        }


}


}
