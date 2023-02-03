/*
 * Classe che imposta tutti gli oggetti
 */

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

    public void setObject(){        //cre gli oggetti e li posiziona

        gp.obj[0] = new OBJ_Key();  //oggetto crearo
        gp.obj[0].worldX = 15 * gp.tileSize;    //coordinate dell'oggetto creato
        gp.obj[0].worldY = 10 * gp.tileSize;

        gp.obj[1] = new OBJ_Door(); 
        gp.obj[1].worldX = 18 * gp.tileSize;
        gp.obj[1].worldY = 10 * gp.tileSize;

        gp.obj[2] = new OBJ_Shoes();
        gp.obj[2].worldX = 15 * gp.tileSize;
        gp.obj[2].worldY = 14 * gp.tileSize;
 
        gp.obj[3] = new OBJ_Chest();
        gp.obj[3].worldX = 20 *gp.tileSize;
        gp.obj[3].worldY = 14 * gp.tileSize;

    }

}
