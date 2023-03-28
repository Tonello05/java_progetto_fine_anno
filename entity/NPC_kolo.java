package entity;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class NPC_kolo extends Entity{
    
    public NPC_kolo(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;
        getPlayerImage();
    }

    public void getPlayerImage(){       //legge le immagini del player

        try {
            
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/koloup1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/npc/koloup2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/res/npc/koloup3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/kolodown1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/npc/kolodown2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/res/npc/kolodown3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/kololeft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/npc/kololeft2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/res/npc/kololeft3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/koloright1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/npc/koloright2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/res/npc/koloright3.png"));
            //TODO
            //gli spites non vengono visualizzati

        } catch (Exception e) {
            e.printStackTrace();
        }
    }   

    


}
