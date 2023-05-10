package decorations;

import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import Main.GamePanel;
import object.SuperObject;

public class DEC_lamp extends SuperObject{
    
    private int spriteNum;
    public int value = 5; 
    GamePanel gp;
    public DEC_lamp(GamePanel gp, int spriteNum){
        this.spriteNum = spriteNum;
        this.gp = gp;
        name = "decoration";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/decorations/red_lamp.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/res/decorations/blue_lamp.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/res/decorations/green_lamp.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }

    @Override
    public void draw(Graphics2D g2, GamePanel gp){  //disegna l'oggetto sullo schermo

    int screenX = worldX - gp.player.worldX + gp.player.screenX;
    int screenY = worldY - gp.player.worldY + gp.player.screenY;

    if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
        worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
        worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
        worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
            if(spriteNum == 0){
                g2.drawImage(image, screenX, screenY,  gp.tileSize, gp.tileSize, null);
            }else if(spriteNum == 1){
                g2.drawImage(image2, screenX, screenY,  gp.tileSize, gp.tileSize, null);
            }else{
                g2.drawImage(image3, screenX, screenY,  gp.tileSize, gp.tileSize, null);
            }
            
        }


}


}
