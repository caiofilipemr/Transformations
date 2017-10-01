package math;

public class MatrixOperation {
    public static double[][] multiply(double[][] matrix1, double[][] matrix2) {
        if (matrix1[0].length != matrix2.length)
            throw new IllegalArgumentException("Matrix can't be multiplied!");

        double[][] result = getMatrixOfZeros(matrix1.length, matrix2[0].length);
        for (int i = 0; i < matrix1.length; ++i) {
            for (int j = 0; j < matrix1[0].length; ++j) {
                for (int k = 0; k < matrix1.length; ++k) {
                    result[i][j] += (matrix1[i][k] * matrix2[k][j]);
                }
            }
        }
        return result;
    }

    private static double[][] getMatrixOfZeros(int rows, int columns) {
        return new double[rows][columns];
    }
}
