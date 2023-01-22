package tile;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import Main.GamePanel;

public class TileManager {
    
    GamePanel gp;
    Tile[] tiles;
    int tileMapNum[][];

    public TileManager(GamePanel gp){ 

        this.gp=gp;

        tiles = new Tile[100];
        tileMapNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        loadMap("/res/maps/map01.txt");
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

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2){

        int col=0;
        int row=0;
        int x=0;
        int y=0;

        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            
            int mapTileNum = tileMapNum[col][row];

            g2.drawImage(tiles[mapTileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x=x+gp.tileSize;

            if(col==gp.maxScreenCol){
                col=0;
                x=0;
                row++;
                y=y+gp.tileSize;
            }

        }

    }
    public void loadMap(String filePath){

        try {
            
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                
                String line = reader.readLine();

                while (col < gp.maxScreenCol) {
                    
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    tileMapNum[col][row] = num;
                    col++;
                }

                if(col == gp.maxScreenCol){

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
