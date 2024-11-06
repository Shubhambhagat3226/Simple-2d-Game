package com.game.TileMap;

import java.awt.image.BufferedImage;

public class Tile {

    // IMAGE & TYPE
    private BufferedImage image;
    private int type;

    // TILE TYPE
    public static final int NORMAL = 0;
    public static final int BLOCKED = 1;


    // CONSTRUCTOR - TO SET IMAGE AND TYPE
    public Tile(BufferedImage image, int type) {
        this.image = image;
        this.type = type;
    }


    // GETTER FOR IMAGE
    public BufferedImage getImage() {
        return image;
    }


    // GETTER FOR TILE TYPE
    public int getType() {
        return type;
    }
}
