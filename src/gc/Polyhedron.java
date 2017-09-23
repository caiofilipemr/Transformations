package gc;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by caiofilipemr on 23/09/17.
 */
public class Polyhedron {
    List<Point> points = new LinkedList<Point>();
    List<Edge> edges = new LinkedList<Edge>();

    public void addPoint(Point point) {
        points.add(point);
    }

    public void generate() {

    }

    public void draw(Graphics g) {
        for (Point point : points) {
            g.fillOval((int) point.x, (int) point.y, 10, 10);
        }
    }
}
