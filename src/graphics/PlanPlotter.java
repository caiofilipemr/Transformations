package graphics;

import gc.Point;

import java.awt.*;

public class PlanPlotter {
    public static final int PIXEL_FACTOR = 10;
    public static final int ORIGIN_X = 400;
    public static final int ORIGIN_Y = 400;
    public static PointConverter pointConverter = new PointConverter(new PointFactor(1, 0.2),
            new PointFactor(0, -1), new PointFactor(-0.8, 0.5));

    public static void plotPlan(Graphics g) {
        Point x = pointConverter.convertPoint(new Point(30, 0, 0), ORIGIN_X, ORIGIN_Y, PIXEL_FACTOR);
        g.drawLine(ORIGIN_X, ORIGIN_Y, (int) x.x, (int) x.y);
        Point y = pointConverter.convertPoint(new Point(0, 30, 0), ORIGIN_X, ORIGIN_Y, PIXEL_FACTOR);
        g.drawLine(ORIGIN_X, ORIGIN_Y, (int) y.x, (int) y.y);
        Point z = pointConverter.convertPoint(new Point(0, 0, 30), ORIGIN_X, ORIGIN_Y, PIXEL_FACTOR);
        g.drawLine(ORIGIN_X, ORIGIN_Y, (int) z.x, (int) z.y);
    }
}
