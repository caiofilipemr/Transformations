package operation.transformation;

import gc.Point;
import gc.Polyhedron;
import math.MatrixOperation;

import java.util.List;

/**
 * Created by caiofilipemr on 30/09/17.
 */
public interface Transformation {
    Polyhedron transform(Polyhedron polyhedron);


    static void multiplyPoints(List<Point> points, double[][] matrix) {
        Point transformedPoint;
        for (Point point : points) {
            transformedPoint = Point.fromColumnArray(MatrixOperation.multiply(matrix, point.toColumnArray()));
            point.copy(transformedPoint);
        }
    }
}
