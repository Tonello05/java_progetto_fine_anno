/*  |SCARABEO|
 *  vita:       12
 *  velocità:   3
 *  danno :     7
 *  drop:       20 coin
 */
package entity;

import java.util.Random;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class MON_scarabeo extends Entity{
    
    //DROP del nemico
    int coin;

    public MON_scarabeo(GamePanel gp){
        super(gp);

        //Specifiche del nemico
        direction = "up";   //direzione iniziale
        name = "scarabeo";    //nome del nemico
        speed = 3;      //velocità del nemico
        maxLife = 12;    //vita massima del nemico
        life = maxLife; //vita attuale del nemico
        coin = 20;   //monete del drop del nemico
        damage = 7; //danno del nemico (il player inizia di base con difesa = 1)
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
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/scarab/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/scarab/up2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/scarab/up1.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/scarab/up2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/scarab/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/scarab/left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/scarab/left3.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/scarab/left4.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/scarab/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/scarab/right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/scarab/right3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/scarab/right4.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/scarab/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/scarab/down2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/scarab/down1.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/scarab/down2.png"));

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
