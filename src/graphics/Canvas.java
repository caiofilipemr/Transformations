package graphics;

import gc.Polyhedron;

import javax.swing.*;
import java.awt.*;

/**
 * Created by caiofilipemr on 23/09/17.
 */
public class Canvas extends JPanel {
    public static final int PIXEL_FACTOR = 10;
    private final Polyhedron polyhedron;

    public Canvas(Polyhedron polyhedron) {
        super();
        this.polyhedron = polyhedron;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.black);
        polyhedron.draw(g);
    }
}
