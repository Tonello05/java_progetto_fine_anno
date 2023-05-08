/*
 * Oggetto Speciale che serve a mostrare al player un messaggio
 */


package object;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_messagge extends SuperObject{

    int messaggeNumber;
    String messages[] = new String[100];
    GamePanel gp;

    public OBJ_messagge(int i, GamePanel gp){
        name = "messagge";
        this.gp=gp;
        messaggeNumber = i;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/messagge.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setupMessages();

    }

    public void setupMessages(){
        
        messages[0] = "per raccogliere un oggetto passaci sopra";
        messages[1] = "una volta raccolto un oggetto premi [C] per\naprire l'inventario";
        messages[2] = "premi [ENTER] per interagire con un npc";
        messages[3] = "premi [ENTER] per attaccare";

    }

    @Override
    public void showMessage(){
        gp.ui.currentDialogue =  messages[messaggeNumber];
    }

}
