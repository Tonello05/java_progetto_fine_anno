package entity;

import java.util.Random;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class NPC_wen extends Entity{

    public NPC_wen(GamePanel gp){
        super(gp);

        direction = "down"; //direzione iniziale dell'npc
        speed = 0;      //velocità dell'npc
        getPlayerImage();   
        setDialogue();
        name = "wen";
        type = 1;   //tipo di entity ( 1 = npc)
        defaultDirection = "down";
        noMovement = true;
    }

    private void getPlayerImage(){       //legge le immagini del player

        try {
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/wen/wen_down1.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public void setAction(){    //esegue un azione

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

    private void setDialogue(){      //dialoghi dell'npc

        dialogues[0] = "Benvenuto nel mio Negozio Cinese!";
        dialogues[1] = "mi raccomando non RUBARE!!!";
        dialogues[2] = "ho questa incredibile spada d'oro, te la vendo\n a 999 4F coin";
        dialogues[3] = "Cosa? hai solo " + gp.player.coins + " 4F coin?\n che povero di m***a!!!";
        dialogues[4] = "ah... hai abbastanza monete? hai usato i CHEAT!!!, \ni programmatori non avevano voglia di fare un\n sistema di negozi quindi ora non so che fare...";
    }

    public void speak(){    //dialogo con l'npc

        //eventuali azioni da fare durante il dialogo (per esempio curare a un certo dialogo)

        if(dialogueIndex == 3){
            dialogues[3] = "Cosa? hai solo " + gp.player.coins + " 4F coin?\n che povero di m***a!!!";
            if(gp.player.coins >= 999){
                dialogueIndex = 4;
            }
        }

        //DIALOGO
        super.speak(dialogueIndex);
        if(dialogueIndex < 3){
            dialogueIndex ++;
        }
    }

}
