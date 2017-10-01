package gc;

public class Point {
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
        return "(" + x + ", " + y + ", " + z + ")";
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
        return new Point(point[0][0], point[1][0], point[2][0]);
    }
    public static Point fromRowArray(double[][] point) {
        return new Point(point[0][0], point[0][1], point[0][2]);
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
}
