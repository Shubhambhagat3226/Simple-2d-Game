package com.game.TileMap;

import com.game.Main.GamePanel;

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


    // SET POSITION OF X & Y -
    // TO MAKE IT STEAM MOVEABLE
    public void setPosition(double x, double y) {
        this.x = (x * moveScale) % GamePanel.WIDTH;
        this.y = (y * moveScale) % GamePanel.WIDTH;
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

        // ADD WIDTH OF PANEL IN X -
        // WHEN X IS -VE
        if (x < 0) {
            g.drawImage(image, (int) x + GamePanel.WIDTH, (int) y, null);
        }

        // REMOVE EXTRA WIDTH OF X -
        // WHEN X IS +VE
        if (x > 0) {
            g.drawImage(image, (int) x - GamePanel.WIDTH, (int) y, null);
        }
    }
}
