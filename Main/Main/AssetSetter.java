/*
 * Oggetto: posizionamento elementi vari (npc, oggetti, mostri...)
 */

package Main;

import entity.MON_Spider;
import entity.NPC_dio;
import entity.NPC_jhoncena;
import entity.NPC_kolo;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;
import object.OBJ_Shoes;
import object.OBJ_Sword_normal;
import object.OBJ_axe;
import object.OBJ_blue_shield;
import object.OBJ_coin;
import object.OBJ_inps;
import object.OBJ_messagge;
import object.OBJ_red_pot;
import object.OBJ_special_door;

public class AssetSetter {
    
    GamePanel gp;

    public AssetSetter(GamePanel gp){

        this.gp=gp;

    }

    public void setObject(){        //cre gli oggetti e li posiziona

        gp.obj[0] = new OBJ_messagge(0, gp);
        gp.obj[0].worldX = 19 * gp.tileSize;
        gp.obj[0].worldY = 20 * gp.tileSize; 

        gp.obj[1] = new OBJ_messagge(1, gp);
        gp.obj[1].worldX = 26 * gp.tileSize;
        gp.obj[1].worldY = 22 * gp.tileSize; 

        gp.obj[2] = new OBJ_messagge(2, gp);
        gp.obj[2].worldX = 30 * gp.tileSize;
        gp.obj[2].worldY = 14 * gp.tileSize; 
        
        gp.obj[3] = new OBJ_messagge(3, gp);
        gp.obj[3].worldX = 44 * gp.tileSize;
        gp.obj[3].worldY = 41 * gp.tileSize;

        gp.obj[4] = new OBJ_red_pot(gp);
        gp.obj[4].worldX = 20 * gp.tileSize;
        gp.obj[4].worldY = 22 * gp.tileSize;

        gp.obj[5] = new OBJ_coin();
        gp.obj[5].worldX = 18 * gp.tileSize;
        gp.obj[5].worldY = 22 * gp.tileSize;

        gp.obj[6] = new OBJ_coin();
        gp.obj[6].worldX = 15 * gp.tileSize;
        gp.obj[6].worldY = 21 * gp.tileSize;

        gp.obj[7] = new OBJ_blue_shield();
        gp.obj[7].worldX = 28 * gp.tileSize;
        gp.obj[7].worldY = 24 * gp.tileSize;

        gp.obj[8] = new OBJ_Sword_normal();
        gp.obj[8].worldX = 29 * gp.tileSize;
        gp.obj[8].worldY = 25 * gp.tileSize;

        gp.obj[9] = new OBJ_Shoes();
        gp.obj[9].worldX = 42 * gp.tileSize;
        gp.obj[9].worldY = 34 * gp.tileSize;

    }

    public void setNpc(){   //crea npc e li posiziona
        
        gp.npc[0] = new NPC_kolo(gp);
        gp.npc[0].worldX = 44 * gp.tileSize;
        gp.npc[0].worldY = 36 * gp.tileSize;

        gp.npc[1] = new NPC_dio(gp);
        gp.npc[1].worldX = 28 * gp.tileSize;
        gp.npc[1].worldY = 14 * gp.tileSize;

        
    }

    public void setEnemy(){     //crea i nemici e li posiziona

        gp.enemy[0] = new MON_Spider(gp);
        gp.enemy[0].worldX = 47 * gp.tileSize;
        gp.enemy[0].worldY = 44 * gp.tileSize;

    }

}
