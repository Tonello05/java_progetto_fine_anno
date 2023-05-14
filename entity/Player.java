/*
 * Classe che gestisce il player
 */

package entity;

import javax.imageio.ImageIO;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Vector;
import java.awt.Rectangle;

import Main.GamePanel;
import Main.KeyHadler;
import object.OBJ_Shield_wood;
import object.OBJ_Sword_normal;
import object.OBJ_stampella;
import object.SuperObject;

public class Player extends Entity{
    
    KeyHadler keyH;

    public int screenX;
    public int screenY;

    //chechPoint
    private int checkPointX;
    private int checkPointY;

    //GAME ITEMS VARIABLES
    public int hasKey = 0;
    public int coins = 0;
    public Vector<SuperObject> inventory = new Vector<>();
    private int inventorySize = 20;

    public Player(GamePanel gp, KeyHadler keyH){    //contruttore e setup del player

        super(gp);
        this.gp=gp;
        this.keyH=keyH;
        screenX = gp.screenWhidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        //HITBOX
        solidArea = new Rectangle();
        solidArea.x=8;
        solidArea.y=16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width=32;
        solidArea.height=32;

        //HITBOX ATTACCO
        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        setItems();
    }

    private void setDefaultValues(){     //imposta alcuni valori predefiniti

        worldX=gp.tileSize * 6;
        worldY=gp.tileSize * 16;
        checkPointX = worldX;
        checkPointY = worldY;
        defaultSpeed=20;
        direction="down";
        maxLife = 6;
        life = maxLife;
        type = 0;
        strenght = 1;
        dexterity = 1;
        currentWeapon = new OBJ_Sword_normal();
        currentShield = new OBJ_Shield_wood();
        damage = getAttack();
        defence = getDefence();
        speed = defaultSpeed;


    }

    private void setItems(){     //imposta gli item

        inventory.add(currentWeapon);
        inventory.add(currentShield);

    }

    private int getAttack(){ //calcola il valore di attacco
        return currentWeapon.attackAttribute * strenght;
    }
    private int getDefence(){    //calcola il valore di difesa
        return currentShield.defenceAttribute * dexterity;
    }
    private int getSpeed(){ //calcola la velocità del player
        if(currentWeapon.haveSpeedAttribute){
            return (int)(defaultSpeed * currentWeapon.speedAttribute);
        }else{
            return (int)(defaultSpeed * currentShield.speedAttribute);
        }
    }

