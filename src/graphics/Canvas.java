package graphics;

import gc.Polyhedron;
import gc.Point;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class Canvas extends JPanel {
    private static final double TRANSITION_FACTOR = 0.1;

    private Polyhedron polyhedron;
    private Polyhedron transitionPolyhedron = null;
    private Polyhedron modifiedPolyhedron = null;

    public Canvas(Polyhedron polyhedron) {
        super();
        this.polyhedron = polyhedron;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.black);
        PlanPlotter.plotPlan(g, polyhedron, transitionPolyhedron, modifiedPolyhedron);

        g.setColor(Color.black);
    }

    public void doTransition() {
        if (!inTransition()) return;

        boolean transitionOver = true;
        Iterator<Point> it = modifiedPolyhedron.getPoints().iterator();
        Point target;
        for (Point point : transitionPolyhedron.getPoints()) {
            target = it.next();
            if (point.x != target.x) {
                point.x = calculateNewCoord(point.x, target.x, TRANSITION_FACTOR);
                transitionOver = false;
            }
        }

        if (!transitionOver) return;
        it = modifiedPolyhedron.getPoints().iterator();
        for (Point point : transitionPolyhedron.getPoints()) {
            target = it.next();
            if (point.y != target.y) {
                point.y = calculateNewCoord(point.y, target.y, TRANSITION_FACTOR);
                transitionOver = false;
            }
        }

        if (!transitionOver) return;
        it = modifiedPolyhedron.getPoints().iterator();
        for (Point point : transitionPolyhedron.getPoints()) {
            target = it.next();
            if (point.z != target.z) {
                point.z = calculateNewCoord(point.z, target.z, TRANSITION_FACTOR);
                transitionOver = false;
            }
        }

        if (transitionOver)
            transitionPolyhedron = null;
    }

    public double calculateNewCoord(double coord, double targetCoord, double transitionFactor) {
        if (TRANSITION_FACTOR > Math.abs(coord - targetCoord))
            return targetCoord;

        return coord < targetCoord ? coord + TRANSITION_FACTOR : coord - TRANSITION_FACTOR;
    }

    public boolean inTransition() {
        return transitionPolyhedron != null;
    }

    public Polyhedron getPolyhedron() {
        return polyhedron;
    }

    public void setPolyhedron(Polyhedron polyhedron) {
        this.polyhedron = polyhedron;
    }

    public Polyhedron getModifiedPolyhedron() {
        return modifiedPolyhedron;
    }

    public void setModifiedPolyhedron(Polyhedron modifiedPolyhedron) {
        this.modifiedPolyhedron = modifiedPolyhedron;
        this.transitionPolyhedron = polyhedron.clone();
    }
}
