package com.game.Main;

import com.game.GameState.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel
        implements Runnable, KeyListener {

    // Dimension Size
    public static final int WIDTH = 320;
    public static final int HEIGHT = 240;
    public static final int SCALE = 2;


    // Game Thread
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000/FPS;


    // Image
    private BufferedImage image;
    private Graphics2D g;


    // GAME STATE MANAGER
    private GameStateManager gsm;


    // Default Constructor - set panel for game window
    public GamePanel() {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }


    // add keyListener and thread safely
    // if the thread is null
    @Override
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }


    // initialize the image, graphics and gameStateManager
    private void init() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) g;
        running = true;
        gsm = new GameStateManager();
    }


    // When thread is call -
    // Start game loop and refresh the panel
    // Also make FPS working well
    @Override
    public void run() {
        init();

        long start;
        long elapsed;
        long wait;

        // Game Loop
        while (running) {
            // start time of loop
            start = System.nanoTime();

            //
            update();
            draw();
            drawToScreen();

            // time taken by update, draw, drawToScreen
            elapsed = System.nanoTime() - start;

            // remaining time for targetTime to complete
            wait = targetTime - elapsed / 1000000;

            // Make the Thread to sleep for remaining time
            // if problem occur then give error message
            try {
                Thread.sleep(wait);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void update() {
        gsm.update();
    }
    private void draw() {
        gsm.draw(g);
    }
    private void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        gsm.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gsm.keyReleased(e.getKeyCode());
    }
}
