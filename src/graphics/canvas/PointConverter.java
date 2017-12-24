package graphics.canvas;

import gc.Point;
import math.PointRound;

public class PointConverter {
	private static final Integer MOVE_FACTOR = 5;
	private static Integer angleX = 350;
	private static Integer angleZ = 225;

    private static PointFactor xFactor = new PointFactor(Math.cos(Math.toRadians(angleX)), -Math.sin(Math.toRadians(angleX)));
	private static PointFactor yFactor = new PointFactor(0, -1);
	private static PointFactor zFactor = new PointFactor(Math.cos(Math.toRadians(angleZ)), -Math.sin(Math.toRadians(angleZ)));

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
    	double factor = xFactor.xFactor / (xFactor.yFactor == 0 ? 1 : xFactor.yFactor);
    	factor = factor * -1;
		double yy = (point.x + point.y * factor - point.z * (zFactor.xFactor + zFactor.yFactor * factor));
		yy = yy / (yFactor.xFactor + yFactor.yFactor * factor);
		double xx = (point.x - yy * yFactor.xFactor - point.z * zFactor.xFactor) / xFactor.xFactor;
		return new Point(xx, yy, point.z);
	}

	public static void refreshFactors() {
    	xFactor.xFactor = Math.cos(Math.toRadians(angleX));
    	xFactor.yFactor = -Math.sin(Math.toRadians(angleX));
    	zFactor.xFactor = Math.cos(Math.toRadians(angleZ));
    	zFactor.yFactor = -Math.sin(Math.toRadians(angleZ));
	}

	public static void moveRight() {
		incrementAngleX();
		incrementAngleZ();
		refreshFactors();
	}

	private static void incrementAngleX() {
    	angleX = angleX + MOVE_FACTOR;
    	if (angleX > 360) angleX = angleX - 360;
	}

	private static void incrementAngleZ() {
		angleZ = angleZ + MOVE_FACTOR;
		if (angleZ > 360) angleZ = angleZ - 360;
	}

	public static void moveLeft() {
		decrementAngleX();
		decrementAngleZ();
		refreshFactors();
	}

	private static void decrementAngleX() {
		angleX = angleX - MOVE_FACTOR;
		if (angleX < 0) angleX = angleX + 360;
	}

	private static void decrementAngleZ() {
		angleZ = angleZ - MOVE_FACTOR;
		if (angleZ < 0) angleZ = angleZ + 360;
	}
}
