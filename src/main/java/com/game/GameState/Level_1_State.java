package com.game.GameState;

import com.game.Main.GamePanel;
import com.game.TileMap.Background;
import com.game.TileMap.TileMap;

import java.awt.*;

public class Level_1_State extends GameState{

    // TILE MAP OBJECT
    private TileMap tileMap;

    // BACKGROUND OBJECT
    private Background bg;


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

        bg = new Background("/Backgrounds/grassbg1.gif", 0.1);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {

        // CLEAR SCREEN
        bg.draw(g);

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
