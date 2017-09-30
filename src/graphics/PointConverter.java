package graphics;

import gc.Point;
import math.PointRound;

public class PointConverter {
    private static PointFactor xFactor = new PointFactor(1, 0.2);
	private static PointFactor yFactor = new PointFactor(0, -1);
	private static PointFactor zFactor = new PointFactor(-0.8, 0.5);

    public static Point convert3dPointTo2d(Point point, int originX, int originY, int pixelFactor) {
    	Point newPoint = new Point(0, 0, 0);
		newPoint = newPoint.add(xFactor.applyFactor(point.x));
		newPoint = newPoint.add(yFactor.applyFactor(point.y));
		newPoint = newPoint.add(zFactor.applyFactor(point.z));
		newPoint = newPoint.multiply(pixelFactor);
    	return newPoint.add(new Point(originX, originY, 0));
    }

    public static Point convert2dPointTo3d(Point point, int originX, int originY, int pixelFactor) {
    	point = new Point(point.x - originX, point.y - originY, point.z);
    	point = point.divide(pixelFactor);
    	return PointRound.roundPoint(solveSystem(point));
	}

	private static Point solveSystem(Point point) {
    	double factor = xFactor.xFactor / xFactor.yFactor;
    	factor = factor * -1;
		double yy = (point.x + point.y * factor - point.z * (zFactor.xFactor + zFactor.yFactor * factor));
		yy = yy / (yFactor.xFactor + yFactor.yFactor * factor);
		double xx = (point.x - yy * yFactor.xFactor - point.z * zFactor.xFactor) / xFactor.xFactor;
		return new Point(xx, yy, point.z);
	}
}
