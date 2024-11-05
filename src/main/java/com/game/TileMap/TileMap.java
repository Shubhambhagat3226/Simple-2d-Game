package com.game.TileMap;

import com.game.Main.GamePanel;

import java.awt.image.BufferedImage;

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


    private int tiltSize;
    private int numRows;
    private int numCols;
    private int width;
    private int height;

    // TILE SET
    private BufferedImage image;
    private int numTileAcross;
    private Tile[][] tiles;

    // DRAWING
    private int rowOffSet;
    private int colOffSet;
    private int numRowsToDraw;
    private int numColsToDraw;


    // CONSTRUCTOR - SET
    public TileMap(int tiltSize) {
        this.tiltSize = tiltSize;
        numColsToDraw = GamePanel.WIDTH / tiltSize + 2;
        numRowsToDraw = GamePanel.HEIGHT/ tiltSize + 2;
        tween = 0.07;
    }

}
