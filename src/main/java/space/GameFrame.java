package main.java.space;

import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame() {
        GamePanel panel = new GamePanel();
        this.add(panel);
        this.setResizable(false);
        this.setTitle("Space Game");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
