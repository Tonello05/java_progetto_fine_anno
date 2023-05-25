package entity;

import java.util.Random;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class NPC_doganiere extends Entity{

    public NPC_doganiere(GamePanel gp){
        super(gp);

        direction = "up"; //direzione iniziale dell'npc
        speed = 1;      //velocità dell'npc
        getPlayerImage();   
        setDialogue();
        name = "doganiere";
        type = 1;   //tipo di entity ( 1 = npc)
        defaultDirection = "up";
        noMovement = true;
    }

    public void getPlayerImage(){       //legge le immagini del player

        try {
            
            up1 = ImageIO.read(getClass().getResourceAsStream("res/npc/doganiere/doganiere.png"));

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

        dialogues[0] = "chi siete?\ncosa fate?\ncosa portate?\n1 fiorino";

    }

    public void speak(){    //dialogo con l'npc

        //eventuali azioni da fare durante il dialogo (per esempio curare a un certo dialogo)
        
        if(gp.player.coins>=1){
            gp.player.coins--;
            gp.player.ha_Pagato=true;
            gp.player.dogana_min=gp.playtime_m;
            gp.player.dogana_sec=gp.playtime_s;
        }
        

        //DIALOGO
        super.speak();
    }

}
