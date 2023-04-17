/*
 * Classe Padre di tutte le entita (NPC, Player, Nemici)
 */

package entity;

import java.awt.image.BufferedImage;

import Main.GamePanel;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Entity {
    
    public int worldX, worldY;
    public int speed;

    GamePanel gp;
    public BufferedImage up1,up2,up3,left1,left2,left3,right1,right2,right3,down1,down2,down3;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;//frame number
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionIsOn = false;
    public int actionLockCounter = 0;
    public boolean noMovement = false;
    public String dialogues[] = new String[20];
    public int dialogueIndex = 0;

    public Entity(GamePanel gp){
        this.gp=gp;
    }
    public void setAction(){}

    public void update(){

        if(noMovement == false){

            setAction();

            collisionIsOn = false;
            gp.cChecker.checkTile(this);
            gp.cChecker.checkObject(this, false);
            gp.cChecker.checkPalyer(this);

            if (collisionIsOn == false ) {

                switch (direction) {
                    case "up":
                        worldY -= speed;spriteCounter++;
                        break;
                
                    case "down":
                        worldY += speed;spriteCounter++;
                        break;
                    case "left":
                        worldX -= speed;spriteCounter++;
                        break;
        
                    case "right":
                        worldX += speed;spriteCounter++;
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

    }

    public void draw(Graphics2D g2){
        
        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

                switch (direction) {
                    case "up":
                        if(gp.gameState == gp.pauseState){spriteNum=3;}
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
                        if(gp.gameState == gp.pauseState){spriteNum=2;}
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
                        if(gp.gameState == gp.pauseState){spriteNum=2;}
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
                        if(gp.gameState == gp.pauseState){spriteNum=2;}
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
                g2.drawImage(image, screenX, screenY,  gp.tileSize, gp.tileSize, null);
            }


    }

    public void speak(){

        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "right":
                direction = "left";
                break;
            case "left":
                direction = "right";
                break;
        }

    }
    
}
