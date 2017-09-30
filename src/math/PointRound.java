package math;

import gc.Point;

import java.math.BigDecimal;

public class PointRound {
    public static Point roundPoint(Point point) {
        return new Point(round(point.x), round(point.y), round(point.z));
    }

    private static double round(double value) {
        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(1, BigDecimal.ROUND_HALF_EVEN);
        return bigDecimal.doubleValue();
    }
}
