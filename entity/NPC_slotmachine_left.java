package entity;

import java.util.Random;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class NPC_slotmachine_left extends Entity{

    public NPC_slotmachine_left(GamePanel gp){
        super(gp);

        direction = "left"; //direzione iniziale dell'npc
        speed = 1;      //velocità dell'npc
        getPlayerImage();   
        setDialogue();
        name = "slot machine";
        type = 1;   //tipo di entity ( 1 = npc)
        defaultDirection = "left";
        noMovement = true;
    }

    public void getPlayerImage(){       //legge le immagini del player

        try {
            
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/objects/slotmachine_left.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/objects/slotmachine_left.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/objects/slotmachine_left.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/objects/slotmachine_left.png"));

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

        dialogues[0] = "\n\ncosto 4 coins";
        dialogues[1] = "metti la monetina\ne tira la leva";
        dialogues[2] = "ritenta, sarai più fortunato";
        dialogues[3] = "";
    }

    public void speak(){    //dialogo con l'npc

        //eventuali azioni da fare durante il dialogo (per esempio curare a un certo dialogo)
        
        int ris1,ris2,ris3;

        ris1 =(int) (Math.random() * 5);
        ris2 =(int) (Math.random() * 5);
        ris3 =(int) (Math.random() * 5);

        if(dialogueIndex == 2){

            gp.player.coins = gp.player.coins - 4;

            dialogues[2] = "risultati: " + ris1 + " " + ris2 + " " + ris3 + "\nritenta, sarai più fortunato";

            if(ris1 == ris2 && ris1 == ris3 && ris2 == ris3){

                dialogueIndex=3;
                dialogues[3] = "risultati: " + ris1 + " " + ris2 + " " + ris3 + "\nHAI VINTO!!!";

            }
        }

        //DIALOGO
        super.speak(dialogueIndex);
        dialogueIndex++;
        if(dialogueIndex == 3 || dialogueIndex ==4 || gp.player.coins < 4){
            dialogueIndex=0;
        }
    }

}
