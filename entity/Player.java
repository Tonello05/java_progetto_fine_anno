package entity;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

import Main.GamePanel;
import Main.KeyHadler;

public class Player extends Entity{
    
    GamePanel gp;
    KeyHadler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHadler keyH){

        this.gp=gp;
        this.keyH=keyH;
        screenX = gp.screenWhidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidArea.width=32;
        solidArea.height=32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){

        worldX=gp.tileSize * 5;
        worldY=gp.tileSize * 5;
        speed=4;
        direction="down";
    }

    public void getPlayerImage(){

        try {
            
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/up2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/res/player/up3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/down2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/res/player/down3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/res/player/left3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/res/player/right3.png"));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void update(){

        

        if(keyH.upPressed == true){
            direction="up";
        }
        if(keyH.downPressed){
            direction="down";
        }
        if(keyH.leftPressed){
            direction="left";
        }
        if(keyH.rightPressed){
            direction="right";
            
        }

        // Check player collision
        collisionIsOn = false;
        gp.cChecker.checkTile(this);

        //if collision is false player can move

        if (collisionIsOn == false) {
            
            switch (direction) {
                case "up":
                    if(keyH.upPressed){worldY -= speed;spriteCounter++;}
                    break;
            
                case "down":
                    if(keyH.downPressed){worldY += speed;spriteCounter++;}
                    break;
                case "left":
                    if (keyH.leftPressed){worldX -= speed;spriteCounter++;}
                    break;
    
                case "right":
                    if(keyH.rightPressed){worldX += speed;spriteCounter++;}
                    break;
            }

        }

        if(spriteCounter>10){
            if(spriteNum==1){spriteNum=2;}
            else if(spriteNum==2){spriteNum=3;}
            else if(spriteNum==3){spriteNum=1;}
            spriteCounter=0;
            
        }

    }

    public void draw(Graphics2D g2){

        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum==1) {
                    image=up1;
                }
                if (spriteNum==2) {
                    image=up2;
                }
                if (spriteNum==3) {
                    image=up3;
                }
                
                break;
            case "down":
                if (spriteNum==1) {
                    image=down1;
                }
                if (spriteNum==2) {
                    image=down2;
                }
                if (spriteNum==3) {
                    image=down3;
                }
                break;
            case "left":
                if (spriteNum==1) {
                    image=left1;
                }
                if (spriteNum==2) {
                    image=left2;
                }
                if (spriteNum==3) {
                    image=left3;
                }
                break;
            case "right":
                if (spriteNum==1) {
                    image=right1;
                }
                if (spriteNum==2) {
                    image=right2;
                }
                if (spriteNum==3) {
                    image=right3;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }

}
