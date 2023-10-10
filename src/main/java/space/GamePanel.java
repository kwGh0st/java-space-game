package main.java.space;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private static final int GAME_WIDTH = 1000;
    private static final int GAME_HEIGHT = 555;
    private static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);

    public GamePanel() {
        this.setPreferredSize(SCREEN_SIZE);
        this.setBackground(Color.black);
    }
}
