package org.tino;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends Frame {
    private static final int WIDTH = 960;
    private static final int HEIGHT = 620;

    private final int xCells = 200;
    private final int yCells = 200;

    private Grid grid;

    public GameFrame() {
        // Initialize Grid:
        this.grid = new Grid(xCells, yCells);
        this.grid.randomize(0.5);

        // Initialize UI:
        setName("JGoL");
        setTitle("JGoL: Conway's Game of Life in Java");
        setSize(WIDTH, HEIGHT);
        setBackground(Color.BLACK);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void update(Graphics g) {
        this.grid.update();
        // TODO: This really feels like a hack, but it's the only way I've found
        //       to force a repaint:
        this.paint(g);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for (int x = 0; x < this.xCells; x++) {
            for (int y = 0; y < this.yCells; y++) {
                int idx = x + y*this.xCells;
                Shape shape = this.grid.cells[idx].shape;
                Color color = this.grid.cells[idx].color;
                drawShape2D(g2d, shape, color);
            }
        }
    }

    private void drawShape2D(Graphics2D g2d, Shape shape, Color color) {
        g2d.setPaint(color);
        g2d.draw(shape);
        g2d.fill(shape);
    }
}
