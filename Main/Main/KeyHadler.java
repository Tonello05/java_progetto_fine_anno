/*
 * Classe che gestisce l'input da tastiera
 */

package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHadler implements KeyListener{

    public boolean upPressed, downPressed, leftPressed, rightPressed, shiftPressed;
    GamePanel gp;

    public KeyHadler(GamePanel gp){
        this.gp=gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {    //controlla quando un tasto ciene premuto

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_SHIFT) {
            shiftPressed = true;
        }
        if(code == KeyEvent.VK_ESCAPE){
            if(gp.gameState == gp.playState){gp.gameState = gp.pauseState;}
            else if(gp.gameState == gp.pauseState){gp.gameState = gp.playState;}
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {   //controlla quando un tasto viene rilasciato
        
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_SHIFT) {
            shiftPressed = false;
        }
        
    }
    
    

}
