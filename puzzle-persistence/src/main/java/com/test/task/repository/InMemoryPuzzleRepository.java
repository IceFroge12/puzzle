package com.test.task.repository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Optional;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class InMemoryPuzzleRepository implements PuzzleRepository{

    InMemoryStorage inMemoryStorage;

    @Override
    public void save(int[][] matrix) {
        inMemoryStorage.save(matrix);
    }

    @Override
    public Optional<int[][]> get() {
        return Optional.ofNullable(inMemoryStorage.get());
    }

    @Override
    public void remove() {
        inMemoryStorage.remove();
    }
}
