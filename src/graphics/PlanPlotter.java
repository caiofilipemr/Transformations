package graphics;

import gc.Point;

import java.awt.*;

/**
 * Created by caiofilipemr on 25/09/17.
 */
public class PlanPlotter {
    public static final int PIXEL_FACTOR = 10;
    public static final int ORIGIN_X = 400;
    public static final int ORIGIN_Y = 400;
    public static PointFactor xFactor = new PointFactor(1, 0.2);
    public static PointFactor yFactor = new PointFactor(0, -1);
    public static PointFactor zFactor = new PointFactor(-0.8, 0.5);

    public static void plotPlan(Graphics g) {
        Point xEnd = new Point(30, 0, 0);
        Point yEnd = new Point(0, 30, 0);
        Point zEnd = new Point(0, 0, 30);

        PointFactor x = xFactor.apllyFactor(ORIGIN_X, ORIGIN_Y, xEnd.x, PIXEL_FACTOR);
        g.drawLine(ORIGIN_X, ORIGIN_Y, (int) x.xFactor, (int) x.yFactor);
        PointFactor y = yFactor.apllyFactor(ORIGIN_X, ORIGIN_Y, yEnd.y, PIXEL_FACTOR);
        g.drawLine(ORIGIN_X, ORIGIN_Y, (int) y.xFactor, (int) y.yFactor);
        PointFactor z = zFactor.apllyFactor(ORIGIN_X, ORIGIN_Y, zEnd.z, PIXEL_FACTOR);
        g.drawLine(ORIGIN_X, ORIGIN_Y, (int) z.xFactor, (int) z.yFactor);
    }
}
