package com.test.task.in;

import com.test.task.entity.Move;
import com.test.task.entity.Puzzle;

import java.util.Optional;

public interface GameService {

    Puzzle startGame();

    Optional<Puzzle> getCurrentGame();

    boolean canMakeMove(Puzzle puzzle, Move move);

    Optional<Puzzle> makeMove(Puzzle puzzle, Move move);
}
