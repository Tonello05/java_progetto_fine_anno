package entity;

import java.util.Random;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class NPC_jessepinkman extends Entity{

    public NPC_jessepinkman(GamePanel gp){
        super (gp);

        direction = "down"; //direzione iniziale dell'npc
        speed = 1;      //velocità dell'npc
        getPlayerImage();
        setDialogue();
        name = "Jessie Pinkman";
        type = 1;   //tipo di entity ( 1 = npc)
        defaultDirection = "down";
        noMovement = true;
    }

    public void getPlayerImage(){       //legge le immagini del player

        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/jessepinkman/JessePinkmanup.png"));            
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/jessepinkman/JessePinkmandown.png"));           
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/jessepinkman/JessePinkmanleft.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/jessepinkman/JessePinkmanright.png"));
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

        dialogues[0] = "Mister White? ah non non sei lui, tieni anche questo da parte mia";
        dialogues[1] = "Mister White quindi è arrivata l'ora di cucinare, finalmente";
        dialogues[2] = "Questa è arte signor White! i miei complimenti";
        dialogues[3] = "Hey signora, qualunque cosa lei venda non mi interasa";
}
    public void speak(){    //dialogo con l'npc

        //eventuali azioni da fare durante il dialogo (per esempio curare a un certo dialogo)

        //DIALOGO
        super.speak();

        onPath = true;
        noMovement = true;
    }

}