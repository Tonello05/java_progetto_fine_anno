/*
 * Classe principale del gioco
 */

package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.SuperObject;
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


    //WORLD SETTINGS    (dimensioni mappa)
    public final int maxWorldCol = 50;  
    public final int maxWorldRow = 50;

    //FPS   (frame per second) (modificabili)
    int FPS = 60;

    //SYSTEM    (vari oggetti che gestiscono il gioco)
    Sound music = new Sound();  
    Sound soundEffect = new Sound();
    Thread gameThread;
    KeyHadler keyH = new KeyHadler(this);
    public TileManager tileManager = new TileManager(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);

    //ENTITY AND OBJECT
    public Player player = new Player(this, keyH);  //player
    public SuperObject obj[] = new SuperObject[50]; //array di oggetti
    public Entity npc[] = new Entity[50];   //array di npc

    //GAME STATE
    public int gameState;   //stato del gioco
    public final int playState = 1;     //gioco in azione
    public final int pauseState = 2;    //gioco in pause
    public final int dialogueState = 3;     //sta avvendendo un dialogo

    public GamePanel(){     //crea il pannello di gioco

        this.setPreferredSize(new Dimension(screenWhidth, screenHeight));
        this.setBackground(Color.decode("#9cd4c4"));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void setUpGame(){        //setup di vari componenti

        aSetter.setObject();    //crea gli npc
        aSetter.setNpc();       //crea gli oggetti
        playMusic(0);         //fa partire la musica
        gameState = playState;  //stato iniziale del gioco su in azione

    }

    public void startGameThread(){      //fa partire il thread che serve a gestire la grafica

        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {         //metodo (thread) che si occupa di disegnare

        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){
            // 1 UPDATE
            update();
            // 2 DRAW
            repaint();

            try {   //calcoli vari che servono a rendere gli FPS accurati
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

    public void update(){   //aggiorna gli elementi da disegnare
        

        if(gameState == playState){

            //player update
            player.update();

            //NPC update

            for (int i = 0; i < npc.length; i++) {
                if(npc[i]!=null){
                    
                    npc[i].update();
                    
                }
            }
        }

        if(gameState == pauseState){
            //servirà in futuro

        }

    }

    public void paintComponent(Graphics g){     //disegna sullo schermo

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        //TILE
        tileManager.draw(g2);

        //OBJECTS
        for (int i = 0; i < obj.length; i++) {
            if(obj[i] != null){obj[i].draw(g2, this);}
        }
        //NPC
        for (int i = 0; i < npc.length; i++) {
            if(npc[i] != null){npc[i].draw(g2);}
        }

        //PLAYER
        player.draw(g2);

        //UI
        ui.draw(g2);

        g2.dispose();

    }

    public void playMusic(int i){       //riproduce la musica

        music.setFile(i);
        music.play();
        music.loop();;

    }

    public void stopMusic(){            //ferma la musica

        music.stop();

    }

    public void playSE(int i){          //riproduce un effetto sonoro

        soundEffect.setFile(i);
        soundEffect.play();

    }

}