    private void getPlayerImage(){       //legge le immagini del player

        try {
            
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/up2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/res/player/up3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/down2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/res/player/down3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/left2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/res/player/left3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/right2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/res/player/right3.png"));
            attackLeft = ImageIO.read(getClass().getResourceAsStream("/res/player/leftHit.png"));
            attackRight = ImageIO.read(getClass().getResourceAsStream("/res/player/rightHit.png"));
            attackUp = ImageIO.read(getClass().getResourceAsStream("/res/player/upHit.png"));
            attackDown = ImageIO.read(getClass().getResourceAsStream("/res/player/downHit.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }    

    public void update(){       //aggiorna la posizione del player

        screenX = gp.getWidth()/2 - (gp.tileSize/2);
        screenY = gp.getHeight()/2 - (gp.tileSize/2);

        if(attacking){
            attacking();
        }else{
            
            if(keyH.upPressed == true){
                direction="up";
            }
            if(keyH.downPressed){
                direction="down";
            }
            if(keyH.leftPressed){
                direction="left";
            }
            if(keyH.rightPressed){
                direction="right";
                
            }

            // Check player collision
            collisionIsOn = false;
            gp.cChecker.checkTile(this);

            //Check object collision
            int object_index = gp.cChecker.checkObject(this, true);
            pickUpObject(object_index);

            //check NPCs collision
            int npcIndex = gp.cChecker.checEntity(this, gp.npc);
            intercatNPC(npcIndex);

            

            //check enemy collision
            int enemyIndex = gp.cChecker.checEntity(this, gp.enemy);
            interactEnemy(enemyIndex);
            //if collision is false player can move

            if (collisionIsOn == false ) {

                switch (direction) {
                    case "up":
                        if(keyH.upPressed){worldY -= speed;spriteCounter++;}
                        break;
                
                    case "down":
                        if(keyH.downPressed){worldY += speed;spriteCounter++;}
                        break;
                    case "left":
                        if (keyH.leftPressed){worldX -= speed;spriteCounter++;}
                        break;
        
                    case "right":
                        if(keyH.rightPressed){worldX += speed;spriteCounter++;}
                        break;
                }
            }

            if(spriteCounter < 15){
                spriteNum = 1;
            }else if ( spriteCounter < 30){
                spriteNum = 2;
            }else if ( spriteCounter < 45){
                spriteNum = 3;
            }else if (spriteCounter < 60){
                spriteNum = 2;
            }else{
                spriteCounter = 0;
            }

        }

        if(invincible){
            invincibleCounter++;
            if(invincibleCounter > 60){invincible = false; invincibleCounter = 0;}
        }

        if(life <= 0){
            gp.gameState = GamePanel.gameOverState;
        }

    }

    private void attacking(){   //aggiorna lo spirte del player se sta attaccando

        spriteCounter ++;
        if(spriteCounter <= 5){
            spriteNum = 1;
        }
        if(spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;

            //salva la posizioe corrente
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidht = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //calcola la hitbox dell'attacco
            switch (direction) {
                case "up":worldY -= solidArea.height;break;
                case "down":worldY += solidArea.height;break;
                case "left":worldX -= solidArea.width;break;
                case "right":worldX += solidArea.width;break;
            }
            //attacco diventa solidArea
            solidArea.height = attackArea.height;
            solidArea.width = attackArea.width;

            //controlla se un nemico è stato colpito
            int monsterIndex = gp.cChecker.checEntity(this, gp.enemy);
            damageMonster(monsterIndex);

            //reimposta la posizione del player
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidht;
            solidArea.height = solidAreaHeight;
        }
        if(spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }


    }

    private void damageMonster(int index){  //gestisce gli attacchi ai nemici

        if(index != 999){

            //nemico danneggiato
            if(gp.enemy[index].invincible == false){
                gp.enemy[index].life -= this.damage;
                gp.enemy[index].invincible = true;
                gp.enemy[index].damageReaction();
                gp.playSE(8);
            }

            //nemico ucciso
            if(gp.enemy[index].life <= 0){
                gp.enemy[index].dying = true;
            }

        }else{
            //if you miss
        }

        

    }

    private void interactEnemy(int index){  //interagisce con un nemico

        if(index != 999){

            if(invincible == false){
                life -= gp.enemy[index].damage - defence;
                invincible = true;
                gp.playSE(7);
            }
        }

    }

    private void intercatNPC(int index){    //interazioni con gli npc

        if(keyH.enterPressed){

            if(index != 999){
                if(!gp.npc[index].animationOn){
                    gp.gameState = GamePanel.dialogueState;
                    gp.npc[index].speak();
                }
            }else if (keyH.enterPressed){
                attacking = true;
            }

        }   

    }

    private void addObject(int i){   //aggiunge un item all'inventario

    if(inventory.size() < inventorySize){
        inventory.add(gp.obj[i]);
        
        gp.ui.showMessage("ha ottenuto " + gp.obj[i].name);
    }else {
        gp.ui.showMessage("il tuo inventario è pieno!!");
    }
    gp.obj[i] = null;
    }

    private void pickUpObject(int index){    //gestisce le interazioni con gli oggetti

        if(index!=999){

            String objectName = gp.obj[index].name;

            switch (objectName) {

                case "special_door":
                    if(gp.obj[index].collision){
                        if(gp.obj[index].checkRequirements()){
                            gp.playSE(3);
                            gp.ui.showMessage("you opened a door!");
                            try {
                                gp.obj[index].image = ImageIO.read(getClass().getResourceAsStream("/res/objects/openedDoor.png"));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            gp.obj[index].collision=false;
                        }else{
                            gp.ui.showMessage(gp.obj[index].getMessagge());
                        }
                    }
                    break;
                case "messagge":
                        
                        gp.obj[index].showMessage();
                        gp.gameState = GamePanel.dialogueState;
                        gp.obj[index] = null;
                    break;
                case "key":
                        hasKey ++;
                        gp.obj[index] = null;
                        gp.playSE(1);
                        gp.ui.showMessage("you got a key!");
                    break;
            
                case "door":
                    if (hasKey > 0 && gp.obj[index].collision) {
                        hasKey--;
                        gp.playSE(3);
                        gp.ui.showMessage("you opened a door!");
                        try {
                            gp.obj[index].image = ImageIO.read(getClass().getResourceAsStream("/res/objects/openedDoor.png"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        gp.obj[index].collision=false;
                        
                    }
                    if(hasKey < 1 && gp.obj[index].collision){
                        gp.ui.showMessage("you need a key!");
                    }

                    break;


                case "chest":
                    gp.gameState = GamePanel.endState;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
                case "inps":
                    gp.obj[index] = null;
                    this.speed = speed/2;
                    gp.playSE(2);
                    coins = 104;
                    gp.ui.showMessage("hai ottenuto la 104!!!");

                    break;
                case "coin":
                    gp.obj[index] = null;
                    gp.playSE(1);
                    coins++;
                    break;
                case "decoration":

                    break;
                default:
                    addObject(index);
                    break;
                }

        }

    }

    public void draw(Graphics2D g2){    //disegna il player sullo schermo

        BufferedImage image = null;

        

        switch (direction) {
            case "up":
                if(gp.gameState == GamePanel.pauseState){spriteNum=3;}

                if(attacking == false){
                    if (spriteNum==1) {image=up1;}
                    if (spriteNum==2) {image=up2;}
                    if (spriteNum==3) {image=up3;}
                    if(keyH.upPressed == false){image=up3;}
                }else{
                    if (spriteNum==1) {image=up1;}
                    if (spriteNum==2) {image=attackUp;}
                }
                break;
            case "down":
                if(gp.gameState == GamePanel.pauseState){spriteNum=2;}
                if(attacking == false){
                    if (spriteNum==1) {image=down1;}
                    if (spriteNum==2) {image=down2;}
                    if (spriteNum==3) {image=down3;}
                    if(keyH.downPressed == false){image=down2;}
                }else{
                    if (spriteNum==1) {image=down1;}
                    if (spriteNum==2) {image=attackDown;}
                }
                break;
            case "left":
                if(gp.gameState == GamePanel.pauseState){spriteNum=2;}
                if(attacking == false){
                    if (spriteNum==1) {image=left1;}
                    if (spriteNum==2) {image=left2;}
                    if (spriteNum==3) {image=left3;}
                    if(keyH.leftPressed == false){image=left2;}
                }else{
                    if (spriteNum==1) {image=left1;}
                    if (spriteNum==2) {image=attackLeft;}
                }
                
                break;
            case "right":
                if(gp.gameState == GamePanel.pauseState){spriteNum=2;}
                if(attacking == false){
                    if (spriteNum==1) {image=right1;}
                    if (spriteNum==2) {image=right2;}
                    if (spriteNum==3) {image=right3;}
                    if(keyH.rightPressed == false){image=right2;}
                }else{
                    if (spriteNum==1) {image=right1;}
                    if (spriteNum==2) {image=attackRight;}
                }
                
                
                break;
        }

        //effetto visivo invincibilità
        if(invincible && invincibleCounter % 2 == 0){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        //se non sta attaccando disegna il movimento normale
        if(attacking == false){g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);}
        
        //disegna il player quando attacca
        if(attacking){

            if(direction == "down"){

            }
            switch (direction) {
                case "down":
                    if(spriteNum == 1){g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);}
                    if(spriteNum == 2){g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize*2, null);}
                    break;
                case "up":
                    if(spriteNum == 1){g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);} 
                    if(spriteNum == 2){g2.drawImage(image, screenX, screenY-gp.tileSize, gp.tileSize, gp.tileSize*2, null);}
                    break;    
                case "left":
                    if(spriteNum == 1){g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);}
                    if(spriteNum == 2){g2.drawImage(image, screenX-gp.tileSize, screenY, gp.tileSize*2, gp.tileSize, null);}
                    break;
                case "right":
                    if(spriteNum == 1){g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);}
                    if(spriteNum == 2){g2.drawImage(image, screenX, screenY, gp.tileSize*2, gp.tileSize, null);}
                    break;
            }
        }

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }

    public void selectItem(){
        int itemIndex = gp.ui.getItemIndexOnSlot();

        if(itemIndex < gp.player.inventory.size()){
            SuperObject selectedItem = gp.player.inventory.get(itemIndex);
            if(selectedItem.type == 1){
                currentWeapon = selectedItem;
                damage = getAttack();
            }else if(selectedItem.type == 2){
                currentShield = selectedItem;
                defence = getDefence();
            }else if(selectedItem.type == 3){
                selectedItem.use(this);
                inventory.remove(selectedItem);
            }

            if(currentShield.haveSpeedAttribute || currentWeapon.haveSpeedAttribute){
                speed = getSpeed();
            }else{
                speed = defaultSpeed;
            }
            
        }

    }

    public void respawn(){  //gestisce la rinascità dopo la morte
        worldX = gp.player.checkPointX;
        worldY = gp.player.checkPointY;
        life = gp.player.maxLife;
        gp.gameState = GamePanel.playState;
        inventory.add(new OBJ_stampella(gp));
    }

}