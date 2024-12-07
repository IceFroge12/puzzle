package com.test.task.repository;

import java.util.Optional;

public interface PuzzleRepository {

    void save(int[][] matrix);

    Optional<int[][]> get();

    void remove();
}
