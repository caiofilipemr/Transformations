package gc;

import graphics.PlanPlotter;

import java.util.LinkedList;
import java.util.List;


public class Polyhedron {
    List<Point> points = new LinkedList<>();
    List<Edge> edges = new LinkedList<>();

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

    public List<Point> getPoints() {
        return points;
    }
}
