/*
 * Classe che gestisce l'interfaccia utente
 */

package Main;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_Key;

public class UI {
    
    GamePanel gp;

    //Font di base
    Font arial_40;
    Font arial_80B;

    //IMAGES
    BufferedImage keyImage;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    Graphics2D g2;

    public boolean gamefinished = false;

    //PLAYTIME
    double playtime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp){
        this.gp=gp;

        arial_40 = new Font("arial", 0 ,40);
        arial_80B = new Font("arial", Font.BOLD ,80);

        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void draw(Graphics2D g2){        //disegna la ui

        this.g2=g2;
        g2.setFont(arial_40);

        if(gp.gameState == gp.playState){

            if(gamefinished){   //end of the game

                g2.setFont(arial_40);
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
    
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey, 100, 75);
    
            //TIME
            playtime += (double)1/60;
            g2.drawString("playtime: " + dFormat.format(playtime), gp.tileSize*11, 65);
    
            //MESSAGE
            if(messageOn){
    
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
    
                messageCounter ++;
    
                if(messageCounter > 60){
    
                    messageCounter = 0;
                    messageOn = false;
    
                }
    
            }

        }

        if(gp.gameState == gp.pauseState){

            drawPauseScreen();

        }

        

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

}
