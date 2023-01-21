package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable{
    
    //SCREEN SETTINGS
    final int originalTileSize = 16;    //16x16 tile
    final int scale = 4; //scale of the tile
    public final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 20;
    final int maxScreenRow = 12;
    final int screenWhidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    KeyHadler keyH = new KeyHadler();

    //FPS
    int FPS = 60;

    Thread gameThread;

    Player player = new Player(this, keyH);

    // set player default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWhidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){
            // 1 UPDATE
            update();
            // 2 DRAW
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if(remainingTime<0){remainingTime=0;}

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        player.draw(g2);

        g2.dispose();

    }
}
