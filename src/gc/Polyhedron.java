package gc;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Polyhedron {
    List<Point> points = new LinkedList<>();
    List<Edge> edges = new LinkedList<>();

    public void addPoint(Point point) {
        points.add(point);
    }

    public void addEdge(Point source, Point destination) {
        edges.add(new Edge(source, destination));
    }

    public boolean deletePoint(int selectedPointIndex) {
        if (selectedPointIndex >= 0 && selectedPointIndex < points.size()) {
            Point deletedPoint = points.remove(selectedPointIndex);
            deletePointEdgesIfAny(deletedPoint);
            return true;
        } else {
            return false;
        }
    }

    private void deletePointEdgesIfAny(Point deletedPoint) {
        Edge edge;
        Iterator<Edge> it = edges.iterator();
        while (it.hasNext()) {
            edge = it.next();
            if (edge.getPointA().equals(deletedPoint) || edge.getPointB().equals(deletedPoint)) {
                it.remove();
            }
        }
    }

    public Polyhedron clone() {
        Polyhedron newPolyhedron = new Polyhedron();
        for (Point point : points) {
            newPolyhedron.addPoint(point.clone());
        }
        for (Edge edge : edges) {
            newPolyhedron.addEdge(newPolyhedron.points.get(newPolyhedron.points.indexOf(edge.getPointA())),
                    newPolyhedron.points.get(newPolyhedron.points.indexOf(edge.getPointB())));
        }
        return newPolyhedron;
    }

    public List<Point> getPoints() {
        return points;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
