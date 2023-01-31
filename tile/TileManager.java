package tile;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Main.GamePanel;



public class TileManager {
    
    GamePanel gp;
    public Tile[] tiles;
    public int tileMapNum[][];
    ArrayList<String> fileNames = new ArrayList<>();
    ArrayList<String> collisionStatus = new ArrayList<>();

    public TileManager(GamePanel gp){ 

        
        this.gp=gp;
        readTileData();

        tiles = new Tile[fileNames.size()];
        tileMapNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        loadMap("/res/maps/worldMap.txt");
        getTileImage();
    }

    private void readTileData(){        //read the tile data

        try {
            
            //READ TILE DATA
            InputStream is = getClass().getResourceAsStream("/res/tiles/TileData.txt");
            BufferedReader bf = new BufferedReader(new InputStreamReader(is));

            String line;

            while((line = bf.readLine()) != null){
                fileNames.add(line);
                collisionStatus.add(bf.readLine());

            }
            bf.close();



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getTileImage(){

        try {
            
            for (int i = 0; i < fileNames.size(); i++) {
                
                String fileName;
                boolean collision;

                //get the file name
                fileName = fileNames.get(i);
                if(collisionStatus.get(i).equals("true")){
                    collision = true;
                }else{
                    collision = false;
                }

                setuptile(i, fileName, collision);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setuptile(int i, String fileName, boolean collision){

        try {
            
            tiles[i] = new Tile();
            tiles[i].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + fileName));
            tiles[i].collision = collision;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2){

        int worldCol=0;
        int worldRow=0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            
            int mapTileNum = tileMapNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int scrrenX = worldX - gp.player.worldX + gp.player.screenX;
            int scrrenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                    g2.drawImage(tiles[mapTileNum].image, scrrenX, scrrenY, gp.tileSize, gp.tileSize, null);
                }

            g2.drawImage(tiles[mapTileNum].image, scrrenX, scrrenY, gp.tileSize, gp.tileSize, null);
            worldCol++;

            if(worldCol==gp.maxWorldCol){
                worldCol=0;
                worldRow++;
            }

        }

    }
    public void loadMap(String filePath){

        try {
            
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                
                String line = reader.readLine();

                while (col < gp.maxWorldCol) {
                    
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    tileMapNum[col][row] = num;
                    col++;
                }

                if(col == gp.maxWorldCol){

                    col=0;
                    row++;
                }
                

            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
