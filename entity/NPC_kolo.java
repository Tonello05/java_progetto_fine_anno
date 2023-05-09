package entity;

import java.util.Random;

import javax.imageio.ImageIO;

import Main.GamePanel;
import object.OBJ_focaccia;
import object.OBJ_mela;

public class NPC_kolo extends Entity{

    public NPC_kolo(GamePanel gp){
        super(gp);

        direction = "down"; //direzione iniziale dell'npc
        speed = 1;      //velocità dell'npc
        getPlayerImage();   
        setDialogue();
        name = "kolosiuk";
        type = 1;   //tipo di entity ( 1 = npc)
        defaultDirection = "down";
        noMovement = true;
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

    public void setDialogue(){      //dialoghi dell'npc

        dialogues[0] = "Vuoi Mela?";
        dialogues[1] = "Focaccia olive?";
        dialogues[2] = "Tieni";
    }

    public void speak(){    //dialogo con l'npc

        //eventuali azioni da fare durante il dialogo (per esempio curare a un certo dialogo)
        
        if(dialogueIndex == 2){
            int i = new Random().nextInt(100);
            if(i > 95){
                if(gp.player.inventory.size() < 20){
                    gp.player.inventory.add(new OBJ_focaccia(gp));
                }

            }else{
                if(gp.player.inventory.size() < 20){
                    gp.player.inventory.add(new OBJ_mela(gp));
                }
            }
        }

        //DIALOGO
        super.speak();
    }

}
