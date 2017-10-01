package operation.transformation;

import gc.Point;
import gc.Polyhedron;
import math.MatrixOperation;

public class TranslationTransformation implements Transformation {
    private final double x;
    private final double y;
    private final double z;
    private final double[][] matrix;

    public TranslationTransformation(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.matrix = new double[][]{{1, 0, 0, x}, {0, 1, 0, y}, {0, 0, 1, z}, {0, 0, 0, 1}};
    }

    @Override
    public Polyhedron transform(Polyhedron polyhedron) {
        Polyhedron newPolyhedron = new Polyhedron();
        for (Point point : polyhedron.getPoints()) {
            newPolyhedron.addPoint(Point.fromColumnArray(MatrixOperation.multiply(matrix, point.toColumnArray())));
        }
        return newPolyhedron;
    }
}
