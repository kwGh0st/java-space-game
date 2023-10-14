package main.java.space;

import java.awt.*;

public class Rocket extends Rectangle {
    private int speed;
    private Color color;

    public Rocket(int x, int y, int width, int height, int speed, Color color) {
        super(x, y, width, height);
        this.speed = speed;
        this.color = color;

    }

    public void move() {
        y -= speed;
    }


    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, width, height);
    }

    public boolean isOutOfScreen() {
        return y < 0 || y > GamePanel.GAME_HEIGHT;
    }
}
