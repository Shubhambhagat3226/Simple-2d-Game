package com.game.TileMap;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Background {

    // IMAGE
    private BufferedImage image;

    // SIZE AND DIMENSION
    private double x;
    private double y;
    private double dx;
    private double dy;

    // MOVEMENTS
    private double moveScale;


    // CONSTRUCTOR- TO LOAD IMAGE AND MOVEMENT
    public Background(String s, double ms) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(s));
            moveScale = ms;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    // SET POSITION
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }


    // SET VECTOR OF DIMENSION
    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }


    // UPDATE IMAGE POSITION WITH VECTOR
    public void update() {
        x += dx;
        y += dy;
    }

    public void draw(java.awt.Graphics2D g) {
        g.drawImage(image, (int)x, (int)y, null);
    }
}
