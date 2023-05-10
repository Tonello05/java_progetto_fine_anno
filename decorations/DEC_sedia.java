package decorations;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Main.GamePanel;
import object.SuperObject;

public class DEC_sedia extends SuperObject{
    
    private BufferedImage image4;
    private String direction;
    GamePanel gp;
    public DEC_sedia(GamePanel gp, String direction){
        this.direction = direction;
        this.gp = gp;
        name = "decoration";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/decorations/chair_left.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/res/decorations/chair_right.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/res/decorations/chair_up.png"));
            image4 = ImageIO.read(getClass().getResourceAsStream("/res/decorations/chair_down.png"));
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
            switch (direction) {
                case "left":
                    g2.drawImage(image, screenX, screenY,  gp.tileSize, gp.tileSize, null);
                    break;
                case "right":
                g2.drawImage(image2, screenX, screenY,  gp.tileSize, gp.tileSize, null);
                break;
                case "up":
                g2.drawImage(image3, screenX, screenY,  gp.tileSize, gp.tileSize, null);
                break;
                case "down":
                g2.drawImage(image4, screenX, screenY,  gp.tileSize, gp.tileSize, null);
                break;
            }
            
        }


}


}
