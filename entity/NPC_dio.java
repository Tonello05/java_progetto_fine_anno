package entity;

import java.util.Random;
import java.util.Vector;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import Main.GamePanel;

public class NPC_dio extends Entity{

    BufferedImage anim1,anim2,anim3,anim4;
    int animationCounter;


    public NPC_dio(GamePanel gp){
        super(gp);

        direction = "right"; //direzione iniziale dell'npc
        speed = 1;      //velocità dell'npc
        getPlayerImage();   
        setDialogue();
        name = "dio";
        type = 1;   //tipo di entity ( 1 = npc)
        defaultDirection = "right";
        noMovement = true;
    }

    public void getPlayerImage(){       //legge le immagini del player

        try {
            
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/dio/diop2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/dio/diop0.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/dio/diop3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/dio/diop1.png"));
            anim1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/dio/dio_anim2.png"));
            anim2 = ImageIO.read(getClass().getResourceAsStream("/res/npc/dio/dio_anim3.png"));
            anim3 = ImageIO.read(getClass().getResourceAsStream("/res/npc/dio/dio_anim4.png"));
            anim4 = ImageIO.read(getClass().getResourceAsStream("/res/npc/dio/dio_anim5.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(){
        if(!animationOn){
            super.update();
        }else{
            animationCounter++;
            if(animationCounter < 30){
                currentAnim = anim1;
            }else if(animationCounter < 60){
                currentAnim = anim2;
            }else if(animationCounter < 90){
                currentAnim = anim3;
            }else if(animationCounter < 120){
                currentAnim = anim4;
            }else{
                currentAnim = anim4;
                this.worldY--;
                if(worldY <100){
                    gp.npc[1] = null;
                } 
            }

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

        dialogues[0] = "Ciao gamer\nsono dio";
        dialogues[1] = "Benvenuto in 4F adventure";
        dialogues[2] = "Spero che questo gioco sia di suo gradimento";
        dialogues[3] = "Sappi che anche se ogni tanto\nassocia il mio nome ad animali\nio le voglio lo stesso bene";
        dialogues[4] = "è ora di andare\nho una partita a boccie con gli apostoli\naddio";
    }

    public void speak(){    //dialogo con l'npc

        //eventuali azioni da fare durante il dialogo (per esempio curare a un certo dialogo)
        
        //DIALOGO
        super.speak();
        if(dialogueIndex==5){
            animationOn = true;
        }
    }

}
