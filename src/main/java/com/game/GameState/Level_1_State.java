package com.game.GameState;

import com.game.Entity.Player;
import com.game.Main.GamePanel;
import com.game.TileMap.Background;
import com.game.TileMap.TileMap;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Date;

public class Level_1_State extends GameState{

    // TILE MAP OBJECT
    private TileMap tileMap;

    // BACKGROUND OBJECT
    private Background bg;

    // PLAYER OBJECT
    private Player player;


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
        tileMap.setTween(1);

        bg = new Background("/Backgrounds/grassbg1.gif", 0.1);

        player = new Player(tileMap);
        player.setPosition(100, 100);
    }

    // CALL PLAYER UPDATE METHOD
    @Override
    public void update() {
        player.update();

        tileMap.setPosition(GamePanel.WIDTH/2 - player.getX(), GamePanel.HEIGHT/2 - player.getY());
    }

    @Override
    public void draw(Graphics2D g) {

        // CLEAR SCREEN
        bg.draw(g);

        // DRAW TILE MAP
        tileMap.draw(g);

        // DRAW PLAYER
        player.draw(g);
    }



    @Override
    public void keyPressed(int k) {

        if (k == KeyEvent.VK_LEFT) player.setLeft(true);
        if (k == KeyEvent.VK_RIGHT) player.setRight(true);
        if (k == KeyEvent.VK_UP) player.setUp(true);
        if (k == KeyEvent.VK_DOWN) player.setDown(true);
        if (k == KeyEvent.VK_W) player.setJumping(true);
        if (k == KeyEvent.VK_E) player.setGliding(true);
        if (k == KeyEvent.VK_R) player.setScratching();
        if (k == KeyEvent.VK_F) player.setFiring();
    }



    @Override
    public void keyReleased(int k) {

        if (k == KeyEvent.VK_LEFT) player.setLeft(false);
        if (k == KeyEvent.VK_RIGHT) player.setRight(false);
        if (k == KeyEvent.VK_UP) player.setUp(false);
        if (k == KeyEvent.VK_DOWN) player.setDown(false);
        if (k == KeyEvent.VK_W) player.setJumping(false);
        if (k == KeyEvent.VK_E) player.setGliding(false);
    }
}
