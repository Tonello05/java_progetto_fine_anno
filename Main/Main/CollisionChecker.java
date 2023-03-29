/*
 * Classe che gestisce le collisioni
 */

package Main;

import entity.Entity;

public class CollisionChecker {
    
    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp=gp;
    }

    public void checkTile(Entity entity){   //controlla le collisioni con la mappa

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y+32;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height+16;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;
        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                    entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                    tileNum1 = gp.tileManager.tileMapNum[entityLeftCol][entityTopRow];
                    tileNum2 = gp.tileManager.tileMapNum[entityRightCol][entityTopRow];
                    if(gp.tileManager.tiles[tileNum1].collision == true || gp.tileManager.tiles[tileNum2].collision == true){

                        entity.collisionIsOn = true;

                    }

                break;
        
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.tileMapNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileManager.tileMapNum[entityRightCol][entityBottomRow];
                if(gp.tileManager.tiles[tileNum1].collision == true || gp.tileManager.tiles[tileNum2].collision == true){

                    entity.collisionIsOn = true;

                }
                break;

            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.tileMapNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.tileMapNum[entityLeftCol][entityBottomRow];
                if(gp.tileManager.tiles[tileNum1].collision == true || gp.tileManager.tiles[tileNum2].collision == true){

                    entity.collisionIsOn = true;

                }
                break;

            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.tileMapNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileManager.tileMapNum[entityRightCol][entityBottomRow];
                if(gp.tileManager.tiles[tileNum1].collision == true || gp.tileManager.tiles[tileNum2].collision == true){

                    entity.collisionIsOn = true;

                }
                break;
        }

    }

    public int checkObject(Entity entity, boolean player){  //gestisce le collisioni gon li oggetti

        int index = 999;

        for (int i = 0; i < gp.obj.length; i++) {
            
            if(gp.obj[i] != null){

                //get entity solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //get the object solid area position

                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision == true){
                                entity.collisionIsOn=true;
                            }
                            if(player = true){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision == true){
                                entity.collisionIsOn=true;
                            }
                            if(player = true){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision == true){
                                entity.collisionIsOn=true;
                            }
                            if(player = true){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if(gp.obj[i].collision == true){
                                entity.collisionIsOn=true;
                            }
                            if(player = true){
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;

            }

        }

        return index;

    }

    //NPCs or Monsters
    public int checEntity(Entity entity, Entity[] target){

        int index = 999;

        for (int i = 0; i < target.length; i++) {
            
            if(target[i] != null){

                //get entity solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                //get the object solid area position

                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                                entity.collisionIsOn=true;
                                index = i;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                                entity.collisionIsOn=true;
                                index = i;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                                entity.collisionIsOn=true;
                                index = i;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                                entity.collisionIsOn=true;
                                index = i;
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;

            }

        }

        return index;

    }

    public void checkPalyer(Entity entity){

            //get entity solid area position
            entity.solidArea.x = entity.worldX + entity.solidArea.x;
            entity.solidArea.y = entity.worldY + entity.solidArea.y;

            //get the object solid area position

            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

            switch (entity.direction) {
                case "up":
                    entity.solidArea.y -= entity.speed;
                    if (entity.solidArea.intersects(gp.player.solidArea)) {
                            entity.collisionIsOn=true;
                    }
                    break;
                case "down":
                    entity.solidArea.y += entity.speed;
                    if (entity.solidArea.intersects(gp.player.solidArea)) {
                            entity.collisionIsOn=true;
                    }
                    break;
                case "left":
                    entity.solidArea.x -= entity.speed;
                    if (entity.solidArea.intersects(gp.player.solidArea)) {
                            entity.collisionIsOn=true;
                    }
                    break;
                case "right":
                    entity.solidArea.x += entity.speed;
                    if (entity.solidArea.intersects(gp.player.solidArea)) {
                            entity.collisionIsOn=true;
                    }
                    break;
                }
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;

    }

}
