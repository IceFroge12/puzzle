package com.test.task.out;

import com.test.task.entity.Puzzle;

import java.util.Optional;

public interface PuzzlePersistenceService {

    void save(Puzzle puzzle);

    Optional<Puzzle> get();

    void remove();
}
