/*
 * Classe Main (avvia il gioco e imposta la finestra)
 */

package Main;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        GamePanel gamePanel = new GamePanel();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle(gamePanel.title);
        gamePanel.setUpGame();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("res/game_icon/logoBernocchi.png"); //icona della finestra
        window.setIconImage(icon.getImage());
        window.setVisible(true);
        gamePanel.startGameThread();

    }   
}
