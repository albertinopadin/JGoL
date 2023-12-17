package org.tino;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.List;

public class Cell {
    public Boolean alive;

    // TODO: Should I use Point or Point2D here?
    public Point2D position;

    public List<Cell> neighbors;

    private Boolean currentState;

    private Boolean nextState;

    // TODO: Constructor

    private void setState(Boolean live) {
        this.currentState = live;
        this.alive = live;
        this.nextState = live;
    }

    public void makeLive() {
        this.setState(true);
        // TODO: Modify cell color / appearance
    }

    public void makeDead() {
        this.setState(false);
        // TODO: Modify cell color / appearance
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
