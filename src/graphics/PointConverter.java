package graphics;

import gc.Point;

public class PointConverter {
    PointFactor xFactor;
	PointFactor yFactor;
	PointFactor zFactor;

    public PointConverter(PointFactor xFactor, PointFactor yFactor, PointFactor zFactor) {
        this.xFactor = xFactor;
        this.yFactor = yFactor;
		this.zFactor = zFactor;
    }

    public Point convertPoint(Point point, int originX, int originY, int pixelFactor) {
    	Point newPoint = new Point(0, 0, 0);
    	newPoint.add(xFactor.apllyFactor(originX, originY, point.x, pixelFactor));
    	newPoint.add(yFactor.apllyFactor(originX, originY, point.y, pixelFactor));
    	newPoint.add(zFactor.apllyFactor(originX, originY, point.z, pixelFactor));
    	return newPoint;
    }
}
