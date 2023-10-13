package main.java.space;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GamerShip extends Ship {
    private int speed = 5;

    public GamerShip(Image shipImage) {
        super(shipImage);
        this.setX((GamePanel.GAME_WIDTH / 2) - (this.getShipImage().getWidth(null) / 2));
    }

    @Override
    public void move() {
        checkBorders();
        x += xVelocity;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> {
                setXDirection(-speed);
                move();
            }
            case KeyEvent.VK_RIGHT ->{
                setXDirection(speed);
                move();
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT -> {
                setXDirection(0);
                move();
            }
        }
    }

    private void setXDirection(int xDirection) {
        xVelocity = xDirection;
    }

    private void checkBorders() {
        if (x >= GamePanel.GAME_WIDTH - this.getShipImage().getWidth(null)) x = GamePanel.GAME_WIDTH - this.getShipImage().getWidth(null);
        if (x <= 0) x = 0;
    }

}
