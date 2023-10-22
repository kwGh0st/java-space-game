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
    protected int hits;

    public Ship(Image shipImage, int hits) {
        this.shipImage = shipImage;
        this.isAlive = true;
        this.hits = hits;
    }

    public Ship(Image shipImage, int rocketSpeed, int hits) {
        this.shipImage = shipImage;
        this.isAlive = true;
        this.rocketSpeed = rocketSpeed;
        this.hits = hits;
        Timer timer = new Timer(2000, this);
        timer.start();
    }

    public void shootRandomly(List<Rocket> rockets) {
        int r = random.nextInt(10);
        if (r == 5 || r == 1 || r == 3 || r == 2) {
            rockets.add(new Rocket(getX() + (getShipImage().getWidth(null) / 2),
                    getY() + getShipImage().getHeight(null), 10, 10, rocketSpeed, Color.red));
        }
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void move() {
        checkBorders();
        x += xVelocity;
        y += yVelocity;
    }

    private void checkBorders() {
        if (x >= (GamePanel.GAME_WIDTH - this.getShipImage().getWidth(null)) || x < 0) {
            xVelocity = -xVelocity;
        }
        if (y >= (GamePanel.GAME_HEIGHT / 3) - this.getShipImage().getHeight(null) || y < 0) {
            yVelocity = -yVelocity;
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
        if ((shipBounds.intersects(rocket) || rocket.intersects(shipBounds))) {
            hits--;
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isAlive) shootRandomly(GamePanel.getRocketsOnTheScreen());

    }
}
