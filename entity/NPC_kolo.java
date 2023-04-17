package entity;

import java.util.Random;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class NPC_kolo extends Entity{
    
    public NPC_kolo(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;
        getPlayerImage();
        setDialogue();
    }

    public void getPlayerImage(){       //legge le immagini del player

        try {
            
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/kolosiuk/koloup1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/npc/kolosiuk/koloup2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/res/npc/kolosiuk/koloup3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/kolosiuk/kolodown1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/npc/kolosiuk/kolodown2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/res/npc/kolosiuk/kolodown3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/kolosiuk/kololeft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/npc/kolosiuk/kololeft2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/res/npc/kolosiuk/kololeft3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/kolosiuk/koloright1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/npc/kolosiuk/koloright2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/res/npc/kolosiuk/koloright3.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public void setAction(){

        actionLockCounter ++;

        if(actionLockCounter == 120){

            Random random = new Random();
            int i = random.nextInt(100)+1; //random number from 1 to 100;

            if( i <= 25 ){
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

    public void setDialogue(){

        dialogues[0] = "Sono kolosiuk!";
        dialogues[1] = "Benvenuto nell'isola 4F";
        dialogues[2] = "se ni mondo ci fosse u poco di bene\ne ognuno si considerasse suo\nfratello...";
        dialogues[3] = "Scoprili Tutti";
    }

    public void speak(){

        super.speak();
        
    }

}
