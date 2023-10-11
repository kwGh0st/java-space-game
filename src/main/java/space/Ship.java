package main.java.space;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ship implements ActionListener {
    private final Image shipImage;
    private int xVelocity;
    private int yVelocity;
    private int x;
    private int y;

    public Ship(Image shipImage) {
        this.shipImage = shipImage;
        this.xVelocity = 1;
        this.yVelocity = 1;
    }

    public Image getShipImage() {
        return shipImage;
    }

    public int getX() {
        return x;
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
