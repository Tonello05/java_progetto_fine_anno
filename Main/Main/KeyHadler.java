/*
 * Classe che gestisce l'input da tastiera
 */

package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

public class KeyHadler implements KeyListener{

    public boolean upPressed, downPressed, leftPressed, rightPressed, shiftPressed, enterPressed;
    private GamePanel gp;

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
        if(gp.gameState == GamePanel.titleState){titleState(code);}

        //GAME STATE
        else if(gp.gameState == GamePanel.playState){playState(code);}

        //PAUSE STATE
        else if(gp.gameState == GamePanel.pauseState){pauseState(code);}

        //DIALOGUE STATE
        else if(gp.gameState == GamePanel.dialogueState){dialogueState(code);}

        //CHARACTER STATE
        else if(gp.gameState == GamePanel.characterState){characterState(code);}

        //DEATH STATE
        else if(gp.gameState == GamePanel.gameOverState){deathState(code);}
    
        //COMMAND STATE
        else if(gp.gameState == GamePanel.commandState){commandState(code);}

        //END STATE
        else if(gp.gameState == GamePanel.endState){endState(code);}
    }

    private void commandState(int code){

        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = GamePanel.pauseState;
        }

    }

    private void deathState(int code){

        if(code == KeyEvent.VK_UP){
            if(gp.ui.commandNumber == 1){
                gp.playSE(5);
                gp.ui.commandNumber = 0;
            }
        }
        if(code == KeyEvent.VK_DOWN){
            if(gp.ui.commandNumber == 0){
                gp.playSE(5);
                gp.ui.commandNumber = 1;
            }
        }
        if(code == KeyEvent.VK_ENTER){
            switch (gp.ui.commandNumber) {
                case 1:
                    System.exit(0);
                    break;
                case 0:
                    gp.player.respawn();
                    break;
            }
        }
    }

    private void titleState(int code){
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
                    JOptionPane.showMessageDialog(null, "pure il salvataggio? mi sermbra un po' troppo, non abbiamo voglia", "ERRORE", JOptionPane.ERROR_MESSAGE);
                        break;
                case 2:
                    System.exit(0);
                    break;
            }
        }
    }

    private void characterState(int code){
            if(code == KeyEvent.VK_C){
                gp.gameState = GamePanel.playState;
            }
            if (code == KeyEvent.VK_W) {
                
                if(gp.ui.slotRow>0){
                    gp.ui.slotRow--;
                    gp.playSE(5);
                }
            }
            if (code == KeyEvent.VK_S) {
                if(gp.ui.slotRow < 3){
                    gp.ui.slotRow++;
                    gp.playSE(5);
                }
            }   
            if (code == KeyEvent.VK_A) {
                if(gp.ui.slotCol > 0){    
                    gp.ui.slotCol--;
                    gp.playSE(5);
                }
            }
            if (code == KeyEvent.VK_D) {
                if(gp.ui.slotCol < 4){
                    gp.ui.slotCol++;
                    gp.playSE(5);
                }
            }
            if(code == KeyEvent.VK_ENTER){
                gp.player.selectItem();
            }
            
    }

    private void dialogueState(int code){
        if(code == KeyEvent.VK_ENTER){
            gp.gameState = GamePanel.playState;
        }
    }

    private void pauseState(int code){
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
                    gp.gameState = GamePanel.playState;
                    break;
            
                case 1:
                    gp.gameState = GamePanel.commandState;
                        break;
                case 2:
                    System.exit(0);
                    break;
            }
        }
    }

    private void playState(int code){
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
        if (code == KeyEvent.VK_C){
            gp.gameState = GamePanel.characterState;
        }
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = GamePanel.pauseState;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
    }

    private void endState(int code){

        if(code == KeyEvent.VK_ESCAPE){
            System.exit(0);
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
