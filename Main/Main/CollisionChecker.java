package Main;

import entity.Entity;

public class CollisionChecker {
    
    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp=gp;
    }

    public void checkTile(Entity entity){

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

}
