/*
 * Classe che gestisce l'interfaccia utente
 */

package Main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import object.OBJ_Key;
import object.OBJ_coin;

public class UI {
    
    GamePanel gp;

    //Font di base
    Font arial_40;
    Font arial_80B;
    Font pixel_font;

    //IMAGES
    BufferedImage keyImage;
    BufferedImage coinImage;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    Graphics2D g2;

    public boolean gamefinished = false;

    //PLAYTIME
    double playtime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    //DIALOGUE
    public String currentDialogue = "";

    //MENU
    public int commandNumber = 0;

    public UI(GamePanel gp){
        this.gp=gp;

        arial_40 = new Font("arial", 0 ,40);
        arial_80B = new Font("arial", Font.BOLD ,80);
        

        try {
            InputStream is = getClass().getResourceAsStream("/res/font/pixel_font.ttf");
            pixel_font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception e) {
            e.printStackTrace();
        }

        OBJ_Key key = new OBJ_Key();
        OBJ_coin coin = new OBJ_coin();
        keyImage = key.image;
        coinImage = coin.image;

    }

    public void draw(Graphics2D g2){        //disegna la ui

        this.g2=g2;
        g2.setFont(pixel_font);
        g2.setFont(g2.getFont().deriveFont(30F));

        switch (gp.gameState) {
            case GamePanel.playState:
                if(gamefinished){   //end of the game

                    g2.setFont(pixel_font);
                    g2.setColor(Color.white);
        
                    String text;
                    int TextLenght;
                    int x, y;
        
                    text = "you found the treasure !!!";
                    TextLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        
                    x = gp.screenWhidth/2 - TextLenght /2;
                    y = gp.screenHeight/2 - (gp.tileSize*3);
                    g2.drawString(text, x, y);
        
        
                    //display playtime at the end of the game
                    text = "playtime: " + dFormat.format(playtime) + "!";
                    TextLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        
                    x = gp.screenWhidth/2 - TextLenght /2;
                    y = gp.screenHeight/2 + (gp.tileSize*4);
                    g2.drawString(text, x, y);
        
                    g2.setFont(arial_80B);
                    g2.setColor(Color.YELLOW);
        
                    text = "congratulations";
                    TextLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        
                    x = gp.screenWhidth/2 - TextLenght /2;
                    y = gp.screenHeight/2 + (gp.tileSize*2);
                    g2.drawString(text, x, y);
        
                    gp.gameThread = null;
        
                }
        
                g2.setFont(g2.getFont().deriveFont(35F));
                g2.setColor(Color.white);
                g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
                g2.drawString("x " + gp.player.hasKey, 100, 75);

                g2.setFont(g2.getFont().deriveFont(35F));
                g2.setColor(Color.white);
                g2.drawImage(coinImage, gp.tileSize/2, gp.tileSize * 2 -20, gp.tileSize, gp.tileSize, null);
                g2.drawString("x " + gp.player.coins, 100, 150);
        
                //TIME
                playtime += (double)1/60;
                g2.drawString("playtime: " + dFormat.format(playtime), gp.tileSize*11, 65);
        
                //MESSAGE
                if(messageOn){
        
                    g2.setFont(g2.getFont().deriveFont(35F));
                    g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
        
                    messageCounter ++;
        
                    if(messageCounter > 60){
        
                        messageCounter = 0;
                        messageOn = false;
        
                    }
        
                }
                break;
        
            case GamePanel.pauseState:
                g2.setFont(g2.getFont().deriveFont(60F));
                drawPauseScreen();
                break;
            case GamePanel.dialogueState:
                drawDialogueScreen();
                break;
            case GamePanel.titleState:
                drawTitleScreen();
                break;
        }

    }

    public void drawTitleScreen(){


        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        int x = getXForCenteredText(gp.title);
        int y = gp.tileSize*3;
        //BackGround
        gp.setBackground(new Color(70, 120, 180));

        //TEXT SHADOW
        g2.setColor(Color.black);
        g2.drawString(gp.title, x+8, y+8);

        //DISEGNA TITOLO
        g2.setColor(Color.white);
        g2.drawString(gp.title, x, y);

        //DISEGNA LOGO
        x = gp.screenWhidth /2 - gp.tileSize;
        y = gp.tileSize * 4;
        try {
            BufferedImage tmpImage = ImageIO.read(getClass().getResourceAsStream("/res/game_icon/logo_bernocchi.png"));
            Image logo = new UtilityTool().scaleImage(tmpImage, gp.tileSize, gp.tileSize);
            g2.drawImage(logo, x, y, gp.tileSize *2 , gp.tileSize *2, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        String text = "New Game";
        x = getXForCenteredText(text);
        y = gp.tileSize * 7;
        g2.drawString(text, x, y);
        if(commandNumber == 0){
            g2.drawString("~>", x-gp.tileSize, y);
        }

        text = "Load Game";
        x = getXForCenteredText(text);
        y = gp.tileSize * 8;
        g2.drawString(text, x, y);
        if(commandNumber == 1){
            g2.drawString("~>", x-gp.tileSize, y);
        }

        text = "Quit Game";
        x = getXForCenteredText(text);
        y = gp.tileSize * 9;
        g2.drawString(text, x, y);
        if(commandNumber == 2){
            g2.drawString("~>", x-gp.tileSize, y);
        }
    }   

    public void drawDialogueScreen(){

        //WINDOW
        int x = gp.tileSize*2;
        int y = gp.tileSize/2;
        int widht = gp.screenWhidth - (gp.tileSize*4);
        int height = gp.tileSize*4;

        drawSubWindow(x, y, widht, height);

        //TEXT
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 35F));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : currentDialogue.split("\n")){

            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int widht, int height){

        Color black = new Color(0, 0, 0, 200);
        Color white = new Color(255, 255, 255);
        g2.setColor(black);
        g2.fillRoundRect(x, y, widht, height, 35, 35);

        
        g2.setColor(white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, widht-10, height-10, 25, 25);

    }

    public void drawPauseScreen(){

        String text = "PAUSED";
        int x = gp.screenWhidth/2;
        int y = gp.screenHeight/2;
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWhidth/2 - length/2;

        g2.drawString(text, x, y-100);

    }

    public void showMessage(String text){      //mostra un messaggio a schermoù

        message = text;
        messageOn = true;

    }

    public int getXForCenteredText(String text){
        int lenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWhidth/2 - lenght /2;
        return x;
    }

}
