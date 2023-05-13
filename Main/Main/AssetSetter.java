/*
 * Oggetto: posizionamento elementi vari (npc, oggetti, mostri...)
 */

package Main;

import decorations.DEC_armadio;
import decorations.DEC_bed;
import decorations.DEC_bocce;
import decorations.DEC_carpet;
import decorations.DEC_flower;
import decorations.DEC_lilypad;
import decorations.DEC_painting;
import decorations.DEC_sgabello;
import decorations.DEC_tavolino;
import decorations.DEC_water_rock;
import entity.MON_Spider;
import entity.MON_wasp;
import entity.NPC_abu;
import entity.NPC_agazzi;
import entity.NPC_cuconato;
import entity.NPC_doganiere;
import entity.NPC_dio;
import entity.NPC_jhoncena;
import entity.NPC_kolo;
<<<<<<< HEAD
import entity.NPC_miotto;
import entity.NPC_slotmachine_left;
import entity.NPC_slotmachine_right;
=======
import entity.NPC_slotmachine;
import entity.NPC_wen;
>>>>>>> c8e7e8996aa815e8449cf58cd6b2cdac5e1227d8
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

        /* DECORAZIONI */

        setDecorations();

        /* ITEMS */

    }

    public void setDecorations(){

        /* A PARTIRE DALL'INDICE 100 */

        /* DECORAZIONI ACQUA */

        gp.obj[100] = new DEC_water_rock(gp, 1);
        gp.obj[100].worldX = 3 * gp.tileSize;
        gp.obj[100].worldY = 9 * gp.tileSize;

        gp.obj[101] = new DEC_water_rock(gp, 1);
        gp.obj[101].worldX = 12 * gp.tileSize;
        gp.obj[101].worldY = 4 * gp.tileSize;

        gp.obj[102] = new DEC_water_rock(gp, 1);
        gp.obj[102].worldX = 28 * gp.tileSize;
        gp.obj[102].worldY = 1 * gp.tileSize;

        gp.obj[103] = new DEC_water_rock(gp, 1);
        gp.obj[103].worldX = 49 * gp.tileSize;
        gp.obj[103].worldY = 4 * gp.tileSize;

        gp.obj[104] = new DEC_water_rock(gp, 1);
        gp.obj[104].worldX = 67 * gp.tileSize;
        gp.obj[104].worldY = 7 * gp.tileSize;

        gp.obj[105] = new DEC_water_rock(gp, 1);
        gp.obj[105].worldX = 86 * gp.tileSize;
        gp.obj[105].worldY = 7 * gp.tileSize;

        gp.obj[106] = new DEC_water_rock(gp, 1);
        gp.obj[106].worldX = 25 * gp.tileSize;
        gp.obj[106].worldY = 7 * gp.tileSize;
        
        gp.obj[107] = new DEC_water_rock(gp, 0);
        gp.obj[107].worldX = 27 * gp.tileSize;
        gp.obj[107].worldY = 8 * gp.tileSize;

        gp.obj[108] = new DEC_lilypad(gp, 0);
        gp.obj[108].worldX = 24 * gp.tileSize;
        gp.obj[108].worldY = 9 * gp.tileSize;

        gp.obj[109] = new DEC_lilypad(gp, 1);
        gp.obj[109].worldX = 27 * gp.tileSize;
        gp.obj[109].worldY = 6 * gp.tileSize;

        gp.obj[110] = new DEC_water_rock(gp, 0);
        gp.obj[110].worldX = 96 * gp.tileSize;
        gp.obj[110].worldY = 25 * gp.tileSize;

        gp.obj[111] = new DEC_water_rock(gp, 0);
        gp.obj[111].worldX = 84 * gp.tileSize;
        gp.obj[111].worldY = 24 * gp.tileSize;

        gp.obj[112] = new DEC_water_rock(gp, 0);
        gp.obj[112].worldX = 74 * gp.tileSize;
        gp.obj[112].worldY = 23 * gp.tileSize;

        gp.obj[113] = new DEC_water_rock(gp, 0);
        gp.obj[113].worldX = 61 * gp.tileSize;
        gp.obj[113].worldY = 23 * gp.tileSize;

        gp.obj[114] = new DEC_water_rock(gp, 0);
        gp.obj[114].worldX = 42 * gp.tileSize;
        gp.obj[114].worldY = 27 * gp.tileSize;

        gp.obj[115] = new DEC_water_rock(gp, 0);
        gp.obj[115].worldX = 28 * gp.tileSize;
        gp.obj[115].worldY = 26 * gp.tileSize;

        gp.obj[116] = new DEC_water_rock(gp, 0);
        gp.obj[116].worldX = 12 * gp.tileSize;
        gp.obj[116].worldY = 25 * gp.tileSize;

        gp.obj[117] = new DEC_water_rock(gp, 0);
        gp.obj[117].worldX = 1 * gp.tileSize;
        gp.obj[117].worldY = 22 * gp.tileSize;

        /* LETTI */

        gp.obj[119] = new DEC_bed(gp, 1);
        gp.obj[119].worldX = 83 * gp.tileSize;
        gp.obj[119].worldY = 28 * gp.tileSize;
        
        gp.obj[120] = new DEC_bed(gp, 2);
        gp.obj[120].worldX = 76 * gp.tileSize;
        gp.obj[120].worldY = 28 * gp.tileSize;
        
        gp.obj[121] = new DEC_bed(gp, 2);
        gp.obj[121].worldX = 77 * gp.tileSize;
        gp.obj[121].worldY = 28 * gp.tileSize;

        gp.obj[122] = new DEC_bed(gp, 0);
        gp.obj[122].worldX = 77 * gp.tileSize;
        gp.obj[122].worldY = 43 * gp.tileSize;

        gp.obj[123] = new DEC_bed(gp, 1);
        gp.obj[123].worldX = 93 * gp.tileSize;
        gp.obj[123].worldY = 42 * gp.tileSize;

        gp.obj[124] = new DEC_bed(gp, 1);
        gp.obj[124].worldX = 94 * gp.tileSize;
        gp.obj[124].worldY = 42 * gp.tileSize;

        gp.obj[125] = new DEC_bed(gp, 2);
        gp.obj[125].worldX = 71 * gp.tileSize;
        gp.obj[125].worldY = 55 * gp.tileSize;

        gp.obj[126] = new DEC_bed(gp, 0);
        gp.obj[126].worldX = 84 * gp.tileSize;
        gp.obj[126].worldY = 68 * gp.tileSize;

        gp.obj[127] = new DEC_bed(gp, 0);
        gp.obj[127].worldX = 85 * gp.tileSize;
        gp.obj[127].worldY = 68 * gp.tileSize;

        gp.obj[130] = new DEC_bed(gp, 2);
        gp.obj[130].worldX = 16 * gp.tileSize;
        gp.obj[130].worldY = 83 * gp.tileSize;

        gp.obj[131] = new DEC_bed(gp, 0);
        gp.obj[131].worldX = 84 * gp.tileSize;
        gp.obj[131].worldY = 88 * gp.tileSize;

        /* TAPPETI */

        gp.obj[132] = new DEC_carpet(gp, 0);
        gp.obj[132].worldX = 77 * gp.tileSize;
        gp.obj[132].worldY = 31 * gp.tileSize;

        gp.obj[133] = new DEC_carpet(gp, 1);
        gp.obj[133].worldX = 85 * gp.tileSize;
        gp.obj[133].worldY = 31 * gp.tileSize;

        gp.obj[134] = new DEC_carpet(gp, 2);
        gp.obj[134].worldX = 97 * gp.tileSize;
        gp.obj[134].worldY = 31 * gp.tileSize;

        gp.obj[135] = new DEC_carpet(gp, 0);
        gp.obj[135].worldX = 80 * gp.tileSize;
        gp.obj[135].worldY = 39 * gp.tileSize;

        gp.obj[136] = new DEC_carpet(gp, 1);
        gp.obj[136].worldX = 96 * gp.tileSize;
        gp.obj[136].worldY = 39 * gp.tileSize;

        gp.obj[137] = new DEC_carpet(gp, 2);
        gp.obj[137].worldX = 74 * gp.tileSize;
        gp.obj[137].worldY = 55 * gp.tileSize;

        gp.obj[138] = new DEC_carpet(gp, 0);
        gp.obj[138].worldX = 81 * gp.tileSize; 
        gp.obj[138].worldY = 66 * gp.tileSize;

        gp.obj[139] = new DEC_carpet(gp, 1);
        gp.obj[139].worldX = 95 * gp.tileSize;
        gp.obj[139].worldY = 66 * gp.tileSize;

        gp.obj[140] = new DEC_carpet(gp, 2);
        gp.obj[140].worldX = 13 * gp.tileSize;
        gp.obj[140].worldY = 88 * gp.tileSize;

        gp.obj[141] = new DEC_carpet(gp, 0);
        gp.obj[141].worldX = 83 * gp.tileSize;
        gp.obj[141].worldY = 91 * gp.tileSize;

        gp.obj[142] = new DEC_armadio(gp);
        gp.obj[142].worldX = 86 * gp.tileSize;
        gp.obj[142].worldY = 28 * gp.tileSize;

        gp.obj[143] = new DEC_armadio(gp);
        gp.obj[143].worldX = 86 * gp.tileSize;
        gp.obj[143].worldY = 28 * gp.tileSize;

        gp.obj[144] = new DEC_armadio(gp);
        gp.obj[144].worldX = 86 * gp.tileSize;
        gp.obj[144].worldY = 28 * gp.tileSize;

        gp.obj[145] = new DEC_sgabello(gp);
        gp.obj[145].worldX = 91 * gp.tileSize;
        gp.obj[145].worldY = 66 * gp.tileSize;

        gp.obj[146] = new DEC_sgabello(gp);
        gp.obj[146].worldX = 91 * gp.tileSize;
        gp.obj[146].worldY = 67 * gp.tileSize;

        gp.obj[147] = new DEC_sgabello(gp);
        gp.obj[147].worldX = 91 * gp.tileSize;
        gp.obj[147].worldY = 68 * gp.tileSize;

        gp.obj[148] = new DEC_sgabello(gp);
        gp.obj[148].worldX = 91 * gp.tileSize;
        gp.obj[148].worldY = 69 * gp.tileSize;

        /* NEGOZIO DI WEN */

        gp.obj[149] = new DEC_tavolino(gp);
        gp.obj[149].worldX = 94 * gp.tileSize;
        gp.obj[149].worldY = 29 * gp.tileSize;

        gp.obj[150] = new DEC_tavolino(gp);
        gp.obj[150].worldX = 96 * gp.tileSize;
        gp.obj[150].worldY = 29 * gp.tileSize;

        gp.obj[151] = new DEC_tavolino(gp);
        gp.obj[151].worldX = 97 * gp.tileSize;
        gp.obj[151].worldY = 29 * gp.tileSize;

        gp.obj[152] = new DEC_armadio(gp);
        gp.obj[152].worldX = 97 * gp.tileSize;
        gp.obj[152].worldY = 28 * gp.tileSize;

        gp.obj[153] = new DEC_flower(gp, 0);
        gp.obj[153].worldX = 96 * gp.tileSize;
        gp.obj[153].worldY = 29 * gp.tileSize;

        gp.obj[154] = new DEC_flower(gp, 1);
        gp.obj[154].worldX = 94 * gp.tileSize;
        gp.obj[154].worldY = 29 * gp.tileSize;

        gp.obj[155] = new DEC_flower(gp, 2);
        gp.obj[155].worldX = 97 * gp.tileSize;
        gp.obj[155].worldY = 29 * gp.tileSize;

        gp.obj[156] = new DEC_painting(gp, 0);
        gp.obj[156].worldX = 96 * gp.tileSize;
        gp.obj[156].worldY = 27 * gp.tileSize;

        gp.obj[157] = new DEC_tavolino(gp);
        gp.obj[157].worldX = 86 * gp.tileSize;
        gp.obj[157].worldY = 30 * gp.tileSize;

        gp.obj[158] = new DEC_sgabello(gp);
        gp.obj[158].worldX = 86 * gp.tileSize;
        gp.obj[158].worldY = 31 * gp.tileSize;

        gp.obj[159] = new DEC_sgabello(gp);
        gp.obj[159].worldX = 86 * gp.tileSize;
        gp.obj[159].worldY = 29 * gp.tileSize;

        gp.obj[160] = new DEC_tavolino(gp);
        gp.obj[160].worldX = 80 * gp.tileSize;
        gp.obj[160].worldY = 42 * gp.tileSize;

        gp.obj[161] = new DEC_sgabello(gp);
        gp.obj[161].worldX = 80 * gp.tileSize;
        gp.obj[161].worldY = 43 * gp.tileSize;

        gp.obj[162] = new DEC_sgabello(gp);
        gp.obj[162].worldX = 80 * gp.tileSize;
        gp.obj[162].worldY = 41 * gp.tileSize;

        gp.obj[163] = new DEC_tavolino(gp);
        gp.obj[163].worldX = 94 * gp.tileSize;
        gp.obj[163].worldY = 40 * gp.tileSize;

        gp.obj[164] = new DEC_sgabello(gp);
        gp.obj[164].worldX = 93 * gp.tileSize;
        gp.obj[164].worldY = 40 * gp.tileSize;

        gp.obj[165] = new DEC_sgabello(gp);
        gp.obj[165].worldX = 95 * gp.tileSize;
        gp.obj[165].worldY = 40 * gp.tileSize;

        gp.obj[166] = new DEC_tavolino(gp);
        gp.obj[166].worldX = 81 * gp.tileSize;
        gp.obj[166].worldY = 69 * gp.tileSize;

        gp.obj[167] = new DEC_sgabello(gp);
        gp.obj[167].worldX = 80 * gp.tileSize;
        gp.obj[167].worldY = 69 * gp.tileSize;

        gp.obj[168] = new DEC_sgabello(gp);
        gp.obj[168].worldX = 82 * gp.tileSize;
        gp.obj[168].worldY = 69 * gp.tileSize;

        gp.obj[169] = new DEC_tavolino(gp);
        gp.obj[169].worldX = 74 * gp.tileSize;
        gp.obj[169].worldY = 57 * gp.tileSize;

        gp.obj[170] = new DEC_sgabello(gp);
        gp.obj[170].worldX = 75 * gp.tileSize;
        gp.obj[170].worldY = 57 * gp.tileSize;

        gp.obj[171] = new DEC_sgabello(gp);
        gp.obj[171].worldX = 73 * gp.tileSize;
        gp.obj[171].worldY = 57 * gp.tileSize;

        gp.obj[172] = new DEC_tavolino(gp);
        gp.obj[172].worldX = 11 * gp.tileSize;
        gp.obj[172].worldY = 86 * gp.tileSize;

        gp.obj[173] = new DEC_tavolino(gp);
        gp.obj[173].worldX = 11 * gp.tileSize;
        gp.obj[173].worldY = 87 * gp.tileSize;

        gp.obj[174] = new DEC_sgabello(gp);
        gp.obj[174].worldX = 10 * gp.tileSize;
        gp.obj[174].worldY = 86 * gp.tileSize;

        gp.obj[175] = new DEC_sgabello(gp);
        gp.obj[175].worldX = 10 * gp.tileSize;
        gp.obj[175].worldY = 87 * gp.tileSize;

        gp.obj[176] = new DEC_sgabello(gp);
        gp.obj[176].worldX = 12 * gp.tileSize;
        gp.obj[176].worldY = 86 * gp.tileSize;

        gp.obj[177] = new DEC_sgabello(gp);
        gp.obj[177].worldX = 12 * gp.tileSize;
        gp.obj[177].worldY = 87 * gp.tileSize;

        /* BOCCE */
        
        gp.obj[178] = new DEC_bocce(gp, 3);
        gp.obj[178].worldX = 97 * gp.tileSize;
        gp.obj[178].worldY = 93 * gp.tileSize;

        gp.obj[179] = new DEC_bocce(gp, 1);
        gp.obj[179].worldX = 95 * gp.tileSize;
        gp.obj[179].worldY = 93 * gp.tileSize;

        gp.obj[180] = new DEC_bocce(gp, 2);
        gp.obj[180].worldX = 97 * gp.tileSize;
        gp.obj[180].worldY = 94 * gp.tileSize;

        gp.obj[181] = new DEC_bocce(gp, 0);
        gp.obj[181].worldX = 96 * gp.tileSize;
        gp.obj[181].worldY = 94 * gp.tileSize;

    }

    public void setNpc(){   //crea npc e li posiziona

        gp.npc[0] = new NPC_dio(gp);
        gp.npc[0].worldX = 13 * gp.tileSize;
        gp.npc[0].worldY = 16 * gp.tileSize;

        gp.npc[1] = new NPC_slotmachine_right(gp);
        gp.npc[1].worldX = 90 * gp.tileSize;
        gp.npc[1].worldY = 66 * gp.tileSize;

        gp.npc[2] = new NPC_slotmachine_right(gp);
        gp.npc[2].worldX = 90 * gp.tileSize;
        gp.npc[2].worldY = 67 * gp.tileSize;

        gp.npc[3] = new NPC_slotmachine_right(gp);
        gp.npc[3].worldX = 90 * gp.tileSize;
        gp.npc[3].worldY = 68 * gp.tileSize;

        gp.npc[4] = new NPC_slotmachine_right(gp);
        gp.npc[4].worldX = 90 * gp.tileSize;
        gp.npc[4].worldY = 69 * gp.tileSize;

        gp.npc[5] = new NPC_slotmachine_left(gp);
        gp.npc[5].worldX = 96 * gp.tileSize;
        gp.npc[5].worldY = 67 * gp.tileSize;

        gp.npc[6] = new NPC_slotmachine_left(gp);
        gp.npc[6].worldX = 96 * gp.tileSize;
        gp.npc[6].worldY = 68 * gp.tileSize;

        gp.npc[7] = new NPC_slotmachine_left(gp);
        gp.npc[7].worldX = 96 * gp.tileSize;
        gp.npc[7].worldY = 69 * gp.tileSize;

<<<<<<< HEAD
        gp.npc[8] = new NPC_slotmachine_left(gp);
        gp.npc[8].worldX = 96 * gp.tileSize;
        gp.npc[8].worldY = 70 * gp.tileSize;

        gp.npc[9] = new NPC_kolo(gp);
        gp.npc[9].worldX = 80 * gp.tileSize;
        gp.npc[9].worldY = 33 * gp.tileSize;

        gp.npc[10] = new NPC_abu(gp);
        gp.npc[10].worldX = 91 * gp.tileSize;
        gp.npc[10].worldY = 66 * gp.tileSize;

        gp.npc[11] = new NPC_agazzi(gp);
        gp.npc[11].worldX = 91 * gp.tileSize;
        gp.npc[11].worldY = 67 * gp.tileSize;

        gp.npc[12] = new NPC_miotto(gp);
        gp.npc[12].worldX = 91 * gp.tileSize;
        gp.npc[12].worldY = 68 * gp.tileSize;

        gp.npc[13] = new NPC_cuconato(gp);
        gp.npc[13].worldX = 91 * gp.tileSize;
        gp.npc[13].worldY = 69 * gp.tileSize;
=======
        gp.npc[8] = new NPC_kolo(gp);
        gp.npc[8].worldX = 84 * gp.tileSize;
        gp.npc[8].worldY = 31 * gp.tileSize;

        gp.npc[9] = new NPC_abu(gp);
        gp.npc[9].worldX = 91 * gp.tileSize;
        gp.npc[9].worldY = 66 * gp.tileSize;

        gp.npc[10] = new NPC_wen(gp);
        gp.npc[10].worldX = 95 * gp.tileSize;
        gp.npc[10].worldY = 29 * gp.tileSize;
>>>>>>> c8e7e8996aa815e8449cf58cd6b2cdac5e1227d8

        

    }

    public void setEnemy(){     //crea i nemici e li posiziona

        gp.enemy[0] = new MON_wasp(gp);
        gp.enemy[0].worldX = 20*gp.tileSize;
        gp.enemy[0].worldY = 20*gp.tileSize;

    }

}