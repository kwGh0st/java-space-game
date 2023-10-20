package main.java.space;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class GamerShip extends Ship {

    public GamerShip(Image shipImage) {
        super(shipImage);
        this.x = ((GamePanel.GAME_WIDTH / 2) - (this.getShipImage().getWidth(null) / 2));
        this.y = (GamePanel.GAME_HEIGHT - this.getShipImage().getHeight(null) - 5);
    }

    @Override
    public void move() {
        checkBorders();
        x += xVelocity;
    }

    public void shoot(List<Rocket> rockets) {
        rockets.add(new Rocket(x, GamePanel.GAME_HEIGHT - getShipImage().getHeight(null) - 10, 10, 10, -10, Color.blue));
        rockets.add(new Rocket(x + getShipImage().getWidth(null), GamePanel.GAME_HEIGHT - getShipImage().getHeight(null) - 10, 10, 10, -10, Color.blue));
    }

    public void keyPressed(KeyEvent e) {
        int speed = 5;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> {
                setXDirection(-speed);
                move();
            }
            case KeyEvent.VK_RIGHT ->{
                setXDirection(speed);
                move();
            }
            case KeyEvent.VK_SPACE -> shoot(GamePanel.getRocketsOnTheScreen());
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
