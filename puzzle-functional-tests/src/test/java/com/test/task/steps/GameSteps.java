package com.test.task.steps;

import com.test.task.entity.Puzzle;
import com.test.task.in.GameService;
import com.test.task.entity.Move;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class GameSteps {

    GameService gameService;

    public Puzzle startGame() {
       return gameService.startGame();
    }

    public Optional<Puzzle> getGame() {
        return gameService.getCurrentGame();
    }

    public Optional<Puzzle> makeMove(Puzzle puzzle, Move move) {
        return gameService.makeMove(puzzle, move);
    }

    public Set<Move> getAvailableMoves(Puzzle puzzle) {
        return Arrays.stream(Move.values())
                .filter(move -> gameService.canMakeMove(puzzle, move))
                .collect(Collectors.toSet());
    }

    public Move getRandomMove(Set<Move> moves) {
        return moves
                .stream()
                .skip(ThreadLocalRandom.current()
                        .nextInt(moves.size()))
                .findAny()
                .orElse(null);
    }

    public void assertGameStarted() {
        assertThat(gameService.getCurrentGame())
                .isPresent();
    }

    public void assertMoveCompleted(Puzzle puzzle, Puzzle.Position oldBlankElementPosition) {
        var newBlankElementPosition = puzzle.getBlankElementPosition();
        assertThat(oldBlankElementPosition)
                .isNotEqualTo(newBlankElementPosition);
        assertThat(puzzle.getValueAtPosition(newBlankElementPosition))
                .isEqualTo(0);
        assertThat(puzzle.getValueAtPosition(oldBlankElementPosition))
                .isNotEqualTo(0);
    }
}
