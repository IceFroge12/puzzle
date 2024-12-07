package com.test.task.data;

import com.test.task.utils.ArrayUtils;

import java.util.HashSet;
import java.util.Set;

public class GeneratorDataProvider {

    public static int[][] generateSquareMatrixWithAscendingValues(int matrixSize) {
        var matrix = new int[matrixSize][matrixSize];
        for (int row = 0; row < matrixSize; row++) {
            for (int column = 0; column < matrixSize; column++) {
                matrix[row][column] = row * matrixSize + (column + 1);
            }
        }
        return matrix;
    }

    public static Set<int[][]> generateSetOfPuzzleMatrixWithBlankInEdgeUpperPosition(int matrixSize) {
        var matrixCollection = new HashSet<int[][]>();
        var matrix = generateSquareMatrixWithAscendingValues(matrixSize);
        for (int i = 0; i < matrixSize; i++) {
            var puzzle = ArrayUtils.deepCopy(matrix);
            puzzle[0][i] = 0;
            matrixCollection.add(puzzle);
        }
        return matrixCollection;
    }

    public static Set<int[][]> generateSetOfPuzzleMatrixWithBlankNotInEdgeUpperPosition(int matrixSize) {
        var matrixCollection = new HashSet<int[][]>();
        var matrix = generateSquareMatrixWithAscendingValues(matrixSize);
        for (int i = 1; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                var puzzle = ArrayUtils.deepCopy(matrix);
                puzzle[i][j] = 0;
                matrixCollection.add(puzzle);
            }
        }
        return matrixCollection;
    }

    public static Set<int[][]> generateSetOfPuzzleMatrixWithBlankInEdgeLeftPosition(int matrixSize) {
        var matrixCollection = new HashSet<int[][]>();
        var matrix = generateSquareMatrixWithAscendingValues(matrixSize);
        for (int i = 0; i < matrixSize; i++) {
            var puzzle = ArrayUtils.deepCopy(matrix);
            puzzle[i][0] = 0;
            matrixCollection.add(puzzle);
        }
        return matrixCollection;
    }

    public static Set<int[][]> generateSetOfPuzzleMatrixWithBlankNotInEdgeLeftPosition(int matrixSize) {
        var matrixCollection = new HashSet<int[][]>();
        var matrix = generateSquareMatrixWithAscendingValues(matrixSize);
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 1; j < matrixSize; j++) {
                var puzzle = ArrayUtils.deepCopy(matrix);
                puzzle[i][j] = 0;
                matrixCollection.add(puzzle);
            }
        }
        return matrixCollection;
    }

    public static Set<int[][]> generateSetOfPuzzleMatrixWithBlankInEdgeRightPosition(int matrixSize) {
        var matrixCollection = new HashSet<int[][]>();
        var matrix = generateSquareMatrixWithAscendingValues(matrixSize);
        for (int i = 0; i < matrixSize; i++) {
            var puzzle = ArrayUtils.deepCopy(matrix);
            puzzle[i][matrixSize - 1] = 0;
            matrixCollection.add(puzzle);
        }
        return matrixCollection;
    }

    public static Set<int[][]> generateSetOfPuzzleMatrixWithBlankNotInEdgeRightPosition(int matrixSize) {
        var matrixCollection = new HashSet<int[][]>();
        var matrix = generateSquareMatrixWithAscendingValues(matrixSize);
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize - 1; j++) {
                var puzzle = ArrayUtils.deepCopy(matrix);
                puzzle[i][j] = 0;
                matrixCollection.add(puzzle);
            }
        }
        return matrixCollection;
    }

    public static Set<int[][]> generateSetOfPuzzleMatrixWithBlankInEdgeDownPosition(int matrixSize) {
        var matrixCollection = new HashSet<int[][]>();
        var matrix = generateSquareMatrixWithAscendingValues(matrixSize);
        for (int i = 0; i < matrixSize; i++) {
            var puzzle = ArrayUtils.deepCopy(matrix);
            puzzle[matrixSize - 1][i] = 0;
            matrixCollection.add(puzzle);
        }
        return matrixCollection;
    }

    public static Set<int[][]> generateSetOfPuzzleMatrixWithBlankNotInEdgeDownPosition(int matrixSize) {
        var matrixCollection = new HashSet<int[][]>();
        var matrix = generateSquareMatrixWithAscendingValues(matrixSize);
        for (int i = 0; i < matrixSize - 1; i++) {
            for (int j = 0; j < matrixSize; j++) {
                var puzzle = ArrayUtils.deepCopy(matrix);
                puzzle[i][j] = 0;
                matrixCollection.add(puzzle);
            }
        }
        return matrixCollection;
    }
}
