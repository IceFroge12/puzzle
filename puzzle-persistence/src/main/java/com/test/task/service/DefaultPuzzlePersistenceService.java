package com.test.task.service;

import com.test.task.repository.PuzzleRepository;
import com.test.task.entity.Puzzle;
import com.test.task.out.PuzzlePersistenceService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class DefaultPuzzlePersistenceService implements PuzzlePersistenceService {

    PuzzleRepository puzzleRepository;

    @Override
    public void save(Puzzle puzzle) {
        var matrix = puzzle.getElements();
        puzzleRepository.save(matrix);
    }

    @Override
    public Optional<Puzzle> get() {
        return puzzleRepository
                .get()
                .map(Puzzle::new);
    }

    @Override
    public void remove() {
        puzzleRepository.remove();
    }
}
