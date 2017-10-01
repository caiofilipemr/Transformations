package graphics.canvas;

import gc.Edge;
import gc.Point;
import gc.Polyhedron;

import java.util.List;

import java.awt.*;

public class PlanPlotter {
    private static int PIXEL_FACTOR = 10;
    private static final int ORIGIN_X = 400;
    private static final int ORIGIN_Y = 400;
    private static final int POINT_SIZE = 11;
    private static Point selectedPoint = null;

    static void plotPlan(Graphics g, Polyhedron polyhedron, Polyhedron transitionPolyhedron, Polyhedron modifiedPolyhedron) {
        drawAxis(g, Color.red, new Point(30, 0, 0));
        drawAxis(g, Color.green, new Point(0, 30, 0));
        drawAxis(g, Color.blue, new Point(0, 0, 30));

        drawPolyhedron(g, polyhedron);
        if (transitionPolyhedron != null) {
            drawPolyhedron(g, transitionPolyhedron, Color.RED, Color.red);
        } else if (modifiedPolyhedron != null) {
            drawPolyhedron(g, modifiedPolyhedron, Color.RED, Color.red);
        }

        drawSelectedPointIfAny(g);
    }

    private static void drawAxis(Graphics g, Color c, Point endPoint) {
        Point endPoint2d = convert3dPointTo2d(endPoint);
        g.setColor(c);
        g.drawLine(ORIGIN_X, ORIGIN_Y, (int) endPoint2d.x, (int) endPoint2d.y);
    }

    private static void drawPolyhedron(Graphics g, Polyhedron polyhedron) {
        drawPolyhedron(g, polyhedron, Color.DARK_GRAY, Color.black);
    }

    private static void drawPolyhedron(Graphics g, Polyhedron polyhedron, Color pointColor, Color edgeColor) {
        g.setColor(pointColor);
        for (Point point : polyhedron.getPoints()) {
            point = convert3dPointTo2d(point);
            drawPoint(g, point);
        }

        g.setColor(edgeColor);
        for (Edge edge : polyhedron.getEdges()) {
            drawLine(g, convert3dPointTo2d(edge.getPointA()), convert3dPointTo2d(edge.getPointB()));
        }
    }

    private static void drawPoint(Graphics g, Point point) {
        g.fillOval(centerPoint(point.x), centerPoint(point.y), POINT_SIZE, POINT_SIZE);
    }

    private static void drawLine(Graphics g, Point source, Point destination) {
        g.drawLine((int) source.x, (int) source.y, (int) destination.x, (int) destination.y);
    }

    private static void drawSelectedPointIfAny(Graphics g) {
        if (selectedPoint != null) {
            Point selectedPoint2d = convert3dPointTo2d(selectedPoint);
            g.setColor(Color.MAGENTA);
            drawPoint(g, selectedPoint2d);
        }
    }

    private static int centerPoint(double xy) {
        return (int) (xy - POINT_SIZE / 2);
    }

    public static Point convert2dPointTo3d(Point point) {
        return PointConverter.convert2dPointTo3d(point, ORIGIN_X, ORIGIN_Y, PIXEL_FACTOR);
    }

    private static Point convert3dPointTo2d(Point point) {
        return PointConverter.convert3dPointTo2d(point, ORIGIN_X, ORIGIN_Y, PIXEL_FACTOR);
    }

    public static Point getClickCollisionIfAny(Point clickedPoint, List<Point> points) {
        for (Point point : points) {
            if (hasClickCollision(clickedPoint, convert3dPointTo2d(point))) {
                return point;
            }
        }
        return null;
    }

    private static boolean hasClickCollision(Point clickedPoint, Point point) {
        int halfPointSize = POINT_SIZE / 2;
        int xMax = (int) (point.x + halfPointSize), xMin = (int) (point.x - halfPointSize);
        int yMax = (int) (point.y + halfPointSize), yMin = (int) (point.y - halfPointSize);
        return (int) (clickedPoint.x) <= xMax
                && clickedPoint.x >= xMin
                && clickedPoint.y <= yMax
                && clickedPoint.y >= yMin;
    }

    public static void zoomIn() {
        PIXEL_FACTOR++;
    }

    public static void zoomOut() {
        PIXEL_FACTOR--;
    }

    public static void setSelectedPoint(Point selectedPoint) {
        PlanPlotter.selectedPoint = selectedPoint;
    }

    public static Point getSelectedPoint() {
        return selectedPoint;
    }
}
