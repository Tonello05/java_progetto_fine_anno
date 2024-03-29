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

import javax.imageio.ImageIO;

import object.OBJ_Heart;

public class UI {
    
    GamePanel gp;

    //Font di base
    private Font pixel_font;

    //IMAGES
    private BufferedImage heart1, heart2, heart3;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    private Graphics2D g2;

    //DIALOGUE
    public String currentDialogue = "";

    //MENU
    public int commandNumber = 0;

    //INVENTORY
    int slotCol = 0;
    int slotRow = 0;

    public UI(GamePanel gp){
        this.gp=gp;        

        try {
            InputStream is = getClass().getResourceAsStream("/res/font/pixel_font.ttf");
            pixel_font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        OBJ_Heart hearth = new OBJ_Heart();
        heart1 = hearth.image;
        heart2 = hearth.image2;
        heart3 = hearth.image3;

    }

    public void draw(Graphics2D g2){        //disegna la ui

        this.g2=g2;
        g2.setFont(pixel_font);
        g2.setFont(g2.getFont().deriveFont(30F));

        switch (gp.gameState) {     //in base allo stato di gioco disegna la ui
            case GamePanel.playState:
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

                drawPlayerLife();

                break;
        
            case GamePanel.pauseState:
                g2.setFont(g2.getFont().deriveFont(60F));
                drawPauseScreen();
                break;
            case GamePanel.dialogueState:
                drawPlayerLife();
                drawDialogueScreen();
                break;
            case GamePanel.titleState:
                drawTitleScreen();
                break;
            case GamePanel.characterState:
                drawCharacterScreen();
                drawInventory();
                break;
            case GamePanel.gameOverState:
                drawDeathScreen();
                break;
            case GamePanel.commandState:
                drawCommandScreen();
                break;
            case GamePanel.endState:
                drawEndState();
                break;
        }
    }

    private void drawEndState(){     //disegna schermata di fine gioco
        gp.setBackground(new Color(0, 0, 0));
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(50F));

        String text;
        int x, y;

        text = "HAI COMPLETATO IL GIOCO!!!";

        x = getXForCenteredText(text);
        y = gp.screenHeight/2 - (gp.tileSize*3);
        g2.drawString(text, x, y);

        //display playtime at the end of the game
        text = "playtime: " + gp.playtime_m + ":" + gp.playtime_s + "!";
        x = getXForCenteredText(text);
        y = gp.screenHeight/2 + (gp.tileSize*2);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(100F));
        g2.setColor(Color.YELLOW);

