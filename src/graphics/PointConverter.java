package graphics;

import gc.Point;

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
    	point = new Point(point.x - originX, originY - point.y , point.z);
    	point = point.divide(pixelFactor);
		Point newPoint = point.add(xFactor.applyFactor(point.x));
		newPoint = newPoint.add(yFactor.applyFactor(point.y));
		return newPoint.add(zFactor.applyFactor(point.z));
	}
}
