/*
 * Classe che gestisce l'input da tastiera
 */

package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHadler implements KeyListener{

    public boolean upPressed, downPressed, leftPressed, rightPressed, shiftPressed, enterPressed;
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

        //TITLE STATE
        if(gp.gameState == GamePanel.titleState){
            if(code == KeyEvent.VK_UP){
                gp.ui.commandNumber --;
                if(gp.ui.commandNumber < 0 ){gp.ui.commandNumber = 2;}
                gp.playSE(5);
            }
            if(code == KeyEvent.VK_DOWN){
                gp.ui.commandNumber ++;
                if(gp.ui.commandNumber > 2 ){gp.ui.commandNumber = 0;}
                gp.playSE(5);
            }
            if(code == KeyEvent.VK_ENTER){
                switch (gp.ui.commandNumber) {
                    case 0:
                        gp.stopMusic();
                        gp.gameState = GamePanel.playState;
                        gp.playMusic(0);
                        break;
                
                    case 1:

                        break;
                    case 2:
                        System.exit(0);
                        break;
                }
            }
        }

        //GAME STATE
        else if(gp.gameState == GamePanel.playState){

            
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
                gp.gameState = GamePanel.pauseState;
            }
            if(code == KeyEvent.VK_ENTER){
                enterPressed = true;
            }

        }

        //PAUSE STATE
        else if(gp.gameState == GamePanel.pauseState){
            if(code == KeyEvent.VK_ESCAPE){
                 gp.gameState = GamePanel.playState;
            }
        }

        //DIALOGUE STATE
        else if(gp.gameState == GamePanel.dialogueState){

            if(code == KeyEvent.VK_ENTER){
                gp.gameState = GamePanel.playState;
            }

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
        if( code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
    }
    
    

}
