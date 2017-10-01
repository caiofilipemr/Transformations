package operation.projection;

import gc.Polyhedron;
import math.MatrixOperation;

public class IsometricProjection implements Projection {

    @Override
    public Polyhedron project(Polyhedron polyhedron) {
        Polyhedron newPolyhedron = polyhedron.clone();
        MatrixOperation.multiplyRowPoints(newPolyhedron.getPoints(), getScaleMatrix());
        return newPolyhedron;
    }

    public double[][] getScaleMatrix() {
        return new double[][]{{0.707, 0.408, 0, 0}, {0, 0.816, 0, 0}, {0.707, -0.408, 0, 0}, {0, 0, 0, 1}};
    }
}
