package main.java.space;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

public class Ship implements ActionListener {
    private final Image shipImage;
    protected int xVelocity;
    private int yVelocity;
    private int rocketSpeed;
    protected int x;
    protected int y;
    private static final Random random = new Random();
    protected boolean isAlive;

    public Ship(Image shipImage) {
        this.shipImage = shipImage;
        this.isAlive = true;
    }

    public Ship(Image shipImage, int rocketSpeed) {
        this.shipImage = shipImage;
        this.isAlive = true;
        Timer timer = new Timer(2000, this);
        timer.start();
        this.rocketSpeed = rocketSpeed;
    }

    public void shootRandomly(List<Rocket> rockets) {
        int r = random.nextInt(10);
        if (r == 5 || r == 1 || r == 3 || r == 2) {
            rockets.add(new Rocket(getX() + (getShipImage().getWidth(null) / 2),
                    getY() + getShipImage().getHeight(null), 10, 10, rocketSpeed, Color.red));
        }
    }

    public void move() {
        checkBorders();
        x += xVelocity;
    }

    private void checkBorders() {
        if (x >= (GamePanel.GAME_WIDTH - this.getShipImage().getWidth(null)) || x < 0) {
            xVelocity = -xVelocity;
        }
    }

    public Image getShipImage() {
        return shipImage;
    }

    public void setX() {
        this.x = random.nextInt(GamePanel.GAME_WIDTH - shipImage.getWidth(null));
    }

    public void setXVelocity() {
        this.xVelocity = random.nextInt(5) + 1;
        this.yVelocity = random.nextInt(5) + 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isHit(Rocket rocket) {
        Rectangle shipBounds = new Rectangle(this.getX(), this.getY(), this.getShipImage().getWidth(null), this.getShipImage().getHeight(null));
        return (shipBounds.intersects(rocket) || rocket.intersects(shipBounds));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isAlive) shootRandomly(GamePanel.getRocketsOnTheScreen());

    }
}
