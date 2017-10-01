package operation.transformation;

import gc.Polyhedron;
import math.MatrixOperation;

public class TranslationTransformation implements Transformation {
    private final double[][] matrix;

    public TranslationTransformation(double x, double y, double z) {
        this.matrix = getTranslationMatrix(x, y, z);
    }

    @Override
    public Polyhedron transform(Polyhedron polyhedron) {
        Polyhedron newPolyhedron = polyhedron.clone();
        MatrixOperation.multiplyColumnPoints(newPolyhedron.getPoints(), matrix);
        return newPolyhedron;
    }

    public static double[][] getTranslationMatrix(double x, double y, double z) {
        return new double[][]{{1, 0, 0, x}, {0, 1, 0, y}, {0, 0, 1, z}, {0, 0, 0, 1}};
    }
}
