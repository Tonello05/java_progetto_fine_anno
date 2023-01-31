package entity;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Entity {
    
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1,up2,up3,left1,left2,left3,right1,right2,right3,down1,down2,down3;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;//frame number
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionIsOn = false;
    
}
