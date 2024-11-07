package com.game.Entity;

import com.game.TileMap.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends MapObject{

    // PLAYER STUFF
    private int health;
    private int maxHealth;
    private int fire;
    private int maxFire;
    private boolean dead;
    private boolean flinching;
    private long flinchTime;

    // FIREBALL
    private boolean firing;
    private int fireCost;
    private int fireBallDamage;
    //private ArrayList<FireBall> fireBalls;

    // SCRATCH
    private boolean scratching;
    private int scratchDamage;
    private int scratchRange;

    // GLIDING
    private boolean gliding;

    // ANIMATIONS STUFF
    private ArrayList<BufferedImage[]> spirits;
    private final int[] numFrames = {2, 8, 1, 2, 4, 2, 5};

    // ANIMATIONS ACTIONS
    private static final int IDLE = 0;
    private static final int WALKING = 1;
    private static final int JUMPING = 2;
    private static final int FALLING = 3;
    private static final int GLIDING = 4;
    private static final int FIREBALL = 5;
    private static final int SCRATCHING = 6;



    public Player(TileMap tileMap) {
        super(tileMap);

        width = 30;
        health = 30;
        cwidth = 20;
        cheight = 20;

        moveSpeed = 0.3;
        maxSpeed = 1.6;
        stopSpeed = 0.4;
        fallSpeed = 0.15;
        maxFallSpeed = 4.0;
        jumpStart = -4.8;
        stopJumpSpeed = 0.3;

        facingRight = true;

        health = maxHealth = 5;
        fire = maxFire = 2500;

        fireCost = 200;
        fireBallDamage = 5;
        //fireBalls = new ArrayList<FireBall>();

        scratchDamage = 8;
        scratchRange = 40;

        // LOAD SPIRITS
        try {
            BufferedImage spiritSheets = ImageIO.read(getClass()
                    .getResourceAsStream("/Sprites/Player/playersprites.gif"));

            for (int i = 0; i < numFrames.length; i++) {
                BufferedImage[] bi = new BufferedImage[numFrames[i]];

                for (int j = 0; j < numFrames[i]; j++) {
                    if (i != numFrames.length - 1) {
                        bi[j] = spiritSheets
                                .getSubimage(j * width
                                        , i * height
                                        , width, height);
                    } else {
                        bi[j] = spiritSheets
                                .getSubimage(j * width * 2
                                        , i * height
                                        , width, height);
                    }

                }

                spirits.add(bi);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }


    // GETTER METHOD


    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getFire() {
        return fire;
    }

    public int getMaxFire() {
        return maxFire;
    }


    // SETTER METHODS
    public void setFiring() {
        firing = true;
    }

    public void setScratching() {
        scratching = true;
    }

    public void setGliding(boolean b) {
        gliding = b;
    }


    // UPDATE METHOD
    public void update() {

        // UPDATE POSITION
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp, ytemp);


        // SET ANIMATIONS
        // SCRATCHING ATTACK STATE
        if (scratching) {
            if (currentAction != SCRATCHING) {
                currentAction = SCRATCHING;
                animation.setFrames(spirits.get(SCRATCHING));
                animation.setDelay(50);
                width = 60;
            }
        }

        // FIREBALL ATTACK STATE
        else if (firing) {
            if (currentAction != FIREBALL) {
                currentAction = FIREBALL;
                animation.setFrames(spirits.get(FIREBALL));
                animation.setDelay(100);
                width = 30;
            }
        }

        // MOVEMENT IS DOWNWARD
        else if (dy > 0) {
            // GLIDING STATE
            if (gliding) {
                if (currentAction != GLIDING) {
                    currentAction = GLIDING;
                    animation.setFrames(spirits.get(GLIDING));
                    animation.setDelay(100);
                    width = 30;
                }
            }
            // FALLING STATE
            else if (currentAction != FALLING) {
                    currentAction = FALLING;
                    animation.setFrames(spirits.get(FALLING));
                    animation.setDelay(100);
                    width = 30;
            }

        }

        // MOVEMENT IS UPWARD
        else if (dy < 0) {
            // JUMPING STATE
            if (currentAction != JUMPING) {
                currentAction = JUMPING;
                animation.setFrames(spirits.get(JUMPING));
                animation.setDelay(-1);
                width = 30;
            }
        }

        // WALKING STATE
        else if (left || right) {
            if (currentAction != WALKING) {
                currentAction = WALKING;
                animation.setFrames(spirits.get(WALKING));
                animation.setDelay(40);
                width = 30;
            }
        }

        // IDLE STATE
        else {
            if (currentAction != IDLE) {
                currentAction = IDLE;
                animation.setFrames(spirits.get(IDLE));
                animation.setDelay(400);
                width = 30;
            }
        }

        // CALL ANIMATION UPDATE METHOD
        animation.update();

        // SET DIRECTION
        if (currentAction != SCRATCHING && currentAction != FIREBALL) {
            if (right) facingRight = true;
            if (left) facingRight = false;
        }

    }


    // DRAW METHOD
    public void draw(Graphics2D g) {

        setMapPosition();

        // DRAW pLAYER
        if (flinching) {
            long elapsed = (System.nanoTime() - flinchTime)/ 1000000;
            if (elapsed/100 % 2 == 0) return;
        }

    }


}

















