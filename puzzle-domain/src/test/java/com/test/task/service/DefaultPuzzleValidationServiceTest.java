package com.test.task.service;

import com.test.task.data.PuzzleDataProvider;
import com.test.task.entity.Puzzle;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultPuzzleValidationServiceTest {

    DefaultPuzzleValidationService defaultPuzzleValidationService = new DefaultPuzzleValidationService();

    @Test
    public void shouldReturnFalse_whenPuzzlesAreUnsolvable() {
        var unsolvablePuzzles = PuzzleDataProvider.getUnsolvablePuzzles();
        assertThatPuzzleIsUnsolvable(unsolvablePuzzles);
    }

    @Test
    public void shouldReturnTrue_whenPuzzlesAreSolvable() {
        var unsolvablePuzzles = PuzzleDataProvider.getSolvablePuzzles();
        assertThatPuzzleIsSolvable(unsolvablePuzzles);
    }

    private void assertThatPuzzleIsUnsolvable(Puzzle puzzle) {
        assertThat(defaultPuzzleValidationService.isValid(puzzle))
                .isFalse();
    }

    private void assertThatPuzzleIsUnsolvable(Set<Puzzle> puzzles) {
        puzzles.forEach(this::assertThatPuzzleIsUnsolvable);
    }

    private void assertThatPuzzleIsSolvable(Puzzle puzzle) {
        assertThat(defaultPuzzleValidationService.isValid(puzzle))
                .isTrue();
    }

    private void assertThatPuzzleIsSolvable(Set<Puzzle> puzzles) {
        puzzles.forEach(this::assertThatPuzzleIsSolvable);
    }
}