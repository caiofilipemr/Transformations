package graphics;

import gc.Point;

public class PointFactor {
    double xFactor;
    double yFactor;

    public PointFactor(double xFactor, double yFactor) {
        this.xFactor = xFactor;
        this.yFactor = yFactor;
    }

    public Point applyFactor(double xyz) {
        return new Point(xyz * xFactor, xyz * yFactor, 0);
    }
}
