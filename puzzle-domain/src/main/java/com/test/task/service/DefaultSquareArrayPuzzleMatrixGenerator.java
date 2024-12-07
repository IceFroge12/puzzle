package com.test.task.service;

public class DefaultSquareArrayPuzzleMatrixGenerator implements SquareArrayPuzzleMatrixGenerator {

    @Override
    public int[][] generatePuzzle(int matrixSize) {
        var matrix = new int[matrixSize][matrixSize];
        for (int row = 0; row < matrixSize; row++) {
            for (int column = 0; column < matrixSize; column++) {
                matrix[row][column] = row * matrixSize + (column + 1);
            }
        }
        matrix[matrixSize - 1][matrixSize - 1] = 0;
        return matrix;
    }
}
