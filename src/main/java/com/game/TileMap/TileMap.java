package com.game.TileMap;

import com.game.Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileMap {

    // POSITION
    private double x;
    private double y;

    // BOUNDS
    private int xmin;
    private int ymin;
    private int xmax;
    private int ymax;

    private double tween;

    // MAP
    private int[][] map;


    private int tileSize;
    private int numRows;
    private int numCols;
    private int width;
    private int height;

    // TILE SET
    private BufferedImage tileSet;
    private int numOfTileAcross;
    private Tile[][] tiles;

    // DRAWING
    private int rowOffSet;
    private int colOffSet;
    private int numRowsToDraw;
    private int numColsToDraw;


    // CONSTRUCTOR - SET
    public TileMap(int tileSize) {
        this.tileSize = tileSize;
        numColsToDraw = GamePanel.WIDTH / tileSize + 2;
        numRowsToDraw = GamePanel.HEIGHT/ tileSize + 2;
        tween = 0.7;
    }


    // LOAD TILE
    public void loadTiles(String s) {

        try {
            // LOAD TILE IMAGE
            tileSet = ImageIO.read(getClass().getResourceAsStream(s));
            numOfTileAcross = tileSet.getWidth() / tileSize;
            tiles = new Tile[2][numOfTileAcross];

            BufferedImage subImage;
            for (int col = 0; col < numOfTileAcross; col++) {
                // UPPER TILE FROM IMAGE
                subImage = tileSet.getSubimage(col * tileSize, 0, tileSize, tileSize);
                tiles[0][col] = new Tile(subImage, Tile.NORMAL);

                // LOWER TILES FROM IMAGE
                subImage = tileSet.getSubimage(col * tileSize, tileSize, tileSize, tileSize);
                tiles[1][col] = new Tile(subImage, Tile.BLOCKED);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


    // LOAD MAP
    public void loadMap(String s) {

        try {
            InputStream in = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());
            map = new int[numRows][numCols];
            width = numCols * tileSize;
            height = numRows * tileSize;


            xmin = GamePanel.WIDTH - width;
            xmax = 0;
            ymin = GamePanel.HEIGHT - height;
            ymax = 0;

            String delims = "\\s+";
            for (int row = 0; row < numRows; row++) {
                String line = br.readLine();
                String[] tokens = line.split(delims);
                for (int col = 0; col < numCols; col++) {
                    map[row][col] = Integer.parseInt(tokens[col]);
                }
            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


    // GETTERS
    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // GETTER FOR TILE TYPE BY MAP
    public int getType(int row, int col) {
        int rc = map[row][col];
        int r = rc / numOfTileAcross;
        int c = rc % numOfTileAcross;
        return tiles[r][c].getType();
    }


    // SET POSITION
    public void setPosition(double x, double y) {
        this.x += (x - this.x) * tween;
        this.y += (y - this.y) * tween;

        fixBounds();

        colOffSet = (int) -this.x / tileSize;
        rowOffSet = (int) -this.y / tileSize;
    }

    private void fixBounds() {
        if (x < xmin) x = xmin;
        if (y < ymin) y = ymin;
        if (x > xmax) x = xmax;
        if (y > ymax) y = ymax;
    }


    // SET TWEEN
    public void setTween(double tween) {
        this.tween = tween;
    }

    // DRAW TILE GRAPHICS
    public void draw(Graphics2D g) {

        for (int row = rowOffSet; row < rowOffSet + numRowsToDraw; row++) {
            if (row >= numRows) break;

            for (int col = colOffSet; col < colOffSet + numColsToDraw; col++) {
                if (col >= numCols) break;
                if (map[row][col] == 0) continue;

                int rc = map[row][col];
                int r = rc / numOfTileAcross;
                int c = rc % numOfTileAcross;

                g.drawImage(tiles[r][c].getImage()
                        ,getX() + col*tileSize
                        , getY() + row*tileSize
                        , null);

            }
        }
    }

}
