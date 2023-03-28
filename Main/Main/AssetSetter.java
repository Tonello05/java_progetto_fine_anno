/*
 * Classe che imposta tutti gli oggetti
 */

package Main;

import entity.NPC_kolo;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Shoes;
import object.OBJ_coin;
import object.OBJ_inps;

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

        gp.obj[4] = new OBJ_Key();  //oggetto crearo
        gp.obj[4].worldX = 12 * gp.tileSize;    //coordinate dell'oggetto creato
        gp.obj[4].worldY = 10 * gp.tileSize;

        gp.obj[5] = new OBJ_inps();
        gp.obj[5].worldX = 15 * gp.tileSize;    //coordinate dell'oggetto creato
        gp.obj[5].worldY = 15 * gp.tileSize;

        gp.obj[6] = new OBJ_coin();
        gp.obj[6].worldX = 17 * gp.tileSize;    //coordinate dell'oggetto creato
        gp.obj[6].worldY = 17 * gp.tileSize;

    }

    public void setNpc(){

        gp.npc[0] = new NPC_kolo(gp);
        gp.npc[0].worldX = 30 * gp.tileSize;
        gp.npc[0].worldY = 15 * gp.tileSize;

    }

}
