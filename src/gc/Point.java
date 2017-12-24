package gc;

import java.text.DecimalFormat;

public class Point {
    private static final DecimalFormat formatter = new DecimalFormat("##.#");

    public double x, y, z;

    public Point() {
        this(0, 0, 0);
    }

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "(" + formatter.format(x) + ", " + formatter.format(y) + ", " + formatter.format(z) + ")";
    }

    @Override
    public boolean equals(Object o) {
        Point point = (Point) o;
        return x == point.x && y == point.y && z == point.z;
    }
    
    public Point add(Point point) {
    	return new Point(x + point.x, y + point.y, z + point.z);
    }

    public Point multiply(double multiplier) {
        return new Point(x * multiplier, y * multiplier, z * multiplier);
    }

    public Point divide(double divider) {
        return new Point(x / divider, y / divider, z / divider);
    }

    public double[][] toColumnArray() {
        return new double[][] {{x}, {y}, {z}, {1}};
    }

    public double[][] toRowArray() {
        return new double[][]{{x, y, z, 1}};
    }

    public static Point fromColumnArray(double[][] point) {
        Point p = new Point(point[0][0], point[1][0], point[2][0]);
        return point[3][0] == 1 ? p : p.divide(point[3][0]);
    }

    public static Point fromRowArray(double[][] point) {
        Point p = new Point(point[0][0], point[0][1], point[0][2]);
        return point[0][3] == 1 ? p : p.divide(point[0][3]);
    }

    public Point clone() {
        return new Point(x, y, z);
    }

    public void copy(Point transformedPoint) {
        x = transformedPoint.x;
        y = transformedPoint.y;
        z = transformedPoint.z;
    }

    public double originDistance() {
        return Math.abs(x) + Math.abs(y) + Math.abs(z);
    }

    public Point neg() {
        return new Point(-x, -y, -z);
    }
}
