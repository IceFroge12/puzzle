package com.test.task;

import com.test.task.steps.GameSteps;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest extends BaseFunctionalTest {

    @Autowired
    GameSteps gameSteps;

    @Test
    public void shouldStartGame() {
        gameSteps.startGame();
        gameSteps.assertGameStarted();
    }

    @Test
    public void shouldMakeCorrectMove() {
        var puzzle = gameSteps.startGame();
        var availableMoves = gameSteps.getAvailableMoves(puzzle);
        var randomMove = gameSteps.getRandomMove(availableMoves);
        var oldBlankElementPosition = puzzle.getBlankElementPosition();
        var puzzleAfterMove = gameSteps.makeMove(puzzle, randomMove);
        assertThat(puzzleAfterMove)
                .isPresent();
        gameSteps.assertMoveCompleted(puzzleAfterMove.get(), oldBlankElementPosition);
    }

    @Test
    public void shouldNotGetCurrentState_whenGameIsNotStarted() {
        assertThat(gameSteps.getGame())
                .isEmpty();
    }
}
