package com.game.Entity;

import com.game.Main.GamePanel;
import com.game.TileMap.Tile;
import com.game.TileMap.TileMap;

import java.awt.*;

public abstract class MapObject {
    // TILE STUFF
    protected TileMap tileMap;
    protected int tileSize;
    protected double xmap;
    protected double ymap;

    // POSITION AND VECTOR
    protected double x;
    protected double y;
    protected double dx;
    protected double dy;

    // DIMENSION
    protected int width;
    protected int height;

    // COLLISION BOX
    protected int cwidth;
    protected int cheight;

    // COLLISION
    protected int currRow;
    protected int currCol;
    protected double xdest;
    protected double ydest;
    protected double xtemp;
    protected double ytemp;

    protected boolean topRight;
    protected boolean topLeft;
    protected boolean bottomRight;
    protected boolean bottomLeft;

    // ANIMATION
    protected Animation animation;
    protected int currentAction;
    protected int previousAction;
    protected boolean facingRight;

    // MOVEMENT
    protected boolean left;
    protected boolean right;
    protected boolean up;
    protected boolean down;
    protected boolean jumping;
    protected boolean falling;

    // MOVEMENTS ATTRIBUTES
    protected double moveSpeed;
    protected double maxSpeed;
    protected double stopSpeed;
    protected double fallSpeed;
    protected double maxFallSpeed;
    protected double jumpStart;
    protected double stopJumpSpeed;


    // CONSTRUCTOR -  TO SET TILE-MAP AND TILE SIZE
    public MapObject(TileMap tileMap) {
        this.tileMap = tileMap;
        tileSize = tileMap.getTileSize();
    }


    // CHECK IF THE ENTITY IS NOT COLLIDE WITH OTHER ENTITY
    public boolean intersects(MapObject o) {
        Rectangle r1 = getRectangle();
        Rectangle r2 = o.getRectangle();
        return r1.intersects(r2);
    }

    private Rectangle getRectangle() {
        return new Rectangle( (int) x - cwidth
                , (int) y - cheight
                , cwidth
                , cheight );
    }


    // CHECK IF THE ENTITY IS NOT COLLIDE WITH TILE
    public void calculateCorners(double x, double y) {

        int leftTile   = (int) (x - cwidth / 2)    / tileSize;
        int rightTile  = (int) (x + cwidth / 2-1)  / tileSize;
        int topTile    = (int) (y - cheight / 2)   / tileSize;
        int bottomTile = (int) (y + cheight / 2-1) / tileSize;

        int tl = tileMap.getType( topTile, leftTile);
        int tr = tileMap.getType( topTile, rightTile);
        int bl = tileMap.getType( bottomTile, leftTile);
        int br = tileMap.getType( bottomTile, rightTile);

        topLeft = (tl == Tile.BLOCKED);
        topRight = (tr == Tile.BLOCKED);
        bottomLeft = (bl == Tile.BLOCKED);
        bottomRight = (br == Tile.BLOCKED);

    }

    public void checkTileMapCollision() {
        currCol = (int) x / tileSize;
        currRow = (int) y / tileSize;

        xdest = x + dx;
        ydest = y + dy;

        xtemp = x;
        ytemp = y;

        calculateCorners(x, ydest);
        // JUMP COLLISION
        if (dy < 0) {
            if (topRight || topLeft) {
                dy = 0;
                ytemp = currRow * tileSize + cheight / 2;
            } else {
                ytemp += dy;
            }
        }
        // FALLING COLLISION
        if (dy > 0) {
            if (bottomLeft || bottomRight) {
                dy = 0;
                falling = false;
                ytemp = (currRow + 1) * tileSize - cheight / 2;
            } else {
                ytemp += dy;
            }
        }

        calculateCorners(xdest, y);
        // LEFT SIDE COLLISION
        if (dx < 0) {
            if (topLeft || bottomLeft) {
                dx = 0;
                xtemp = currCol * tileSize + cwidth/2;
            } else {
                xtemp += dx;
            }
        }
        // RIGHT SIDE COLLISION
        if (dx > 0) {
            if (topRight || bottomRight) {
                dx = 0;
                xtemp = (currCol + 1) * tileSize - cwidth / 2;
            } else {
                xtemp += dx;
            }
        }

        // FALLING OR NOT
        if (!falling) {
            calculateCorners(x, ydest+1);
            if (!bottomLeft && !bottomRight) {
                falling = true;
            }
        }

    }


    // GETTER METHOD
    public int getX() { return (int) x; }
    public int getY() { return (int) y; }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getCwidth() {
        return cwidth;
    }
    public int getCheight() {
        return cheight;
    }


    // SET X AND Y POSITION
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // SET VECTOR - MOVEMENTS
    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    // MAP POSITION
    public void setMapPosition() {
        xmap = tileMap.getX();
        ymap = tileMap.getY();
    }

    // SETTER METHOD FOR MOVES
    public void setLeft(boolean left) {
        this.left = left;
    }
    public void setRight(boolean right) {
        this.right = right;
    }
    public void setUp(boolean up) {
        this.up = up;
    }
    public void setDown(boolean down) {
        this.down = down;
    }
    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean notOnScreen() {
        return x + xmap + width < 0
                || x + xmap - width > GamePanel.WIDTH
                || y + ymap + height < 0
                || y + ymap - height > GamePanel.HEIGHT;
    }
}















