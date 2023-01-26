package tile;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import Main.GamePanel;

public class TileManager {
    
    GamePanel gp;
    public Tile[] tiles;
    public int tileMapNum[][];

    public TileManager(GamePanel gp){ 

        this.gp=gp;

        tiles = new Tile[100];
        tileMapNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        loadMap("/res/maps/worldMap.txt");
        getTileImage();
    }

    public void getTileImage(){

        try {
            
            for (int i = 0; i < tiles.length; i++) {
                tiles[i]= new Tile();
            }

            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass1.png"));
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass2.png"));
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass3.png"));
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water1.png"));
            tiles[3].collision=true;
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water2.png"));
            tiles[4].collision=true;
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/water3.png"));
            tiles[5].collision=true;
            tiles[6].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/path1.png"));
            tiles[7].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/path2.png"));
            tiles[8].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/path3.png"));
            tiles[9].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/treeTop.png"));
            tiles[9].collision=true;
            tiles[10].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/treeBottom.png"));
            tiles[10].collision=true;
            tiles[11].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/blueFlower.png"));
            tiles[12].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/yellowFlower.png"));
            tiles[13].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/pinkFlower.png"));


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
