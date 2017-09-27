package graphics;

import gc.Point;
import gc.Polyhedron;

import java.awt.*;

public class PlanPlotter {
    public static final int PIXEL_FACTOR = 10;
    public static final int ORIGIN_X = 400;
    public static final int ORIGIN_Y = 400;
    public static final int POINT_SIZE = 7;

    public static void plotPlan(Graphics g, Polyhedron polyhedron) {
        Point x = PointConverter.convert3dPointTo2d(new Point(30, 0, 0), ORIGIN_X, ORIGIN_Y, PIXEL_FACTOR);
        g.drawLine(ORIGIN_X, ORIGIN_Y, (int) x.x, (int) x.y);
        Point y = PointConverter.convert3dPointTo2d(new Point(0, 30, 0), ORIGIN_X, ORIGIN_Y, PIXEL_FACTOR);
        g.drawLine(ORIGIN_X, ORIGIN_Y, (int) y.x, (int) y.y);
        Point z = PointConverter.convert3dPointTo2d(new Point(0, 0, 30), ORIGIN_X, ORIGIN_Y, PIXEL_FACTOR);
        g.drawLine(ORIGIN_X, ORIGIN_Y, (int) z.x, (int) z.y);

        drawPolyhedron(g, polyhedron);
    }

    public static void drawPolyhedron(Graphics g, Polyhedron polyhedron) {
        for (Point point : polyhedron.getPoints()) {
            point = PointConverter.convert3dPointTo2d(point, ORIGIN_X, ORIGIN_Y, PIXEL_FACTOR);
            g.fillOval(centerPoint(point.x), centerPoint(point.y), POINT_SIZE, POINT_SIZE);
        }
    }

    private static int centerPoint(double xy) {
        return (int) (xy - POINT_SIZE / 2);
    }

    public static Point convert2dPointTo3d(Point point) {
        return PointConverter.convert2dPointTo3d(point, ORIGIN_X, ORIGIN_Y, PIXEL_FACTOR);
    }
}
