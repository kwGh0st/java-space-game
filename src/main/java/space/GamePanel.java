package main.java.space;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements Runnable {
    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    static final int GAME_WIDTH = (int) (SCREEN_SIZE.getWidth() * 0.50);
    static final int GAME_HEIGHT = (int) (SCREEN_SIZE.getHeight() * 0.75);
    private final List<Ship> enemies;
    private final GamerShip gamer;
    private final Image background = new ImageIcon("src\\img\\background.png").getImage();

    public GamePanel() {
        enemies = new ArrayList<>();
        createEnemies();
        gamer = new GamerShip(new ImageIcon("src\\img\\ship.png").getImage());
        this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        this.addKeyListener(new AL(gamer));
        this.setFocusable(true);
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    public void createEnemies() {
        new EnemiesFactory(enemies);
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(background, 0, 0, getWidth(), getHeight(), this);

        enemies.forEach(e -> g2d.drawImage(e.getShipImage(), e.getX(), e.getY(), this));
        enemies.forEach(e -> e.getRockets().forEach(r -> r.draw(g)));

        g2d.drawImage(gamer.getShipImage(), gamer.getX(), GAME_HEIGHT - gamer.getShipImage().getHeight(this), this);
        gamer.getRockets().forEach(e -> e.draw(g));
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        while (true) {
            long now = System.nanoTime();

            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                enemies.forEach(Ship::move);
                enemies.forEach(e -> e.getRockets().forEach(Rocket::move));
                gamer.move();
                gamer.getRockets().forEach(Rocket::move);
                gamer.getRockets().removeIf(Rocket::isOutOfScreen);
                repaint();
                delta--;
            }

        }
    }

    private static class AL extends KeyAdapter {
        private final GamerShip ship;

        public AL(GamerShip ship) {
            this.ship = ship;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            ship.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            ship.keyReleased(e);
        }
    }
}
