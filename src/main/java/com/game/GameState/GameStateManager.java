package com.game.GameState;

import java.util.ArrayList;

public class GameStateManager {

    // STATE VALUES
    public static final int MENUSTATE = 0;
    public static final int LEVEL_1_STATE = 1;


    // STATES OF LEVEL IN GAME
    private ArrayList<GameState> gameStates;
    private int currentState;


    // DEFAULT CONSTRUCTOR -
    // SET STATE AT START OF THE GAME
    public GameStateManager() {
        gameStates = new ArrayList<GameState>();

        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
    }


    // CHANGE STATE TO NEW STATE
    public void setState(int state) {
        currentState = state;
        gameStates.get(currentState).init();
    }


    // UPDATE GAME-STATE
    public void update() {
        gameStates.get(currentState).update();
    }


    // DRAW UPDATE STATE
    public void draw(java.awt.Graphics2D g) {
        gameStates.get(currentState).draw(g);
    }


    // GET KEY-PRESSED VALUE FROM --
    // GAME-PANEL AND PASS TO STATE
    public void keyPressed(int k) {
        gameStates.get(currentState).keyPressed(k);
    }


    // GET KEY-RELEASED VALUE FROM --
    // GAME-PANEL AND PASS TO STATE
    public void keyReleased(int k) {
        gameStates.get(currentState).keyReleased(k);
    }

}
