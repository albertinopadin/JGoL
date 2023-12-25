package org.tino;

import java.awt.*;
import java.util.List;

public class Cell {
    public Boolean alive;

    public Shape shape;

    public Color color;
    private Color liveColor;
    private Color deadColor;

    public List<Cell> neighbors;

    private Boolean currentState;

    private Boolean nextState;

    public Cell(Boolean alive, Shape shape, Color liveColor, Color deadColor) {
        this.shape = shape;
        this.liveColor = liveColor;
        this.deadColor = deadColor;
        if (alive) {
            this.makeLive();
        } else {
            this.makeDead();
        }
    }

    private void setState(Boolean live) {
        this.currentState = live;
        this.alive = live;
        this.nextState = live;
    }

    public void makeLive() {
        this.setState(true);
        // TODO: Modify cell color / appearance
        this.color = this.liveColor;
    }

    public void makeDead() {
        this.setState(false);
        // TODO: Modify cell color / appearance
        this.color = this.deadColor;
    }

    public Boolean needsUpdate() {
        return this.nextState != this.currentState;
    }

    public void prepareUpdate() {
        long liveNeighbors = this.neighbors.stream().filter(neighbor -> neighbor.alive).count();
        if (!(!this.currentState && liveNeighbors < 3)) {
            this.nextState = (this.currentState && liveNeighbors == 2) || (liveNeighbors == 3);
        }
    }

    public void update() {
        if (this.needsUpdate()) {
            if (this.nextState) {
                this.makeLive();
            } else {
                this.makeDead();
            }
        }
    }
}
