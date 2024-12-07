package com.test.task.service;

import com.test.task.entity.Puzzle;
import com.test.task.entity.PuzzleNotValidException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class DefaultPuzzleGeneratorTest {

    final int MATRIX_SIZE = 4;

    @Mock
    SquareArrayPuzzleMatrixGenerator squareArrayPuzzleMatrixGenerator;
    @Mock
    PuzzleShuffleService puzzleShuffleService;
    @Mock
    PuzzleValidationService puzzleValidationService;

    @InjectMocks
    DefaultPuzzleGenerator defaultPuzzleGenerator;

    @Test
    void shouldGeneratePuzzle() {
        var puzzle = getPuzzle();
        var shuffledPuzzle = getSuffledPuzzle();

        when(squareArrayPuzzleMatrixGenerator.generatePuzzle(MATRIX_SIZE))
                .thenReturn(getMatrix());
        when(puzzleShuffleService.shuffle(puzzle))
                .thenReturn(shuffledPuzzle);
        when(puzzleValidationService.isValid(shuffledPuzzle))
                .thenReturn(true);


        var actualResult = defaultPuzzleGenerator.generate(MATRIX_SIZE);
        assertThat(actualResult)
                .isEqualTo(shuffledPuzzle);
    }

    @Test
    void shouldNotGeneratePuzzle_whenItIsNotSolvable() {
        var puzzle = getPuzzle();
        var shuffledPuzzle = getSuffledPuzzle();

        when(squareArrayPuzzleMatrixGenerator.generatePuzzle(MATRIX_SIZE))
                .thenReturn(getMatrix());
        when(puzzleShuffleService.shuffle(puzzle))
                .thenReturn(shuffledPuzzle);
        when(puzzleValidationService.isValid(shuffledPuzzle))
                .thenReturn(false);

        assertThrows(
                PuzzleNotValidException.class,
                () -> defaultPuzzleGenerator.generate(MATRIX_SIZE),
                "Expected generate() to throw, but it didn't"
        );
    }

    private int[][] getMatrix() {
        return new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 0}
        };
    }

    private int[][] getShuffledMatrix() {
        return new int[][]{
                {5, 0, 1, 15},
                {10, 11, 4, 6},
                {3, 13, 14, 12},
                {7, 2, 8, 9},
        };
    }

    private Puzzle getPuzzle() {
        return new Puzzle(getMatrix());
    }

    private Puzzle getSuffledPuzzle() {
        return new Puzzle(getShuffledMatrix());
    }
}