import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    
    //SCREEN SETTINGS
    final int originalTileSize = 16;    //16x16 tile
    final int scale = 3; //scale of the tile
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 28;
    final int maxScreenRow = 15;
    final int screenWhidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;
    KeyHadler keyH = new KeyHadler();

    //FPS
    int FPS = 60;

    Thread gameThread;

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

        if(keyH.upPressed == true){
            playerY -= playerSpeed;
        }
        if(keyH.downPressed){
            playerY += playerSpeed;
        }
        if(keyH.leftPressed){
            playerX -= playerSpeed;
        }
        if(keyH.rightPressed){
            playerX += playerSpeed;
        }


    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);

        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();

    }
}
