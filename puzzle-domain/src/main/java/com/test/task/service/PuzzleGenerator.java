package com.test.task.service;

import com.test.task.entity.Puzzle;

public interface PuzzleGenerator {

    Puzzle generate(int matrixSize);
}
