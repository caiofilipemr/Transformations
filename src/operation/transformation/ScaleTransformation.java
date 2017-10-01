package operation.transformation;

import gc.Polyhedron;
import math.MatrixOperation;

public class ScaleTransformation implements Transformation {
    private final double sx;
    private final double sy;
    private final double sz;

    public ScaleTransformation(double sx, double sy, double sz) {
        this.sx = sx;
        this.sy = sy;
        this.sz = sz;
    }

    @Override
    public Polyhedron transform(Polyhedron polyhedron) {
        Polyhedron newPolyhedron = polyhedron.clone();
        MatrixOperation.multiplyColumnPoints(newPolyhedron.getPoints(), getScaleMatrix());
        return newPolyhedron;
    }

    public double[][] getScaleMatrix() {
        return new double[][]{{sx, 0, 0, 0}, {0, sy, 0, 0}, {0, 0, sz, 0}, {0, 0, 0, 1}};
    }
}
