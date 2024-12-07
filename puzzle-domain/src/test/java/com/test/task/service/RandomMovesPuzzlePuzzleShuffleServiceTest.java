package com.test.task.service;

import com.test.task.entity.Puzzle;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomMovesPuzzlePuzzleShuffleServiceTest {

    final int SHUFFLE_STEP_COUNT = 28;

    RandomMovesPuzzlePuzzleShuffleService randomMovesPuzzlePuzzleShuffleService = new RandomMovesPuzzlePuzzleShuffleService(SHUFFLE_STEP_COUNT);


    @Test
    void shuffle() {
        var puzzle = getPuzzle();
        var shuffledPuzzle = randomMovesPuzzlePuzzleShuffleService.shuffle(puzzle);
        assertThat(shuffledPuzzle)
                .isNotEqualTo(puzzle);
    }

    private int[][] getMatrix() {
        return new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 0}
        };
    }

    private Puzzle getPuzzle() {
        return new Puzzle(getMatrix());
    }
}