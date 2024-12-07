package com.test.task.data;

import com.test.task.entity.Puzzle;

import java.util.Set;
import java.util.stream.Collectors;

public class PuzzleDataProvider {
    
    private static int DEFAULT_MATRIX_SIZE = 4;

    public static Set<Puzzle> getPuzzleWithBlankInEdgeUpperPosition() {
        var matrixCollections = GeneratorDataProvider.generateSetOfPuzzleMatrixWithBlankInEdgeUpperPosition(DEFAULT_MATRIX_SIZE);
        return matrixCollections
                .stream()
                .map(Puzzle::new)
                .collect(Collectors.toSet());
    }

    public static Set<Puzzle> getPuzzleWithBlankNotInEdgeUpperPosition() {
        var matrixCollections = GeneratorDataProvider.generateSetOfPuzzleMatrixWithBlankNotInEdgeUpperPosition(DEFAULT_MATRIX_SIZE);
        return matrixCollections
                .stream()
                .map(Puzzle::new)
                .collect(Collectors.toSet());
    }

    public static Set<Puzzle> getPuzzleWithBlankInEdgeLeftPosition() {
        var matrixCollections = GeneratorDataProvider.generateSetOfPuzzleMatrixWithBlankInEdgeLeftPosition(DEFAULT_MATRIX_SIZE);
        return matrixCollections
                .stream()
                .map(Puzzle::new)
                .collect(Collectors.toSet());
    }

    public static Set<Puzzle> getPuzzleWithBlankNotInEdgeLeftPosition() {
        var matrixCollections = GeneratorDataProvider.generateSetOfPuzzleMatrixWithBlankNotInEdgeLeftPosition(DEFAULT_MATRIX_SIZE);
        return matrixCollections
                .stream()
                .map(Puzzle::new)
                .collect(Collectors.toSet());
    }

    public static Set<Puzzle> getPuzzleWithBlankInEdgeRightPosition() {
        var matrixCollections = GeneratorDataProvider.generateSetOfPuzzleMatrixWithBlankInEdgeRightPosition(DEFAULT_MATRIX_SIZE);
        return matrixCollections
                .stream()
                .map(Puzzle::new)
                .collect(Collectors.toSet());
    }

    public static Set<Puzzle> getPuzzleWithBlankNotInEdgeRightPosition() {
        var matrixCollections = GeneratorDataProvider.generateSetOfPuzzleMatrixWithBlankNotInEdgeRightPosition(DEFAULT_MATRIX_SIZE);
        return matrixCollections
                .stream()
                .map(Puzzle::new)
                .collect(Collectors.toSet());
    }

    public static Set<Puzzle> getPuzzleWithBlankInEdgeDownPosition() {
        var matrixCollections = GeneratorDataProvider.generateSetOfPuzzleMatrixWithBlankInEdgeDownPosition(DEFAULT_MATRIX_SIZE);
        return matrixCollections
                .stream()
                .map(Puzzle::new)
                .collect(Collectors.toSet());
    }

    public static Set<Puzzle> getPuzzleWithBlankNotInEdgeDownPosition() {
        var matrixCollections = GeneratorDataProvider.generateSetOfPuzzleMatrixWithBlankNotInEdgeDownPosition(DEFAULT_MATRIX_SIZE);
        return matrixCollections
                .stream()
                .map(Puzzle::new)
                .collect(Collectors.toSet());
    }

    public static Set<Puzzle> getUnsolvablePuzzles() {
        var puzzle1 = new int[][]{
                new int[]{3, 9, 1, 15},
                new int[]{14, 11, 4, 6},
                new int[]{13, 0, 10, 12},
                new int[]{2, 7, 8, 5},
        };

        var puzzle2 = new int[][]{
                new int[]{5, 0, 1, 15},
                new int[]{10, 11, 4, 6},
                new int[]{3, 13, 14, 12},
                new int[]{7, 2, 8, 9},
        };
        return Set.of(new Puzzle(puzzle1), new Puzzle(puzzle2));
    }

    public static Set<Puzzle> getSolvablePuzzles() {
        var puzzle1 = new int[][]{
                new int[]{13, 2, 10, 3},
                new int[]{1, 12, 8, 4},
                new int[]{5, 0, 9, 6},
                new int[]{15, 14, 11, 7},
        };

        var puzzle2 = new int[][]{
                new int[]{6, 13, 7, 10},
                new int[]{8, 9, 11, 0},
                new int[]{15, 2, 12, 5},
                new int[]{14, 3, 1, 4},
        };
        return Set.of(new Puzzle(puzzle1), new Puzzle(puzzle2));
    }
}
