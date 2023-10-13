package main.java.space;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Ship {
    private final Image shipImage;
    protected int xVelocity;
    private int yVelocity;
    protected int x;
    protected int y;
    private JPanel panel;
    private static final Random random = new Random();

    public Ship(Image shipImage) {
        this.shipImage = shipImage;
    }

    public Ship(Image shipImage, JPanel panel) {
        this.shipImage = shipImage;
        this.panel = panel;
        this.xVelocity = random.nextInt(5) + 1;
        this.yVelocity = random.nextInt(5) + 1;
        this.x = random.nextInt(GamePanel.GAME_WIDTH - shipImage.getWidth(null));

    }

    public void move() {
        checkBorders();
        x += xVelocity;
    }

    private void checkBorders() {
        if (x >= (panel.getWidth() - this.getShipImage().getWidth(null)) || x < 0) {
            xVelocity = -xVelocity;
        }
    }

    public Image getShipImage() {
        return shipImage;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
