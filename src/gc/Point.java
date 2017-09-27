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
    
    public Point add(Point point) {
    	return new Point(x + point.x, y + point.y, z + point.z);
    }

    public Point multiply(double multiplier) {
        return new Point(x * multiplier, y * multiplier, z * multiplier);
    }

    public Point divide(double divider) {
        return new Point(x / divider, y / divider, z / divider);
    }
}
