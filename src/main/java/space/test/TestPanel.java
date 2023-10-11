package main.java.space.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestPanel extends JPanel implements ActionListener {
    private final int PANEL_WIDTH = 500;
    private final int PANEL_HEIGHT = 500;
    private final Image enemy;
    private Image backgroundImage;
    private Timer timer;
    private int xVelocity = 1;
    private int yVelocity = 1;
    private int x = 0;
    private int y = 0;

    TestPanel() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.black);
        enemy = new ImageIcon("C:\\Users\\Karol\\Desktop\\Projekty na Github\\Space\\src\\enemy.png").getImage();
        timer = new Timer(5, this);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(enemy, x, y, null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        checkBorders();
        x += xVelocity;
        y += yVelocity;
        repaint();
    }

    private void checkBorders() {
        if (x >= (PANEL_WIDTH - enemy.getWidth(this)) || x < 0) {
            xVelocity = -xVelocity;
        }
        if (y >= (PANEL_HEIGHT - enemy.getHeight(this)) || y < 0) {
            yVelocity = -yVelocity;
        }
    }
}
