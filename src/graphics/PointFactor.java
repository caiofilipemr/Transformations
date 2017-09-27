package graphics;

import gc.Point;

public class PointFactor {
    double xFactor;
    double yFactor;

    public PointFactor(double xFactor, double yFactor) {
        this.xFactor = xFactor;
        this.yFactor = yFactor;
    }

    public Point apllyFactor(int originX, int originY, double xyz, int pixelFactor) {
        return new Point(pixelFactor * xyz * xFactor + originX, pixelFactor * xyz * yFactor + originY, 0);
    }
}
