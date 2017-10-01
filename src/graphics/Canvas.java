package graphics;

import gc.Polyhedron;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
    private final Polyhedron polyhedron;
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
        PlanPlotter.plotPlan(g, polyhedron, modifiedPolyhedron);

        g.setColor(Color.black);
    }

    public Polyhedron getModifiedPolyhedron() {
        return modifiedPolyhedron;
    }

    public void setModifiedPolyhedron(Polyhedron modifiedPolyhedron) {
        this.modifiedPolyhedron = modifiedPolyhedron;
    }
}
