package com.game.GameState;

import com.game.Main.GamePanel;
import com.game.TileMap.TileMap;

import java.awt.*;

public class Level_1_State extends GameState{

    // TILE MAP OBJECT
    private TileMap tileMap;


    // CONSTRUCTOR TO SET LEVEL 1
    public Level_1_State(GameStateManager gsm) {
        this.gsm = gsm;
        init();
    }


    // INITIALIZE THE TILE AND MAP FOR LEVEL 1
    @Override
    public void init() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tilesets/grasstileset.gif");
        tileMap.loadMap("/Maps/level1-1.map");
        tileMap.setPosition(0,0);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {

        // CLEAR SCREEN
        g.setColor(Color.WHITE);
        g.fillRect(0,0, GamePanel.WIDTH, GamePanel.HEIGHT);

        // DRAW TILE MAP
        tileMap.draw(g);
    }

    @Override
    public void keyPressed(int k) {

    }

    @Override
    public void keyReleased(int k) {

    }
}
