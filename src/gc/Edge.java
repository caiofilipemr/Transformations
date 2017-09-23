package gc;

/**
 * Created by caiofilipemr on 23/09/17.
 */
public class Edge {
    private final Point pointA, pointB;

    Edge(Point pointA, Point pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
    }

    public Point getPointA() {
        return pointA;
    }

    public Point getPointB() {
        return pointB;
    }
}
