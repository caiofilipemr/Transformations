package gc;

import graphics.Canvas;
import graphics.PlanPlotter;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by caiofilipemr on 23/09/17.
 */
public class Polyhedron {
    private final int OVAL_SIZE = 7;
    List<Point> points = new LinkedList<Point>();
    List<Edge> edges = new LinkedList<Edge>();

    public void addPoint(Point point) {
        points.add(point);
    }

    public boolean deletePoint(int selectedPointIndex) {
        if (selectedPointIndex >= 0 && selectedPointIndex < points.size()) {
            points.remove(selectedPointIndex);
            return true;
        } else {
            return false;
        }
    }

    public void generate() {

    }

    public void draw(Graphics g) {
        for (Point point : points) {
            g.fillOval(calculateX(point), calculateY(point), OVAL_SIZE, OVAL_SIZE);
        }
    }

    private int calculateX(Point point) {
        int x = (int) (point.x * PlanPlotter.PIXEL_FACTOR - OVAL_SIZE / 2);
        return x;
    }

    private int calculateY(Point point) {
        return (int) (point.y * PlanPlotter.PIXEL_FACTOR - OVAL_SIZE / 2);
    }
}
