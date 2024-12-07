package com.test.task.service;

import com.test.task.entity.Puzzle;
import com.test.task.in.GameService;
import com.test.task.entity.Move;
import com.test.task.out.PuzzlePersistenceService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DefaultGameService implements GameService {

    int defaultMatrixSize;
    PuzzleGenerator puzzleGenerator;
    PuzzlePersistenceService puzzlePersistenceService;
    Map<Move, Function<Puzzle, Puzzle>> moveMap;
    Map<Move, Function<Puzzle, Boolean>> canMoveMap;

    public DefaultGameService(int defaultMatrixSize, PuzzleGenerator puzzleGenerator, PuzzlePersistenceService puzzlePersistenceService) {
        Function<Puzzle, Puzzle> moveUp = Puzzle::moveUp;
        Function<Puzzle, Puzzle> moveRight = Puzzle::moveRight;
        Function<Puzzle, Puzzle> moveDown = Puzzle::moveDown;
        Function<Puzzle, Puzzle> moveLeft = Puzzle::moveLeft;

        moveMap = new HashMap<>();
        moveMap.put(Move.UP, moveUp);
        moveMap.put(Move.RIGHT, moveRight);
        moveMap.put(Move.DOWN, moveDown);
        moveMap.put(Move.LEFT, moveLeft);

        Function<Puzzle, Boolean> canMoveUp = Puzzle::canMoveUp;
        Function<Puzzle, Boolean> canMoveRight = Puzzle::canMoveRight;
        Function<Puzzle, Boolean> canMoveDown = Puzzle::canMoveDown;
        Function<Puzzle, Boolean> canMoveLeft = Puzzle::canMoveLeft;

        canMoveMap = new HashMap<>();
        canMoveMap.put(Move.UP, canMoveUp);
        canMoveMap.put(Move.RIGHT, canMoveRight);
        canMoveMap.put(Move.DOWN, canMoveDown);
        canMoveMap.put(Move.LEFT, canMoveLeft);

        this.defaultMatrixSize = defaultMatrixSize;
        this.puzzleGenerator = puzzleGenerator;
        this.puzzlePersistenceService = puzzlePersistenceService;
    }

    @Override
    public Puzzle startGame() {
        var puzzle = puzzleGenerator.generate(defaultMatrixSize);
        puzzlePersistenceService.save(puzzle);
        return puzzle;
    }

    @Override
    public Optional<Puzzle> getCurrentGame() {
        return puzzlePersistenceService.get();
    }

    @Override
    public boolean canMakeMove(Puzzle puzzle, Move move) {
        return canMoveMap
                .getOrDefault(move, ($) -> false)
                .apply(puzzle);
    }

    @Override
    public Optional<Puzzle> makeMove(Puzzle puzzle, Move move) {
        if (!moveMap.containsKey(move)) {
            return Optional.empty();
        }
        if (!canMakeMove(puzzle, move)) {
            return Optional.empty();
        }
        var afterMove = moveMap.get(move).apply(puzzle);
        puzzlePersistenceService.save(afterMove);
        return Optional.of(afterMove);

    }
}
