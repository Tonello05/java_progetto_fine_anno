package entity;

import java.util.Random;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class NPC_miotto extends Entity{

    public NPC_miotto(GamePanel gp){
        super(gp);

        direction = "left"; //direzione iniziale dell'npc
        speed = 1;      //velocità dell'npc
        getPlayerImage();   
        setDialogue();
        name = "Miotto";
        type = 1;   //tipo di entity ( 1 = npc)
        defaultDirection = "left";
        noMovement = true;
    }

    public void getPlayerImage(){       //legge le immagini del player

        try {
            
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/miotto/miotto2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/miotto/miotto0.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/miotto/miotto3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/miotto/miotto1.png"));

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

        dialogues[0] = "...";
        dialogues[1] = "...";
        dialogues[2] = "...";
        dialogues[3] = "...";
        dialogues[4] = "...";
    }

    public void speak(){    //dialogo con l'npc

        //eventuali azioni da fare durante il dialogo (per esempio curare a un certo dialogo)
        


        //DIALOGO
        super.speak();

        if(dialogueIndex==5){
            gp.npc[1]=null;
        }
    }

}
