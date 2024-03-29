/*
 * Classe principale del gioco
*/

package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import ai.PathFinder;
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

    //GAME TITLE
    public final String title = "4F ADVENTURE !!";    //titolo del gioco

    //WORLD SETTINGS    (dimensioni mappa)
    public final int maxWorldCol = 100;  
    public final int maxWorldRow = 100;

    //FPS   (frame per second) (modificabili)
    private int FPS = 60;
    public int fps_counter = 0;

    //SYSTEM    (vari oggetti che gestiscono il gioco)
    public Sound music = new Sound();          //gestore delle musiche
    public Sound soundEffect = new Sound();    //gestore degli effetti sonori
    private Thread gameThread;  //thread principale del gioco
    public KeyHadler keyH = new KeyHadler(this);    //gestisce i comandi
    public TileManager tileManager = new TileManager(this); //si occupa di disegnare la mappa
    public CollisionChecker cChecker = new CollisionChecker(this);  //controlla le collisioni
    public AssetSetter aSetter = new AssetSetter(this); //crea e posiziona oggetti/nemici/npc
    public UI ui = new UI(this);    //disegna la user interface
    public PathFinder pFinder = new PathFinder(this);   //si occupa del pathfinding
    public int playtime_s = 0;  //secondi playtime
    public int playtime_m = 0;  //minuti playtime

    //ENTITY AND OBJECT
    public Player player = new Player(this, keyH);  //player
    public SuperObject obj[] = new SuperObject[200]; //array di oggetti
    public Entity npc[] = new Entity[50];   //array di npc
    public Entity enemy[] = new Entity[50];     //array di nemici

    //GAME STATE
    public int gameState;   //stato del gioco
    public final static int playState = 1;     //gioco in azione
    public final static int pauseState = 2;    //gioco in pause
    public final static int dialogueState = 3;     //sta avvendendo un dialogo
    public final static int titleState = 4;    //schermata iniziale
    public final static int characterState = 5;     //statistiche del player
    public final static int gameOverState = 6;  //Stato di game over
    public final static int commandState = 7;   //comandi di gioco
    public final static int endState = 8;   //fine del gioco

    public GamePanel(){     //crea il pannello di gioco

        this.setPreferredSize(new Dimension(screenWhidth, screenHeight));
        //this.setBackground(Color.decode("#9cd4c4"));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void setUpGame(){        //setup di vari componenti
        aSetter.setObject();    //crea gli npc
        aSetter.setNpc();       //crea gli oggetti
        aSetter.setEnemy();     //crea i nemici
        gameState = titleState;  //stato iniziale del gioco su in azione
        playMusic(6);   //musica del menu iniziale

    }

    public void startGameThread(){      //fa partire il thread che serve a gestire la grafica

        gameThread = new Thread(this);
        gameThread.start();

    }

    public void endGameThread(){    //fa terminare il ther del gioco
        gameThread = null;
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

            //update fps counter
            fps_counter ++;
            //update playtime
            if(fps_counter % 60 == 0){
                playtime_s += 1;
            }
            if(playtime_s == 60){
                playtime_s = 0;
                playtime_m++;
            }

            if(fps_counter == 120){
                fps_counter = 0;
            }

            //player update
            player.update();

            //NPC update

            for (int i = 0; i < npc.length; i++) {
                if(npc[i]!=null){
                    npc[i].update(); 
                }
            }

            //ENEMY UPDATE
            for (int i = 0; i < enemy.length; i++) {
                if(enemy[i]!=null){
                    if(enemy[i].alive && !enemy[i].dying){enemy[i].update();}
                    if(!enemy[i].alive){enemy[i] = null;}
                }
            }

        }

    }

    public void paintComponent(Graphics g){     //disegna sullo schermo

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        if(gameState == titleState || gameState == pauseState || gameState == commandState || gameState == endState){
            ui.draw(g2);
        }else{

            //TILE
            tileManager.draw(g2);
            //NPC
            for (int i = 0; i < npc.length; i++) {
                if(npc[i] != null){npc[i].draw(g2);}
            }
            //OBJECTS
            for (int i = 0; i < obj.length; i++) {
                if(obj[i] != null){obj[i].draw(g2, this);}
            }
            //ENEMY
            for (int i = 0; i < enemy.length; i++) {
                if(enemy[i] != null){enemy[i].draw(g2);}
            }
            //PLAYER
            player.draw(g2);

            //UI
            ui.draw(g2);

        }

        


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

    public void endGame(){      //fa finire il gioco
        gameState = endState;
    }

}
