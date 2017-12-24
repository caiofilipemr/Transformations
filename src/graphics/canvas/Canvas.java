package graphics.canvas;

import gc.Polyhedron;
import graphics.canvas.transition.FarthestPointTransition;
import graphics.canvas.transition.Transition;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
    private static final double TRANSITION_FACTOR = 0.1;

    private Polyhedron polyhedron;
    private Polyhedron transitionPolyhedron = null;
    private Polyhedron modifiedPolyhedron = null;
    private Transition transition = new FarthestPointTransition();

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

        boolean transitionOver = transition.doTransition(transitionPolyhedron, modifiedPolyhedron, TRANSITION_FACTOR);
        if (transitionOver)
            transitionPolyhedron = null;
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

    public Transition getTransition() {
        return transition;
    }

    public void setTransition(Transition transition) {
        this.transition = transition;
    }

    public void useModifiedPolyhedronIfAny() {
        if (!inTransition()) {
            if (modifiedPolyhedron != null) {
                polyhedron = modifiedPolyhedron;
                modifiedPolyhedron = null;
            }
        }
    }
}
