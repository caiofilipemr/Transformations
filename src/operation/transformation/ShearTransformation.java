package operation.transformation;

import gc.Polyhedron;
import math.MatrixOperation;

public class ShearTransformation implements Transformation {
    private final double xy;
    private final double xz;
    private final double yx;
    private final double yz;
    private final double zx;
    private final double zy;

    public ShearTransformation(double xy, double xz, double yx, double yz, double zx, double zy) {
        this.xy = xy;
        this.xz = xz;
        this.yx = yx;
        this.yz = yz;
        this.zx = zx;
        this.zy = zy;
    }

    @Override
    public Polyhedron transform(Polyhedron polyhedron) {
        Polyhedron newPolyhedron = polyhedron.clone();
        MatrixOperation.multiplyColumnPoints(newPolyhedron.getPoints(), getScaleMatrix());
        return newPolyhedron;
    }

    public double[][] getScaleMatrix() {
        return new double[][]{{1, xy, xz, 0}, {yx, 1, yz, 0}, {zx, zy, 1, 0}, {0, 0, 0, 1}};
    }
}
