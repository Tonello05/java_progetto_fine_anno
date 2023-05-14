/*  |VESPA|
 *  vita:       4
 *  velocità:   1
 *  danno :     3
 *  drop:       4 coin
 */

package entity;

import java.util.Random;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class MON_wasp extends Entity{
    //DROP del nemico
    int coin;

    public MON_wasp(GamePanel gp){
        super(gp);

        //Specifiche del nemico
        direction = "up";   //direzione iniziale
        name = "wasp";    //nome del nemico
        speed = 3;      //velocità del nemico
        maxLife = 4;    //vita massima del nemico
        life = maxLife; //vita attuale del nemico
        coin = 4;   //monete del drop del nemico
        damage = 3; //danno del nemico (il player inizia di base con difesa = 1)
        fly = true; //se l nemico è volante le animazioni sono diverse
        //HITBOX
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        //ALTRO
        type = 2;   //tipo dell'entity (2 = enemy)
        getImage();

    }

    public void getImage(){     //carica gli sprites del nemico
        try {

            //sprites movimento
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/wasp/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/wasp/up2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/wasp/up1.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/wasp/up2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/wasp/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/wasp/left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/wasp/left1.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/wasp/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/wasp/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/wasp/right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/wasp/right1.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/wasp/right2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/wasp/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/wasp/down2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/wasp/down1.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/wasp/down2.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void setAction(){    //azione del nemico

 
        if(onPath){     //se aggrato segue il player
            
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
            searchPath(goalCol, goalRow);

        }else if (!noMovement){ //se non è aggrato si muove a random
            actionLockCounter ++;   //aumenta di 1 ogni frame

            if(actionLockCounter == 120){   //ogni 120 frame cambia la direzione in cui sta camminando
    
                Random random = new Random();   
                int i = random.nextInt(100)+1; //numero a caso tra 1 a 100
    
                if( i <= 25 ){  //seleziona una direzione a caso
                    direction = "up";
                }else if( i > 25 && i <=50 ){
                    direction = "down";
                }else if( i > 50 && i <=75 ){
                    direction = "right";
                }else if(i > 75){
                    direction = "left";
                }
    
                actionLockCounter = 0;
    
            }
        }
    }

    @Override
    public void update(){

        super.update();
         
        //se il nemico è vicino al player potrebbe aggrarsi

        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance)/gp.tileSize;
        if(!onPath && tileDistance <5){
            int i = new Random().nextInt(100)+1;
            if(i>50){onPath = true;}
            hpBarOn = true;
            hpBArCounter = 0;
        }
        
        //sel il nemico è lontano dal player non è più aggrato
        if(onPath && tileDistance > 20){
            onPath = false;
            hpBarOn = false;
            hpBArCounter = 0;
        }

        if(spriteCounter < 10){
            spriteNum = 2;
        }else if(spriteCounter < 20){
            spriteNum = 1;
        }else{
            spriteCounter = 0;
        }


    }

    //codice eseguito quando il nemico è morto
    public void killed(){

        gp.player.coins += coin;
        gp.ui.showMessage("ottenute " + coin + " monete ");

    }

    @Override
    public void damageReaction(){

        actionLockCounter = 0;
        onPath = true;

    }

}
