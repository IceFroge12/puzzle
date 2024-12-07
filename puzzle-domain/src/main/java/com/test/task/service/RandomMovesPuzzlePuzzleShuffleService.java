package com.test.task.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import com.test.task.entity.Puzzle;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RandomMovesPuzzlePuzzleShuffleService implements PuzzleShuffleService {

    int shuffleStepCount;
    HashMap<Predicate<Puzzle>, Function<Puzzle, Puzzle>> moveMap;

    public RandomMovesPuzzlePuzzleShuffleService(final int shuffleStepCount) {
        Predicate<Puzzle> canMoveUp = Puzzle::canMoveUp;
        Predicate<Puzzle> canMoveRight = Puzzle::canMoveRight;
        Predicate<Puzzle> canMoveDown = Puzzle::canMoveDown;
        Predicate<Puzzle> canMoveLeft = Puzzle::canMoveLeft;

        Function<Puzzle, Puzzle> moveUp = Puzzle::moveUp;
        Function<Puzzle, Puzzle> moveRight = Puzzle::moveRight;
        Function<Puzzle, Puzzle> moveDown = Puzzle::moveDown;
        Function<Puzzle, Puzzle> moveLeft = Puzzle::moveLeft;

        moveMap = new HashMap<>();

        moveMap.put(canMoveUp, moveUp);
        moveMap.put(canMoveRight, moveRight);
        moveMap.put(canMoveDown, moveDown);
        moveMap.put(canMoveLeft, moveLeft);
        this.shuffleStepCount = shuffleStepCount;
    }

    public Puzzle shuffle(final Puzzle puzzle) {
        int step = 0;
        var shuffledPuzzle = puzzle;
        var random = new Random();
        while (step < shuffleStepCount) {
            var randomSelectedMove = getRandomSelectedMove(shuffledPuzzle, random);
            shuffledPuzzle = randomSelectedMove.apply(shuffledPuzzle);
            step++;
        }
        return shuffledPuzzle;
    }

    private Function<Puzzle, Puzzle> getRandomSelectedMove(final Puzzle puzzle, final Random random) {
        var availableActions = moveMap
                .entrySet()
                .stream()
                .filter(action -> action.getKey().test(puzzle))
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet());
        return availableActions
                .stream()
                .skip(random.nextInt(availableActions.size()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Can't find action"));
    }
}
