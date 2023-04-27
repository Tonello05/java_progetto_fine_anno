/*
 * Oggetto: posizionamento elementi vari (npc, oggetti, mostri...)
 */

package Main;

import entity.MON_Spider;
import entity.NPC_kolo;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Shoes;
import object.OBJ_coin;
import object.OBJ_inps;
import object.OBJ_messagge;
import object.OBJ_special_door;

public class AssetSetter {
    
    GamePanel gp;

    public AssetSetter(GamePanel gp){

        this.gp=gp;

    }

    public void setObject(){        //cre gli oggetti e li posiziona

              //commentato per provare i nemici
            gp.obj[0] = new OBJ_Key();
            gp.obj[0].worldX = 30 * gp.tileSize;
            gp.obj[0].worldY = 9 * gp.tileSize;

            gp.obj[1] = new OBJ_Door();
            gp.obj[1].worldX = 36 * gp.tileSize;
            gp.obj[1].worldY = 10 * gp.tileSize;

            gp.obj[2] = new OBJ_coin();
            gp.obj[2].worldX = 39 * gp.tileSize;
            gp.obj[2].worldY = 8 * gp.tileSize;

            gp.obj[3] = new OBJ_coin();
            gp.obj[3].worldX = 44 * gp.tileSize;
            gp.obj[3].worldY = 8 * gp.tileSize;

            gp.obj[4] = new OBJ_Shoes();
            gp.obj[4].worldX = 41 * gp.tileSize;
            gp.obj[4].worldY = 8 * gp.tileSize;

            gp.obj[5] = new OBJ_messagge(0, gp);
            gp.obj[5].worldX = 15 * gp.tileSize;
            gp.obj[5].worldY = 10 * gp.tileSize;

            gp.obj[6] = new OBJ_special_door(gp, 1);
            gp.obj[6].worldX = 23 * gp.tileSize;
            gp.obj[6].worldY = 10 * gp.tileSize;

    }

    public void setNpc(){   //crea npc e li posiziona
        
        gp.npc[0] = new NPC_kolo(gp);   //creazione oggetto
        gp.npc[0].worldX = 26 * gp.tileSize;    // coordinata x dell'npc
        gp.npc[0].worldY = 9 * gp.tileSize;    // coordinata y dell'npc
        
    }

    public void setEnemy(){     //crea i nemici e li posiziona
        
        gp.enemy[0] = new MON_Spider(gp);
        gp.enemy[0].worldX = 18 * gp.tileSize;
        gp.enemy[0].worldY = 10 * gp.tileSize;
        
    }

}
