package Main;

import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Shoes;

public class AssetSetter {
    
    GamePanel gp;

    public AssetSetter(GamePanel gp){

        this.gp=gp;

    }

    public void setObject(){

        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 15 * gp.tileSize;
        gp.obj[0].worldY = 10 * gp.tileSize;

        gp.obj[1] = new OBJ_Door(); 
        gp.obj[1].worldX = 18 * gp.tileSize;
        gp.obj[1].worldY = 10 * gp.tileSize;

        gp.obj[2] = new OBJ_Shoes();
        gp.obj[2].worldX = 15 * gp.tileSize;
        gp.obj[2].worldY = 14 * gp.tileSize;

    }

}
