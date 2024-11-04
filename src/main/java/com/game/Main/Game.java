package com.game.Main;

import javax.swing.*;

public class Game {

    public static void main(String[] args) {

        // game frame is set

        var window = new JFrame("Dragon Tale");
        window.setContentPane(new GamePanel());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
    }
}
