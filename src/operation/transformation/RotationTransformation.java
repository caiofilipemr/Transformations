package operation.transformation;

import gc.Point;
import gc.Polyhedron;
import math.MatrixOperation;

import java.util.List;

public class RotationTransformation implements Transformation {
    private final double x;
    private final double y;
    private final double z;

    public RotationTransformation(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public Polyhedron transform(Polyhedron polyhedron) {
        Polyhedron newPolyhedron = polyhedron.clone();
        rotateXIfGreaterThenZero(newPolyhedron);
        rotateYIfGreaterThenZero(newPolyhedron);
        rotateZIfGreaterThenZero(newPolyhedron);
        return newPolyhedron;
    }

    public void rotateXIfGreaterThenZero(Polyhedron newPolyhedron) {
        if (x > 0) {
            Point originClosest = getOriginClosestPoint(newPolyhedron.getPoints());
            Transformation.multiplyPoints(newPolyhedron.getPoints(), calculateXMatrix(x, originClosest));
        }
    }

    public void rotateYIfGreaterThenZero(Polyhedron newPolyhedron) {
        if (y > 0) {
            Point originClosest = getOriginClosestPoint(newPolyhedron.getPoints());
            Transformation.multiplyPoints(newPolyhedron.getPoints(), calculateYMatrix(y, originClosest));
        }
    }

    public void rotateZIfGreaterThenZero(Polyhedron newPolyhedron) {
        if (z > 0) {
            Point originClosest = getOriginClosestPoint(newPolyhedron.getPoints());
            Transformation.multiplyPoints(newPolyhedron.getPoints(), calculateZMatrix(z, originClosest));
        }
    }

    private Point getOriginClosestPoint(List<Point> points) {
        if (points.isEmpty())
            return null;

        Point closest = points.get(0);
        for (Point point : points) {
            closest = point.originDistance() < closest.originDistance() ? point : closest;
        }
        return closest;
    }

    private double[][] calculateXMatrix(double angle, Point originClosest) {
        angle = Math.toRadians(angle);
        double[][] translationMatrixNegative = TranslationTransformation.getTranslationMatrix(
                -originClosest.x, -originClosest.y, -originClosest.z);
        double[][] translationMatrixPositive = TranslationTransformation.getTranslationMatrix(
                originClosest.x, originClosest.y, originClosest.z);
        double[][] xMatrix = new double[][] {{1, 0, 0, 0}, {0, Math.cos(angle), -Math.sin(angle), 0},
                {0, Math.sin(angle), Math.cos(angle), 0}, {0, 0, 0, 1}};
        return MatrixOperation.multiply(translationMatrixPositive, MatrixOperation.multiply(xMatrix, translationMatrixNegative));
    }

    private double[][] calculateYMatrix(double angle, Point originClosest) {
        angle = Math.toRadians(angle);
        double[][] translationMatrixNegative = TranslationTransformation.getTranslationMatrix(
                -originClosest.x, -originClosest.y, -originClosest.z);
        double[][] translationMatrixPositive = TranslationTransformation.getTranslationMatrix(
                originClosest.x, originClosest.y, originClosest.z);
        double[][] yMatrix = new double[][] {{Math.cos(angle), 0, Math.sin(angle), 0}, {0, 1, 0, 0},
                {-Math.sin(angle), 0, Math.cos(angle), 0}, {0, 0, 0, 1}};
        return MatrixOperation.multiply(translationMatrixPositive, MatrixOperation.multiply(yMatrix, translationMatrixNegative));
    }

    private double[][] calculateZMatrix(double angle, Point originClosest) {
        angle = Math.toRadians(angle);
        double[][] translationMatrixNegative = TranslationTransformation.getTranslationMatrix(
                -originClosest.x, -originClosest.y, -originClosest.z);
        double[][] translationMatrixPositive = TranslationTransformation.getTranslationMatrix(
                originClosest.x, originClosest.y, originClosest.z);
        double[][] zMatrix = new double[][] {{Math.cos(angle), -Math.sin(angle), 0, 0},
                {Math.sin(angle), Math.cos(angle), 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        return MatrixOperation.multiply(translationMatrixPositive, MatrixOperation.multiply(zMatrix, translationMatrixNegative));
    }
}
