/*
 * Classe Padre di tutte le entita (NPC, Player, Nemici)
 */

package entity;

import java.awt.image.BufferedImage;

import Main.GamePanel;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Entity {
    
    public int worldX, worldY;  //posizione entità nella mapa
    public int speed;   //velocità

    GamePanel gp;
    public BufferedImage up1,up2,up3,up4,left1,left2,left3,left4,right1,right2,right3,right4,down1,down2,down3,down4;  //sprites dell'entita
    public BufferedImage attackUp, attackDown, attackLeft, attackRight;
    public String direction;    //direzione corrente dell'entita
    public int spriteCounter = 0;   //contatore per gli sprite (server nel metodo draw )
    public int spriteNum = 1;   //frame number
    public Rectangle solidArea = new Rectangle(0,0,48,48);  //HitBox dell'entità
    public Rectangle attackArea = new Rectangle(0,0,0,0);  //HitBox dell'attacco
    public int solidAreaDefaultX, solidAreaDefaultY;    //altre variabili per le hitbox
    public boolean collisionIsOn = false;   //true se l'entità è in collisione con qualcosa
    public int actionLockCounter = 0;      //contatore per far eseguire a un npc un azione
    public boolean noMovement = false;  //se messa a true l'entità non eseguira azioni
    public String dialogues[] = new String[20]; //array dei dialoghi dell'npc
    public int dialogueIndex = 0;   //contatore dei dialoghi
    public String name;     //nome dell'npc
    public int type;    // 0 = player; 1 = npc; 2 = enemy
    public boolean attacking = false;   //indica se l'entita sta attaccando

    //CHARACTER STATUS
    public int maxLife;
    public int life;
    public boolean invincible = false;
    public int invincibleCounter = 0;

    public Entity(GamePanel gp){
        this.gp=gp;
    }
    public void setAction(){}   //servirà negli oggetti npc per impostare le suo azioni predefinite

    public void update(){   //calcola posizione e eventuali collisioni dell'npc

        if(noMovement == false){

            setAction();

            collisionIsOn = false;
            gp.cChecker.checkTile(this);
            gp.cChecker.checkObject(this, false);
            gp.cChecker.checEntity(this, gp.npc);
            gp.cChecker.checEntity(this, gp.enemy);

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

        if(invincible){
            invincibleCounter++;
            if(invincibleCounter > 40){invincible = false; invincibleCounter = 0;}
        }

    }

    public void draw(Graphics2D g2){    //disegna l'npc
        
        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){

                switch (direction) {
                    case "up":
                        if(gp.gameState == GamePanel.pauseState){spriteNum=3;}
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
                        if(gp.gameState == GamePanel.pauseState){spriteNum=2;}
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
                        if(gp.gameState == GamePanel.pauseState){spriteNum=2;}
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
                        if(gp.gameState == GamePanel.pauseState){spriteNum=2;}
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

                if(invincible && invincibleCounter % 2 == 0){
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
                }

                g2.drawImage(image, screenX, screenY,  gp.tileSize, gp.tileSize, null);

                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            }


    }

    public void speak(){    //metodo che serve per i dialoghi

        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue =  name + ": " + dialogues[dialogueIndex];
        
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
