/*
 * Classe che gestisce il player
 */

package entity;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

import Main.GamePanel;
import Main.KeyHadler;

public class Player extends Entity{
    
    KeyHadler keyH;

    public int screenX;
    public int screenY;

    //GAME ITEMS VARIABLES
    public int hasKey = 0;
    public int coins = 0;

    public Player(GamePanel gp, KeyHadler keyH){    //contruttore e setup del player

        super(gp);
        this.gp=gp;
        this.keyH=keyH;
        screenX = gp.screenWhidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width=32;
        solidArea.height=32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){     //imposta alcuni valori predefiniti

        worldX=gp.tileSize * 10;
        worldY=gp.tileSize * 10;
        speed=4;
        direction="down";
    }

    public void getPlayerImage(){       //legge le immagini del player

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

    public void update(){       //aggiorna la posizione del player

        screenX = gp.getWidth()/2 - (gp.tileSize/2);
        screenY = gp.getHeight()/2 - (gp.tileSize/2);

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

        //Check object collision
        int object_index = gp.cChecker.checkObject(this, true);
        pickUpObject(object_index);

        //check NPCs collision
        int npcIndex = gp.cChecker.checEntity(this, gp.npc);
        intercatNPC(npcIndex);

        //if collision is false player can move

        if (collisionIsOn == false ) {

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

    public void intercatNPC(int index){

        if(index != 999){
            
            if(keyH.enterPressed){
                gp.gameState = GamePanel.dialogueState;
                gp.npc[index].speak();
            }
        }

    }

    public void pickUpObject(int index){    //gestisce le interazioni con gli oggetti

        if(index!=999){

            String objectName = gp.obj[index].name;

            switch (objectName) {
                case "key":
                        hasKey ++;
                        gp.obj[index] = null;
                        gp.playSE(1);
                        gp.ui.showMessage("you got a key!");
                    break;
            
                case "door":

                    if (hasKey > 0 && gp.obj[index].collision) {
                        hasKey--;
                        gp.playSE(3);
                        gp.ui.showMessage("you opened a door!");
                        try {
                            gp.obj[index].image = ImageIO.read(getClass().getResourceAsStream("/res/objects/openedDoor.png"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        gp.obj[index].collision=false;
                        
                    }
                    if(hasKey < 1 && gp.obj[index].collision){
                        gp.ui.showMessage("you need a key!");
                    }

                    break;
                case "shoes":
                    gp.obj[index] = null;
                    this.speed +=2;
                    gp.playSE(2);
                    gp.ui.showMessage("speed up!");
                    break;

                case "chest":
                    gp.ui.gamefinished = true;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
                case "inps":
                    gp.obj[index] = null;
                    this.speed = speed/2;
                    gp.playSE(2);
                    coins = 104;
                    gp.ui.showMessage("hai ottenuto la 104!!!");

                    break;
                case "coin":
                    gp.obj[index] = null;
                    gp.playSE(1);
                    coins++;
                    break;
            }

        }

    }

    public void draw(Graphics2D g2){    //disegna il player sullo schermo

        BufferedImage image = null;

        

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
                if(keyH.upPressed == false){image=up3;}
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
                if(keyH.downPressed == false){image=down2;}
                
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
                if(keyH.leftPressed == false){image=left2;}
                
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
                if(keyH.rightPressed == false){image=right2;}
                
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }

}
