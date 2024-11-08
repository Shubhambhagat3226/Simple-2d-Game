package com.game.Entity;

import com.game.TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FireBall extends MapObject{

    private boolean hit;
    private boolean remove;
    private BufferedImage[] sprites;
    private BufferedImage[] hitSprites;

    // CONSTRUCTOR
    public FireBall(TileMap tileMap, boolean right) {
        super(tileMap);

        moveSpeed = 3.8;
        if (right) dx = moveSpeed;
        else dx = - moveSpeed;

        width = 30;
        height = 30;
        cwidth = 14;
        cheight = 14;

        // LOAD SPRITES
        try {
            BufferedImage spriteSheet = ImageIO.read(getClass()
                    .getResourceAsStream("/Sprites/Player/fireball.gif"));

            sprites = new BufferedImage[4];
            for (int i = 0; i < sprites.length; i++) {
                sprites[i] = spriteSheet.getSubimage(i * width, 0, width, height);
            }

            hitSprites = new BufferedImage[3];
            for (int i = 0; i < hitSprites.length; i++) {
                hitSprites[i] = spriteSheet.getSubimage(i * width, height, width, height);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(70);

    }

    // SET METHOD;
    public void setHit() {
        if (hit) return;
        hit = true;
        animation.setFrames(hitSprites);
        animation.setDelay(70);
        dx = 0;
    }


    // remove return
    public boolean shouldRemove(){
        return remove;
    }


    // UPDATE METHOD
    public void update() {
        checkTileMapCollision();
        setPosition(xtemp, ytemp);

        // REMOVE FIREBALL
        if (dx == 0 && !hit) {
            setHit();
        }

        // CALL ANIMATION UPDATE
        animation.update();
        if (hit && animation.hasPlayedOnce()) {
            remove = true;
        }
    }


    // DRAW METHOD
    public void draw(Graphics2D g) {
        setMapPosition();

        // FACE AT RIGHT SIDE
        if (facingRight) {
            g.drawImage(animation.getImage()
                    , (int) (x + xmap - width/2)
                    , (int) (y + ymap - height/2)
                    , null);
        }
        // FACE AT LEFT SIDE
        else {
            g.drawImage(animation.getImage()
                    , (int) (x + xmap - width/2 + width)
                    , (int) (y + ymap - height/2)
                    , -width
                    , height
                    , null);
        }
    }



}
