package com.game.Entity;

import java.awt.image.BufferedImage;

public class Animation {
    private BufferedImage[] frames;
    private int currentFrame;

    private long startTime;
    private long delay;

    private boolean playedOnce;


    // CONSTRUCTOR - SET PLAYED-ONCE FALSE
    public Animation() {
        playedOnce = false;
    }

    // SET FRAMES
    public void setFrames(BufferedImage[] frames) {
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
        playedOnce = false;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }


    public void update() {
        if (delay == -1) return;

        long elapsed = (System.nanoTime() - startTime) / 1000000;
        if (elapsed > delay) {
            currentFrame++;
            startTime = System.nanoTime();
        }
        if (currentFrame == frames.length) {
            currentFrame = 0;
            playedOnce = true;
        }
    }


    // GETTER METHOD
    public int getCurrentFrame() {
        return currentFrame;
    }

    public boolean hasPlayedOnce() {
        return playedOnce;
    }

    public BufferedImage getImage() {
        return frames[currentFrame];
    }
}
