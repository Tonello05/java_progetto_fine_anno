package entity;

import java.util.Random;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class NPC_thenerd extends Entity{

    public NPC_thenerd(GamePanel gp){
        super (gp);

        direction = "down"; //direzione iniziale dell'npc
        speed = 1;      //velocità dell'npc
        getPlayerImage();
        setDialogue();
        name = "Jhon Cena";
        type = 1;   //tipo di entity ( 1 = npc)
        defaultDirection = "down";
        noMovement = true;
    }

    public void getPlayerImage(){       //legge le immagini del player

        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/thenerd/TheNerdup1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/npc/thenerd/TheNerdup2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/res/npc/thenerd/TheNerdup3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/thenerd/TheNerddown1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/npc/thenerdTheNerddown2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/res/npc/thenerd/TheNerddown3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/thenerd/TheNerdleft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/npc/thenerd/TheNerdleft2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/res/npc/thenerd/TheNerdleft3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/thenerd/TheNerdright1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/npc/thenerd/TheNerdright2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/res/npc/thenerd/TheNerdright3.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setAction(){    //esegue un azione (per questo npc si limita a  farlo muovere a caso)

        if(onPath){

            int goalCol = 30;
            int goalRow = 8;
            searchPath(goalCol, goalRow);

        }else if (!noMovement){
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

    public void setDialogue(){      //dialoghi dell'npc

        dialogues[0] = "emh...in realtà...vorrei stare da soloh";
        dialogues[1] = "mh? vorresti ottenere da me i progresshi della quest? emh, in realtà... non lo soh...\nva bene dai, ma solo perché mi pari un nerd quanto me";
}

    public void speak(){    //dialogo con l'npc

        //eventuali azioni da fare durante il dialogo (per esempio curare a un certo dialogo)

        //DIALOGO
        super.speak();

        onPath = true;
        noMovement = true;
    }

}