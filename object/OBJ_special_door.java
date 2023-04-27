/*
 * porta che si apre solo nel caso una condizione sia vera
 */

package object;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class OBJ_special_door extends SuperObject{
    
    public int requirement;
    GamePanel gp;

    public OBJ_special_door(GamePanel gp, int i){
        this.requirement = i;
        this.gp = gp;
        name = "special_door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/door.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
        System.out.println("prova");

    }

    @Override
    public boolean checkRequirements(){

        switch (requirement) {
            case 1:
                if(gp.player.coins > 0){return true;}
                break;
        }
        return false;
    };

    @Override
    public String getMessagge(){
        switch (requirement) {
            case 1:
                return "uccidi il ragno per proseguire";
        
        }
        return "";
    }

}
