package graphics;

import gc.Point;

/**
 * Created by caiofilipemr on 25/09/17.
 */
public class PointFactor {
    double xFactor;
    double yFactor;

    public PointFactor(double xFactor, double yFactor) {
        this.xFactor = xFactor;
        this.yFactor = yFactor;
    }

    public PointFactor apllyFactor(int originX, int originY, double xyz, int pixelFactor) {
        return new PointFactor(pixelFactor * xyz * xFactor + originX, pixelFactor * xyz * yFactor + originY);
    }
}
