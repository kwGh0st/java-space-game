package main.java.space;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

public class EnemiesFactory implements ActionListener {
    private final List<Ship> enemiesList;

    public EnemiesFactory(List<Ship> enemiesList) {
        this.enemiesList = enemiesList;
        Timer timer = new Timer(3000, this);
        timer.start();
    }

    private void setEnemyParameters(Ship ship) {
        ship.setXVelocity();
        ship.setX();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Random random = new Random();
        Ship enemy = new Ship(new ImageIcon("src\\img\\enemy.png").getImage(), random.nextInt(6) + 4, 5);
        enemiesList.add(enemy);
        setEnemyParameters(enemy);
    }
}
