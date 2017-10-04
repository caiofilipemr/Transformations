package operation.projection;

import gc.Point;
import gc.Polyhedron;
import math.MatrixOperation;

public class PlanPerspectiveProjection implements Projection {

    private final Point viewPoint;

    public PlanPerspectiveProjection(Point viewPoint) {
        this.viewPoint = viewPoint;
    }

    @Override
    public Polyhedron project(Polyhedron polyhedron) {
        Polyhedron newPolyhedron = polyhedron.clone();
        Point firstPoint = newPolyhedron.getPoints().isEmpty() ? null : newPolyhedron.getPoints().get(0);
        MatrixOperation.multiplyRowPoints(newPolyhedron.getPoints(), getMatrix(firstPoint));
        return newPolyhedron;
    }

    public double[][] getMatrix(Point comparablePoint) {
        if (comparablePoint != null) {
            if (Math.signum(viewPoint.x) * Math.signum(comparablePoint.x) == -1) {
                return calculateXMatrix(viewPoint);
            } else if (Math.signum(viewPoint.y) * Math.signum(comparablePoint.y) == -1) {
                return calculateYMatrix(viewPoint);
            } else {
                return calculateZMatrix(viewPoint);
            }
        } else {
            if (viewPoint.x < 0) {
                return calculateXMatrix(viewPoint);
            } else if (viewPoint.y < 0) {
                return calculateYMatrix(viewPoint);
            } else {
                return calculateZMatrix(viewPoint);
            }
        }
    }

    private double[][] getTranslationMatrix(Point point) {
        return new double[][] {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {point.x, point.y, point.z, 1}};
    }

    private double[][] calculateXMatrix(Point point) {
        Point translationPoint = new Point(0, point.y, point.z);
        double[][] translationMatrixPositive = getTranslationMatrix(translationPoint);
        double[][] translationMatrixNegative = getTranslationMatrix(translationPoint.neg());
        double[][] xMatrix = new double[][] {{0, 0, 0, 1.0/Math.abs(point.x)}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        return MatrixOperation.multiply(translationMatrixNegative, MatrixOperation.multiply(xMatrix, translationMatrixPositive));
    }

    private double[][] calculateYMatrix(Point point) {
        Point translationPoint = new Point(point.x, 0, point.z);
        double[][] translationMatrixPositive = getTranslationMatrix(translationPoint);
        double[][] translationMatrixNegative = getTranslationMatrix(translationPoint.neg());
        double[][] yMatrix = new double[][] {{1, 0, 0, 0}, {0, 0, 0, 1.0/Math.abs(point.y)}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        return MatrixOperation.multiply(translationMatrixNegative, MatrixOperation.multiply(yMatrix, translationMatrixPositive));
    }

    private double[][] calculateZMatrix(Point point) {
        Point translationPoint = new Point(point.x, point.y, 0);
        double[][] translationMatrixPositive = getTranslationMatrix(translationPoint);
        double[][] translationMatrixNegative = getTranslationMatrix(translationPoint.neg());
        double[][] zMatrix = new double[][] {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 0, 1.0/(-1*point.z)}, {0, 0, 0, 1}};
        return MatrixOperation.multiply(translationMatrixNegative, MatrixOperation.multiply(zMatrix, translationMatrixPositive));
    }
}
