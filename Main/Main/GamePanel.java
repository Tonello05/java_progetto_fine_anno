package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
    
    //SCREEN SETTINGS
    final int originalTileSize = 16;    //16x16 tile
    final int scale = 4; //scale of the tile
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWhidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    KeyHadler keyH = new KeyHadler();

    //FPS
    int FPS = 60;

    Thread gameThread;

    Player player = new Player(this, keyH);
    TileManager tileManager = new TileManager(this);

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

        tileManager.draw(g2);
        player.draw(g2);


        g2.dispose();

    }
}
