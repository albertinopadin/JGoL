package org.tino;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Grid {
    private final int xCells;
    private final int yCells;
    public int generation;

    public Cell[] cells;

    private final int cellSize = 40;
    private final int cellGap = 10;

    private final int offset = 100;

    public Grid(int xCells, int yCells) {
        this.xCells = xCells;
        this.yCells = yCells;
        this.generation = 0;
        this.cells = IntStream.range(0, xCells * yCells).mapToObj(i -> {
            int x = i % xCells;
            int y = i / xCells;
            double absPos = this.cellSize + this.cellGap;
            double xPoint = x*absPos;
            double yPoint = y*absPos;
            Rectangle2D shape = new Rectangle2D.Double(
                    xPoint + offset,
                    yPoint + offset,
                    this.cellSize,
                    this.cellSize);
            return new Cell(false, shape, Color.BLUE);
        }).toArray(Cell[]::new);
        this.setNeighbors();
    }

    private void setNeighbors() {
        for (int x = 0; x < this.xCells; x++) {
            for (int y = 0; y < this.yCells; y++) {
                this.cells[x + y*this.xCells].neighbors = this.getNeighborsForCell(x, y);
            }
        }
    }

    private List<Cell> getNeighborsForCell(int x, int y) {
        ArrayList<Cell> neighbors = new ArrayList<>();

        int leftX = x - 1;
        int rightX = x + 1;
        int topY = y - 1;
        int bottomY = y + 1;

        if (leftX > -1) {
            neighbors.add(this.cells[leftX + y*this.xCells]);
        }

        if (leftX > -1 && topY > -1) {
            neighbors.add(this.cells[leftX + topY*this.xCells]);
        }

        if (topY > -1) {
            neighbors.add(this.cells[x + topY*this.xCells]);
        }

        if (rightX < this.xCells && topY > -1) {
            neighbors.add(this.cells[rightX + topY*this.xCells]);
        }

        if (rightX < this.xCells) {
            neighbors.add(this.cells[rightX + y*this.xCells]);
        }

        if (rightX < this.xCells && bottomY < this.yCells) {
            neighbors.add(this.cells[rightX + bottomY*this.xCells]);
        }

        if (bottomY < this.yCells) {
            neighbors.add(this.cells[x + bottomY*this.xCells]);
        }

        if (leftX > -1 && bottomY < this.yCells) {
            neighbors.add(this.cells[leftX + bottomY*this.xCells]);
        }

        return neighbors;
    }

    public int update() {
        for (int x = 0; x < this.xCells; x++) {
            for (int y = 0; y < this.yCells; y++) {
                this.cells[x + y*this.xCells].prepareUpdate();
            }
        }

        for (int x = 0; x < this.xCells; x++) {
            for (int y = 0; y < this.yCells; y++) {
                this.cells[x + y*this.xCells].update();
            }
        }

        ++this.generation;
        return this.generation;
    }

    public void reset() {
        for (int x = 0; x < this.xCells; x++) {
            for (int y = 0; y < this.yCells; y++) {
                this.cells[x + y*this.xCells].makeDead();
            }
        }

        this.generation = 0;
    }

    public void makeAllLive() {
        for (int x = 0; x < this.xCells; x++) {
            for (int y = 0; y < this.yCells; y++) {
                this.cells[x + y*this.xCells].makeLive();
            }
        }
    }

    public void randomize(Double liveProbability) {
        this.reset();
        for (int x = 0; x < this.xCells; x++) {
            for (int y = 0; y < this.yCells; y++) {
                if (Math.random() <= liveProbability) {
                    this.cells[x + y * this.xCells].makeLive();
                }
            }
        }
    }
}
