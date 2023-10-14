package main.java.space;

import java.awt.*;

public class Rocket extends Rectangle {
    private final int speed;

    public Rocket(int x, int y, int width, int height, int speed) {
        super(x, y, width, height);
        this.speed = speed;

    }

    public void move() {
        y -= speed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(x, y, width, height);
    }

    public boolean isOutOfScreen() {
        return y < 0;
    }
}
