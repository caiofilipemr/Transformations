package math;

import gc.Point;

import java.util.List;

public class MatrixOperation {
    public static double[][] multiply(double[][] matrix1, double[][] matrix2) {
        if (matrix1[0].length != matrix2.length)
            throw new IllegalArgumentException("Matrix can't be multiplied!");

        double[][] result = getMatrixOfZeros(matrix1.length, matrix2[0].length);
        for (int i = 0; i < matrix1.length; ++i) {
            for (int j = 0; j < matrix2[0].length; ++j) {
                for (int k = 0; k < matrix1[0].length; ++k) {
                    result[i][j] += (matrix1[i][k] * matrix2[k][j]);
                }
            }
        }
        return result;
    }

    private static double[][] getMatrixOfZeros(int rows, int columns) {
        return new double[rows][columns];
    }

    public static void multiplyColumnPoints(List<Point> points, double[][] matrix) {
        Point transformedPoint;
        for (Point point : points) {
            transformedPoint = Point.fromColumnArray(MatrixOperation.multiply(matrix, point.toColumnArray()));
            point.copy(transformedPoint);
        }
    }

    public static void multiplyRowPoints(List<Point> points, double[][] matrix) {
        Point transformedPoint;
        for (Point point : points) {
            transformedPoint = Point.fromRowArray(MatrixOperation.multiply(point.toRowArray(), matrix));
            point.copy(transformedPoint);
        }
    }
}
