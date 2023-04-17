/*
 * Classe Padre di tutti gli oggetti (Porte, Power-up, Chiavi...)
 */

package object;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Main.GamePanel;

public class SuperObject {
    
    //per altre info sulla creazione di un oggetto guarda la classe OBJ_Shoes
    //le interazioni con gli oggetti sono all'interno della classe player

    public BufferedImage image;         //immagine dell'oggetto
    public String name;                 //nome dell'oggetto
    public boolean collision = false;   //collisioni dell'oogetto ative o no
    public int worldX, worldY;          //posizione dell'oggetto
    public Rectangle solidArea = new Rectangle(0,0,48,48);  //hitbox dell'oggetto
    public int solidAreaDefaultX = 0 , solidAreaDefaultY = 0;       //altre variabili per l'hitbox

    public void draw(Graphics2D g2, GamePanel gp){  //disegna l'oggetto sullo schermo

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
            worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                g2.drawImage(image, screenX, screenY,  gp.tileSize, gp.tileSize, null);
            }


    }

}
