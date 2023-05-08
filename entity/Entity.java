/*
 * Classe Padre di tutte le entita (NPC, Player, Nemici)
 * 
 * Per info sulla creazione di un NPC guarda la classe NPC_kolo
 * Per info sulla creazione di un nemico guarda la classe MON_Spider
 */

package entity;

import java.awt.image.BufferedImage;

import Main.GamePanel;
import object.SuperObject;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Entity {
    
    public int worldX, worldY;  //posizione entità nella mapa
    //Game Panel
    GamePanel gp;

    //CHARACTER STATUS
    public int maxLife;     //vita massima
    public int life;    //vita attuale
    public boolean invincible = false;  //invicibilità attiva
    public int invincibleCounter = 0;   //contatore tempo invincibilità
    public int speed;   //velocità
    public String name;     //nome dell'entita
    public int type;    // 0 = player; 1 = npc; 2 = enemy
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;
    int hpBArCounter = 0;
    public int damage;  //dannno dell'entità
    public int defence; //difesa dell'entità
    public int strenght;    //più ne ha più danno fa
    public int dexterity;   //più ne ha meno danno riceve
    public SuperObject currentWeapon;
    public SuperObject currentShield;

    //Variabili per le immagini
    public BufferedImage up1,up2,up3,up4,left1,left2,left3,left4,right1,right2,right3,right4,down1,down2,down3,down4;  //sprites dell'entita
    public BufferedImage attackUp, attackDown, attackLeft, attackRight;
    public String direction;    //direzione corrente dell'entita
    public int spriteCounter = 0;   //contatore per gli sprite (server nel metodo draw )
    public int spriteNum = 1;   //frame number
    public int dyingCounter = 0;

    //variabili HITBOX
    public Rectangle solidArea = new Rectangle(0,0,48,48);  //HitBox dell'entità
    public Rectangle attackArea = new Rectangle(0,0,0,0);  //HitBox dell'attacco
    public int solidAreaDefaultX, solidAreaDefaultY;    //altre variabili per le hitbox
    public boolean collisionIsOn = false;   //true se l'entità è in collisione con qualcosa
    
    //ALTRO
    public boolean noMovement = false;  //se messa a true l'entità non eseguira azioni
    public String dialogues[] = new String[20]; //array dei dialoghi dell'npc
    public int dialogueIndex = 0;   //contatore dei dialoghi
    public boolean attacking = false;   //indica se l'entita sta attaccando
    public int actionLockCounter = 0;      //contatore per far eseguire a un npc un azione
    public boolean onPath = false;      //se l'entità  sta seguendo un percorso
    public String defaultDirection;     //direzione di default

    public Entity(GamePanel gp){
        this.gp=gp;
    }

    public void killed(){}      //metodo che gestirà la morte dei nemici
    public void setAction(){}   //servirà negli oggetti npc per impostare le suo azioni predefinite
    public void damageReaction(){} //reazione dei nemic al danno

    public void checkCollision(){
        //controlla le collisioni varie
        collisionIsOn = false;
        gp.cChecker.checkTile(this);    //tiles
        gp.cChecker.checkObject(this, false);   //oggetti
        gp.cChecker.checEntity(this, gp.npc);   //npc
        gp.cChecker.checEntity(this, gp.enemy); //nemici
        gp.cChecker.checkPalyer(this);  //player
    }

    public void update(){   //calcola posizione e eventuali collisioni dell'npc

        setAction();
        checkCollision();

        if (collisionIsOn == false && (onPath || !noMovement)) {

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
        }else if (type == 1 && !onPath){   //se un npc è fermo prende una direzione di default
            direction = defaultDirection;
        }

        if(spriteCounter>10){
            if(spriteNum==1){spriteNum=2;}
            else if(spriteNum==2){spriteNum=3;}
            else if(spriteNum==3){spriteNum=1;}
            spriteCounter=0;
            
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

                //disegna la barra della vita
                if(type == 2 && hpBarOn && !dying){

                    double oneScale = (double)gp.tileSize/maxLife;
                    double hpBarValue = oneScale * life;

                    g2.setColor(new Color(35,35,35));
                    g2.fillRect(screenX-1, screenY-16, gp.tileSize+2, 12);
                    g2.setColor(new Color(255,0,30));
                    g2.fillRect(screenX, screenY-15, (int)hpBarValue, 10);

                    hpBArCounter ++;
                    if( hpBArCounter > 600){
                        hpBArCounter = 0;
                        hpBarOn = false;
                    }
                }


                if(invincible && invincibleCounter % 2 == 0){
                    changeAlpha(g2, 0.2f);
                    hpBarOn = true;
                    hpBArCounter = 0;
                }

                if(dying == true){
                    dyingAnimation(g2);
                }

                g2.drawImage(image, screenX, screenY,  gp.tileSize, gp.tileSize, null);

                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            }


    }

    public void dyingAnimation(Graphics2D g2){

        dyingCounter++;

        int i = 5;
        if(dyingCounter <= i){changeAlpha(g2, 0);}
        if(dyingCounter > i && dyingCounter <= i*2){changeAlpha(g2, 1);}
        if(dyingCounter > i*2 && dyingCounter <= i*3){changeAlpha(g2, 0);}
        if(dyingCounter > i*3 && dyingCounter <= i*4){changeAlpha(g2, 1);}
        if(dyingCounter > i*4 && dyingCounter <= i*5){changeAlpha(g2, 0);}
        if(dyingCounter > i*5 && dyingCounter <= i*6){changeAlpha(g2, 1);}
        if(dyingCounter > i*6 && dyingCounter <= i*7){changeAlpha(g2, 0);}
        if(dyingCounter > i*7 && dyingCounter <= i*8){changeAlpha(g2, 1);}

        if(dyingCounter > i*8){
            dying = false;
            alive = false;
            killed();
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
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
    
    public void searchPath(int goalCol,int GoalRow){

        int startCol = (worldX +solidArea.x)/gp.tileSize;
        int startRow = (worldY +solidArea.y)/gp.tileSize;

        gp.pFinder.setNodes(startCol, startRow, goalCol, GoalRow);

        

        if(gp.pFinder.search()){
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            //ENTITY HITBOX
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "up";
        
            }
            else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize){
                direction = "down";
            }
            else if(enTopY > nextY && enBottomY < nextY + gp.tileSize ){    //left o right
                if(enLeftX > nextX){
                    direction = "left";
                }
                if(enLeftX < nextX){
                    direction = "right";
                }
            }
            else if(enTopY > nextY && enLeftX > nextX){
                direction = "up";
                checkCollision();
                if(collisionIsOn){
                    direction = "left";
                }
            }
            else if(enTopY > nextY && enLeftX < nextX){
                direction = "up";
                checkCollision();
                if(collisionIsOn){
                    direction = "right";
                }
            }
            else if(enTopY < nextY && enLeftX > nextX){
                direction = "down";
                checkCollision();
                if(collisionIsOn){
                    direction = "left";
                }
            }
            else if(enTopY < nextY && enLeftX < nextX){
                direction = "down";
                checkCollision();
                if(collisionIsOn){
                    direction = "right";
                }
            }

            int nextCol = gp.pFinder.pathList.get(0).col;
            int nextRow = gp.pFinder.pathList.get(0).row;

            if(nextCol == goalCol && nextRow == GoalRow){
                onPath = false;
            }

        }

    }

}