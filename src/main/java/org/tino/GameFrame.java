package org.tino;

import java.awt.*;

public class GameFrame extends Frame {
    private static int WIDTH = 960;
    private static int HEIGHT = 620;

    private Grid grid;
    private int xCells = 100;
    private int yCells = 100;

    public GameFrame() {
        // Initialize Grid:
        this.grid = new Grid(xCells, yCells);

        // Initialize UI:
        setName("JGoL");
        setTitle("JGoL: Conway's Game of Life in Java");
        setSize(WIDTH, HEIGHT);
        setBackground(Color.BLACK);
        setVisible(true);
    }
}
