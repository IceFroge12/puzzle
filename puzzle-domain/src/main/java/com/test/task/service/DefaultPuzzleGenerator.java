package com.test.task.service;

import com.test.task.entity.Puzzle;
import com.test.task.entity.PuzzleNotValidException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class DefaultPuzzleGenerator implements PuzzleGenerator {

    SquareArrayPuzzleMatrixGenerator squareArrayPuzzleMatrixGenerator;
    PuzzleShuffleService puzzleShuffleService;
    PuzzleValidationService puzzleValidationService;

    @Override
    public Puzzle generate(int matrixSize) {
        var puzzleMatrix = squareArrayPuzzleMatrixGenerator.generatePuzzle(matrixSize);
        var puzzle = new Puzzle(puzzleMatrix);
        var shuffledPuzzle = puzzleShuffleService.shuffle(puzzle);
        validate(shuffledPuzzle);
        return shuffledPuzzle;
    }

    private void validate(Puzzle puzzle) {
        if (!puzzleValidationService.isValid(puzzle)) {
            log.error("Puzzle {} is not valid", puzzle);
            throw new PuzzleNotValidException("Puzzle is not valid");
        }
    }
}
