package main.java.space.test;

import javax.swing.*;
import java.awt.*;

public class TestFrame extends JFrame {
    private TestPanel testPanel;

    public TestFrame() throws HeadlessException {
        testPanel = new TestPanel();
        this.add(testPanel);
        this.setTitle("Space");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
}
