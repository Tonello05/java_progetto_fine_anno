/*
 * Oggetto: Utility
 * Descrizione: alcuni metodi utili all'interno del progetto
 */

package Main;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {
    
    public BufferedImage scaleImage(BufferedImage original , int widht, int height){    //scala un immagine

        BufferedImage scaledImage = new BufferedImage(widht, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, widht, height, null);
        g2.dispose();

        return scaledImage;

    }


}
