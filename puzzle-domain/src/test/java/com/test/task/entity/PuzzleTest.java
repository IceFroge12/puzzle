package com.test.task.entity;

import com.test.task.data.PuzzleDataProvider;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class PuzzleTest {


    @Test
    public void shouldNotReturnTrue_whenBlankCanNotBeMovedUp() {
        var puzzles = PuzzleDataProvider.getPuzzleWithBlankInEdgeUpperPosition();
        assertThatElementCanNotBeMovedUp(puzzles);
    }

    @Test
    public void shouldReturnTrue_whenBlankCanBeMovedUp() {
        var puzzles = PuzzleDataProvider.getPuzzleWithBlankNotInEdgeUpperPosition();
        assertThatElementCanBeMovedUp(puzzles);
    }

    @Test
    public void shouldReturnFalse_whenBlankCanNotBeMovedLeft() {
        var puzzles = PuzzleDataProvider.getPuzzleWithBlankInEdgeLeftPosition();
        assertThatElementCanNotBeMovedLeft(puzzles);
    }

    @Test
    public void shouldReturnTrue_whenBlankCanBeMovedLeft() {
        var puzzles = PuzzleDataProvider.getPuzzleWithBlankNotInEdgeLeftPosition();
        assertThatElementCanBeMovedLeft(puzzles);
    }

    @Test
    public void shouldReturnFalse_whenBlankCanNotBeMovedRight() {
        var puzzles = PuzzleDataProvider.getPuzzleWithBlankInEdgeRightPosition();
        assertThatElementCanNotBeMovedRight(puzzles);
    }

    @Test
    public void shouldReturnTrue_whenBlankCanBeMovedRight() {
        var puzzles = PuzzleDataProvider.getPuzzleWithBlankNotInEdgeRightPosition();
        assertThatElementCanBeMovedRight(puzzles);
    }

    @Test
    public void shouldReturnFalse_whenBlankCanNotBeDownRight() {
        var puzzles = PuzzleDataProvider.getPuzzleWithBlankInEdgeDownPosition();
        assertThatElementCanNotBeMovedDown(puzzles);
    }

    @Test
    public void shouldReturnTrue_whenBlankCanBeDownRight() {
        var puzzles = PuzzleDataProvider.getPuzzleWithBlankNotInEdgeDownPosition();
        assertThatElementCanBeMovedDown(puzzles);
    }

    private void assertThatElementCanNotBeMovedUp(Set<Puzzle> puzzles) {
        puzzles.forEach(this::assertThatElementCanNotBeMovedUp);
    }

    private void assertThatElementCanNotBeMovedUp(Puzzle puzzle) {
        assertThat(puzzle.canMoveUp())
                .isFalse();
    }

    private void assertThatElementCanBeMovedUp(Set<Puzzle> puzzles) {
        puzzles.forEach(this::assertThatElementCanBeMovedUp);
    }

    private void assertThatElementCanBeMovedUp(Puzzle puzzle) {
        assertThat(puzzle.canMoveUp())
                .isTrue();
    }

    private void assertThatElementCanNotBeMovedLeft(Set<Puzzle> puzzles) {
        puzzles.forEach(this::assertThatElementCanNotBeMovedLeft);
    }

    private void assertThatElementCanNotBeMovedLeft(Puzzle puzzle) {
        assertThat(puzzle.canMoveLeft())
                .isFalse();
    }

    private void assertThatElementCanBeMovedLeft(Set<Puzzle> puzzles) {
        puzzles.forEach(this::assertThatElementCanBeMovedLeft);
    }

    private void assertThatElementCanBeMovedLeft(Puzzle puzzle) {
        assertThat(puzzle.canMoveLeft())
                .isTrue();
    }

    private void assertThatElementCanNotBeMovedRight(Set<Puzzle> puzzles) {
        puzzles.forEach(this::assertThatElementCanNotBeMovedRight);
    }

    private void assertThatElementCanNotBeMovedRight(Puzzle puzzle) {
        assertThat(puzzle.canMoveRight())
                .isFalse();
    }

    private void assertThatElementCanBeMovedRight(Set<Puzzle> puzzles) {
        puzzles.forEach(this::assertThatElementCanBeMovedRight);
    }

    private void assertThatElementCanBeMovedRight(Puzzle puzzle) {
        assertThat(puzzle.canMoveRight())
                .isTrue();
    }

    private void assertThatElementCanNotBeMovedDown(Set<Puzzle> puzzles) {
        puzzles.forEach(this::assertThatElementCanNotBeMovedDown);
    }

    private void assertThatElementCanNotBeMovedDown(Puzzle puzzle) {
        assertThat(puzzle.canMoveDown())
                .isFalse();
    }

    private void assertThatElementCanBeMovedDown(Set<Puzzle> puzzles) {
        puzzles.forEach(this::assertThatElementCanBeMovedDown);
    }

    private void assertThatElementCanBeMovedDown(Puzzle puzzle) {
        assertThat(puzzle.canMoveDown())
                .isTrue();
    }
}