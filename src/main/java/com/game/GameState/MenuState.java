package com.game.GameState;

import com.game.TileMap.Background;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState{

    // BACKGROUND VARIABLE
    private Background bg;

    // OPTIONS
    private int currentChoice = 0;
    private String[] options = { "Start", "Help", "Quit"};

    // TITLE FONT & COLOR
    private Color titleColor;
    private Font titleFont;

    private Font font;


    // SET GAME-STATE-MANAGER & BACKGROUND , COLOR, TITLE-FONT , FONT
    public MenuState(GameStateManager gsm) {
        this.gsm = gsm;

        try {
            // SET BACKGROUND OBJECT
            bg = new Background("/Backgrounds/menubg.gif", 1);
            bg.setVector(-0.1, 0);

            // SET COLOR
            titleColor = new Color(128, 0, 0);

            // SET OBJECT
            titleFont = new Font("Century Gothic", Font.PLAIN, 28);
            font = new Font("Arial", Font.PLAIN, 12);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {

    }


    // CALL BACKGROUND UPDATE METHOD
    @Override
    public void update() {
        bg.update();
    }


    // DRAW THE GRAPHICS OF MENU
    @Override
    public void draw(Graphics2D g) {

        // CALL BACKGROUND METHOD
        bg.draw(g);

        // DRAW TITLE
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("Dragon Tale", 80, 70);

        // DRAW MENU OPTIONS
        g.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.BLACK);
            } else {
                g.setColor(Color.RED);
            }
            g.drawString(options[i], 145, 140 + i * 15);
        }
    }


    // SELECT CHOICE AS THE KEY-VALUE
    // BY KEY PRESSED
    private void select() {
        switch (currentChoice) {

            // START
            case 0 :
                break;

            // HELP
            case 1 :
                break;

            // EXIT
            case 2 :
                System.exit(0);
        }
    }


    // TAKE KEY-VALUE AND WHAT ACTION PERFORM ACCORDING TO THAT VALE
    @Override
    public void keyPressed(int k) {

        // ENTERED KEY
        if (k == KeyEvent.VK_ENTER) {
            select();
        }

        // UP KEY
        if (k == KeyEvent.VK_UP) {
            currentChoice--;
            if (currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }

        // DOWN KEY
        if (k == KeyEvent.VK_DOWN) {
            currentChoice++;
            if (currentChoice == options.length) {
                currentChoice = 0;
            }
        }
    }

    @Override
    public void keyReleased(int k) {

    }
}