        text = "CONGRATULAZIONI!!";
        x = getXForCenteredText(text);
        y = gp.screenHeight/2;
        g2.drawString(text, x, y);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(30F));

        text = "premi [esc] per uscire dal gioco";

        x = getXForCenteredText(text);
        y = gp.screenHeight - (gp.tileSize*2);
        g2.drawString(text, x, y);

        gp.endGameThread();
    }

    private void drawCommandScreen(){    //disegna la schermata dei comandi

        gp.setBackground(new Color(0, 0, 0));

        int x;
        int y;
        String text;

        text = "premi [esc] per tornare al menu di pausa";
        g2.setColor(Color.white);
        x = getXForCenteredText(text);
        y = gp.tileSize*1;
        g2.drawString(text, x, y);

        text = "[W][A][S][D] = MOVIMENTO";
        g2.setColor(Color.white);
        x = gp.tileSize;
        y = gp.tileSize*3;
        g2.drawString(text, x, y);

        text = "[ENTER] = ATTACCO";
        g2.setColor(Color.white);
        x = gp.tileSize * 8;
        y = gp.tileSize*3;
        g2.drawString(text, x, y);

        text = "[C] = INVENTARIO";
        g2.setColor(Color.white);
        x = gp.tileSize;
        y = gp.tileSize*5;
        g2.drawString(text, x, y);

        text = "[ESC] = PAUSA";
        g2.setColor(Color.white);
        x = gp.tileSize * 8;
        y = gp.tileSize*5;
        g2.drawString(text, x, y);

        text = "COMANDI INVENTARIO:";
        g2.setColor(Color.white);
        x = gp.tileSize;
        y = gp.tileSize*7;
        g2.drawString(text, x, y);

        text = "usa le freccette per navigare l'inventario";
        g2.setColor(Color.white);
        x = gp.tileSize;
        y = gp.tileSize*8;
        g2.drawString(text, x, y);

        text = "[ENTER] = EQUIPAGGIA/USA UN OGGETTO";
        g2.setColor(Color.white);
        x = gp.tileSize * 8;
        y = gp.tileSize*8;
        g2.drawString(text, x, y);

    }

    private void drawDeathScreen(){  //disegna la schermata di morte

        g2.setColor(new Color(0,0,0, 150));

        g2.fillRect(0, 0, gp.screenWhidth, gp.screenHeight);
        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        text = "Game Over";
        g2.setColor(Color.black);
        x = getXForCenteredText(text);
        y = gp.tileSize*4;
        g2.drawString(text, x, y);
        //MAIN
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);
        //Retry
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50f));
        text = "Retry";
        x = getXForCenteredText(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if(commandNumber == 0){
            g2.drawString(">", x-40, y);;
        }

        //Quit
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50f));
        text = "Quit";
        x = getXForCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if(commandNumber == 1){
            g2.drawString(">", x-40, y);;
        }

    }

    private void drawCharacterScreen(){  //disegna la schermata delle statistiche del player

        //FRAME
        final int frameX = gp.tileSize * 2;
        final int frameY = gp.tileSize;
        final int frameWidht = gp.tileSize * 5;
        final int drameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidht, drameHeight);

        //TESTO
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 48;

        //NOMI STATISTICHE
        g2.drawString("vita", textX, textY);
        textY += lineHeight;
        g2.drawString("attacco", textX, textY);
        textY += lineHeight;
        g2.drawString("difesa", textX, textY);
        textY += lineHeight;
        g2.drawString("forza", textX, textY);
        textY += lineHeight;
        g2.drawString("destrezza", textX, textY);
        textY += lineHeight;
        g2.drawString("velocità", textX, textY);
        textY += lineHeight;
        g2.drawString("monete", textX, textY);
        textY += lineHeight;
        g2.drawString("chiavi", textX, textY);
        textY += lineHeight * 2;
        g2.drawString("arma", textX, textY);
        textY += lineHeight * 1.5;
        g2.drawString("scudo", textX, textY);

        //VALORI STATISTICHE
        int tailX = (frameX + frameWidht) - 30;
        //reset y del testo
        textY = frameY + gp.tileSize;
        String value;
        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXForRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.damage);
        textX = getXForRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.defence);
        textX = getXForRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.strenght);
        textX = getXForRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.dexterity);
        textX = getXForRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.speed);
        textX = getXForRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.coins);
        textX = getXForRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.hasKey);
        textX = getXForRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.image, tailX - gp.tileSize, textY, gp.tileSize, gp.tileSize, null);
        textY += lineHeight * 1.5;
        g2.drawImage(gp.player.currentShield.image, tailX - gp.tileSize, textY, gp.tileSize, gp.tileSize, null);
    }

    private void drawPlayerLife(){   //disegna la vita del player

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i=0;

        //disegna vita massima
        while (i < gp.player.maxLife/2) {
            
            g2.drawImage(heart3, x, y, gp.tileSize, gp.tileSize, null);
            i++;
            x += gp.tileSize;
        }

        //reset valori
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i=0;

        //disegna vita corrente
        while (i < gp.player.life) {
            
            g2.drawImage(heart2, x, y, gp.tileSize, gp.tileSize, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart1, x, y, gp.tileSize, gp.tileSize, null);
            }
            i++;
            x += gp.tileSize;
        }



    }

    private void drawTitleScreen(){  //disegna la schermata iniziale


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
            BufferedImage tmpImage = ImageIO.read(getClass().getResourceAsStream("/res/game_icon/logoBernocchi.png"));
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

    private void drawDialogueScreen(){   //disegna i dialoghi

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

    private void drawSubWindow(int x, int y, int widht, int height){    //disegna una finestra passandogli dimensioni e posizione

        Color black = new Color(0, 0, 0, 200);
        Color white = new Color(255, 255, 255);
        g2.setColor(black);
        g2.fillRoundRect(x, y, widht, height, 35, 35);

        
        g2.setColor(white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, widht-10, height-10, 25, 25);

    }

    private void drawPauseScreen(){  //disegna la schermata di pausa

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        int x = getXForCenteredText("PAUSA");
        int y = gp.tileSize*3;
        //BackGround
        gp.setBackground(new Color(70, 120, 180));

        //TEXT SHADOW
        g2.setColor(Color.black);
        g2.drawString("PAUSA", x+8, y+8);

        //DISEGNA TITOLO
        g2.setColor(Color.white);
        g2.drawString("PAUSA", x, y);

        //DISEGNA LOGO
        x = gp.screenWhidth /2 - gp.tileSize;
        y = gp.tileSize * 4;
        try {
            BufferedImage tmpImage = ImageIO.read(getClass().getResourceAsStream("/res/game_icon/logoBernocchi.png"));
            Image logo = new UtilityTool().scaleImage(tmpImage, gp.tileSize, gp.tileSize);
            g2.drawImage(logo, x, y, gp.tileSize *2 , gp.tileSize *2, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
        String text = "Continue";
        x = getXForCenteredText(text);
        y = gp.tileSize * 7;
        g2.drawString(text, x, y);
        if(commandNumber == 0){
            g2.drawString("~>", x-gp.tileSize, y);
        }

        text = "Comandi";
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

    private void drawInventory(){    //disegna l'inventario del player

        //FRAME
        int frameX = gp.tileSize*9;
        int frameY = gp.tileSize;
        int frameWidht = gp.tileSize*6;
        int frameHeight = gp.tileSize*5;

        drawSubWindow(frameX, frameY, frameWidht, frameHeight);

        //SLOTS

        int slotXstart = frameX + 20;
        int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;

        //items
        for (int i = 0; i < gp.player.inventory.size(); i++) {
            
            //equip cursor
            if(gp.player.inventory.get(i) == gp.player.currentWeapon || gp.player.inventory.get(i) == gp.player.currentShield){
                g2.setColor(Color.orange);
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);

            }


            g2.drawImage(gp.player.inventory.get(i).image, slotX, slotY, gp.tileSize, gp.tileSize, null);
            slotX += gp.tileSize;
            if(i == 4 || i  == 9 || i == 14){
                slotX = slotXstart;
                slotY += gp.tileSize;
            }
        }

        //cursor
        int cursorX = slotXstart + (gp.tileSize * slotCol);
        int cursorY = slotYstart + (gp.tileSize * slotRow);
        int cursorWidht = gp.tileSize;
        int cursorHeight = gp.tileSize;

        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidht, cursorHeight, 10, 10);

        //FRAME descrizione

        int dframeX = frameX;
        int dframeY = frameY + frameHeight;
        int dframeWidht = frameWidht;
        int dframeHeight = gp.tileSize * 3;

        

        //scrittura descrizione
        int textX = dframeX + 20;
        int textY = dframeY + gp.tileSize/2;
        g2.setFont(g2.getFont().deriveFont(28F));
        
        int itemIndex = getItemIndexOnSlot();
        if(itemIndex < gp.player.inventory.size()){
            drawSubWindow(dframeX, dframeY, dframeWidht, dframeHeight);
            for (String line : gp.player.inventory.get(itemIndex).description.split("\n")) {
                g2.drawString(line, textX, textY);
                textY += 32;
            }
            
        }

        //FRAME sotto descrizione

        dframeX = frameX;
        dframeY = (frameY*4) + frameHeight;
        dframeWidht = frameWidht;
        dframeHeight = gp.tileSize * 2;

        

        //scrittura sotto descrizione
        textX = dframeX + gp.tileSize;
        textY = dframeY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(40F));
        
        itemIndex = getItemIndexOnSlot();
        if(itemIndex < gp.player.inventory.size()){
            if(gp.player.inventory.get(itemIndex).type == 1){
                drawSubWindow(dframeX, dframeY, dframeWidht, dframeHeight);
                g2.drawString(("DMG: " + gp.player.inventory.get(itemIndex).attackAttribute), textX, textY);
            }else if (gp.player.inventory.get(itemIndex).type == 2){
                drawSubWindow(dframeX, dframeY, dframeWidht, dframeHeight);
                g2.drawString(("DEF: " + gp.player.inventory.get(itemIndex).defenceAttribute), textX, textY);
            }
            
        }



    }

    public int getItemIndexOnSlot(){    //ottiene l'indice dell'oggetto selezionato
        return slotCol + (slotRow*5);
    }

    public void showMessage(String text){      //mostra un messaggio a schermoù

        message = text;
        messageOn = true;

    }

    private int getXForCenteredText(String text){
        int lenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWhidth/2 - lenght /2;
        return x;
    }

    private int getXForRightText(String text, int tailX){
        int lenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX -lenght;
        return x;
    }

}
