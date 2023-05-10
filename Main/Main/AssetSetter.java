/*
 * Oggetto: posizionamento elementi vari (npc, oggetti, mostri...)
 */

package Main;

import entity.MON_Spider;
import entity.NPC_doganiere;
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

    }

    public void setNpc(){   //crea npc e li posiziona

        gp.npc[0] = new NPC_dio(gp);
        gp.npc[0].worldX = 13 * gp.tileSize;
        gp.npc[0].worldY = 16 * gp.tileSize;

        
    }

    public void setEnemy(){     //crea i nemici e li posiziona

    }
}