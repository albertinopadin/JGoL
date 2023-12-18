package org.tino;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class Grid {
    private int xCells;
    private int yCells;
    public int generation;

    public Cell[] cells;

    public Grid(int xCells, int yCells) {
        this.xCells = xCells;
        this.yCells = yCells;
        this.generation = 0;
        this.cells = (Cell[]) IntStream.range(0, xCells * yCells).mapToObj(i -> {
            int x = i % xCells;
            int y = i / xCells;
            return new Cell(false, new Point2D.Double(x, y), Color.BLUE);
        }).toArray();
        this.setNeighbors();
    }

    private void setNeighbors() {
        for (int x = 0; x < this.xCells; x++) {
            for (int y = 0; y < this.yCells; y++) {
                this.cells[x + y*this.xCells].neighbors = this.getNeighborsForCell(x, y);
            }
        }
    }

    // TODO: Implement
    private List<Cell> getNeighborsForCell(int x, int y) {
        return Collections.emptyList();
    }
}
