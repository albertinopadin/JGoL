package org.tino;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;

public class GameFrame extends Frame {
    private static final int WIDTH = 960;
    private static final int HEIGHT = 620;

    private final int xCells = 10;
    private final int yCells = 10;

    private Grid grid;

    public GameFrame() {
        // Initialize Grid:
        this.grid = new Grid(xCells, yCells);

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
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        for (int x = 0; x < this.xCells; x++) {
            for (int y = 0; y < this.yCells; y++) {
                int idx = x + y*this.xCells;
                Shape shape = this.grid.cells[idx].shape;
                Color color = this.grid.cells[idx].color;
                g2d.setPaint(color);
                g2d.draw(shape);
                g2d.fill(shape);
                Rectangle2D bounds = shape.getBounds2D();
                g2d.setPaint(Color.RED);
                g2d.drawString(String.valueOf(idx),
                                (int) bounds.getCenterX(),
                                (int) bounds.getCenterY());
            }
        }
    }

    private void drawShape2D(Graphics2D g2d, Shape shape, Color color) {
        g2d.setPaint(color);
        g2d.draw(shape);
        g2d.fill(shape);
    }
}
