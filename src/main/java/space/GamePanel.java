package main.java.space;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GamePanel extends JPanel implements Runnable {
    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    static final int GAME_WIDTH = (int) (SCREEN_SIZE.getWidth() * 0.50);
    static final int GAME_HEIGHT = (int) (SCREEN_SIZE.getHeight() * 0.75);
    static final List<Ship> ships = new CopyOnWriteArrayList<>();
    static final List<Rocket> rocketsOnTheScreen = new CopyOnWriteArrayList<>();
    private final Image background = new ImageIcon("src\\img\\background.png").getImage();

    public GamePanel() {
        createEnemies();
        GamerShip gamer = new GamerShip(new ImageIcon("src\\img\\ship.png").getImage());
        ships.add(gamer);
        this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        this.addKeyListener(new AL(gamer));
        this.setFocusable(true);
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    public void createEnemies() {
        new EnemiesFactory(ships);
    }


    public static List<Rocket> getRocketsOnTheScreen() {
        return rocketsOnTheScreen;
    }
    private void destroyShip(Ship ship) {
        ship.isAlive = false;
        ships.remove(ship);
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(background, 0, 0, getWidth(), getHeight(), this);

        ships.forEach(e -> g2d.drawImage(e.getShipImage(),e.getX(), e.getY(), this));
        rocketsOnTheScreen.forEach(r -> r.draw(g));
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
                rocketsOnTheScreen.removeIf(Rocket::isOutOfScreen);
                ships.forEach(Ship::move);
                rocketsOnTheScreen.forEach(Rocket::move);
                checkHit();
                repaint();
                delta--;
            }
        }
    }

    private void checkHit() {
        for (Ship ship : ships) {
            for (Rocket rocket : rocketsOnTheScreen) {
                    if (ship.isHit(rocket)) {
                        destroyShip(ship);
                        rocketsOnTheScreen.remove(rocket);
                    }
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
