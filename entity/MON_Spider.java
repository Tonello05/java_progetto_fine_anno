
package entity;

import java.util.Random;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class MON_Spider extends Entity{
    
    //DROP del nemico
    int coin;

    public MON_Spider(GamePanel gp){
        super(gp);

        //Specifiche del nemico
        direction = "up";   //direzione iniziale
        name = "spider";    //nome del nemico
        speed = 1;      //velocità del nemico
        maxLife = 4;    //vita massima del nemico
        life = maxLife; //vita attuale del nemico
        coin = 4;   //monete del drop del nemico
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
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/spider/SpiderWalkingUp1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/spider/SpiderWalkingUp2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/spider/SpiderWalkingUp3.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/spider/SpiderWalkingUp4.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/spider/spiderWalkinleft0.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/spider/spiderWalkinleft1.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/spider/spiderWalkinleft2.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/spider/spiderWalkinleft3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/spider/SpiderWalkingSide1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/spider/SpiderWalkingSide2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/spider/SpiderWalkingSide3.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/spider/SpiderWalkingSide4.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/spider/SpiderWalking1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/spider/SpiderWalking2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/spider/SpiderWalking3.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/res/enemy/spider/SpiderWalking4.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void setAction(){    //azione del nemico

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

    //codice eseguito quando il nemico è morto
    public void killed(){

        gp.player.coins += coin;
        gp.ui.showMessage("ottenute " + coin + " monete ");

    }

    @Override
    public void damageReaction(){

        actionLockCounter = 0;
        direction = gp.player.direction;

    }

}
