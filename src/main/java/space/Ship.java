package main.java.space;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    protected List<Rocket> rockets;
    private Timer timer;

    public Ship(Image shipImage) {
        this.shipImage = shipImage;
        rockets = new ArrayList<>();
    }

    public Ship(Image shipImage, int rocketSpeed) {
        this.shipImage = shipImage;
        rockets = new ArrayList<>();
        this.timer = new Timer(3000, this);
        this.timer.start();
        this.rocketSpeed = rocketSpeed;
    }

    public List<Rocket> getRockets() {
        return rockets;
    }

    public void shootRandomly() {
        int r = random.nextInt(10);
        if (r == 5 || r == 1 || r == 3 || r == 2) {
            rockets.add(new Rocket(getX() + (getShipImage().getWidth(null) / 2),
                    getY() + getShipImage().getHeight(null), 10, 10, Math.negateExact(rocketSpeed), Color.red));
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

    @Override
    public void actionPerformed(ActionEvent e) {
        shootRandomly();
    }
}
