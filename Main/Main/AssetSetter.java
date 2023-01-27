package Main;

import object.OBJ_Key;
import object.SuperObject;

public class AssetSetter {
    
    GamePanel gp;

    public AssetSetter(GamePanel gp){

        this.gp=gp;

    }

    public void setObject(){

        gp.obj[0]= new OBJ_Key();
        gp.obj[0].worldX = 6 * gp.tileSize;
        gp.obj[0].worldY = 3 * gp.tileSize;
        

    }

}
