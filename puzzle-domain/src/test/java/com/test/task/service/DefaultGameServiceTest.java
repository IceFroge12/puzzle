package com.test.task.service;

import com.test.task.entity.Puzzle;
import com.test.task.entity.Move;
import com.test.task.out.PuzzlePersistenceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultGameServiceTest {

    final int MATRIX_SIZE = 4;
    @Mock
    PuzzleGenerator puzzleGenerator;
    @Mock
    PuzzlePersistenceService puzzlePersistenceService;
    DefaultGameService defaultGameService;

    @BeforeEach
    public void setUp() {
        defaultGameService = new DefaultGameService(MATRIX_SIZE, puzzleGenerator, puzzlePersistenceService);
    }

    @Test
    public void shouldStartGameAndSaveState() {
        var generatedPuzzle = getPuzzle();
        when(puzzleGenerator.generate(MATRIX_SIZE))
                .thenReturn(generatedPuzzle);

        var returnedPuzzle = defaultGameService.startGame();

        verify(puzzlePersistenceService).save(generatedPuzzle);
        assertThat(returnedPuzzle)
                .isEqualTo(generatedPuzzle);
    }

    @Test
    public void shouldGetCurrentGame() {
        var generatedPuzzle = getPuzzle();
        when(puzzlePersistenceService.get())
                .thenReturn(Optional.of(generatedPuzzle));

        assertThat(defaultGameService.getCurrentGame())
                .isPresent();
        assertThat(defaultGameService.getCurrentGame().get())
                .isEqualTo(generatedPuzzle);
    }

    @Test
    public void shouldNotGetCurrentGame_whenGameIsNotStarted() {
        when(puzzlePersistenceService.get())
                .thenReturn(Optional.empty());

        assertThat(defaultGameService.getCurrentGame())
                .isNotPresent();
    }

    @ParameterizedTest()
    @MethodSource("getMoveToPuzzleMap")
    public void shouldBeAbleToMakeMove(Map.Entry<Move, Puzzle> moveToPuzzle) {
        assertThat(defaultGameService.canMakeMove(moveToPuzzle.getValue(), moveToPuzzle.getKey()))
                .isTrue();
    }

    @Test
    public void shouldNotMakeMove_whenMoveIsUnknown() throws NoSuchFieldException, IllegalAccessException {
        clearMoveMapping();
        assertThat(defaultGameService.makeMove(getPuzzle(), Move.UP))
                .isNotPresent();
    }

    @Test
    public void shouldNotMakeMove_whenMoveIsNotAllowed() {
        assertThat(defaultGameService.makeMove(getPuzzle(), Move.RIGHT))
                .isNotPresent();
    }

    @Test
    public void shouldMakeMove() {
        var puzzleAfterMove = getPuzzleAfterMoveLeft();

        var actualPuzzleAfterMove = defaultGameService.makeMove(getPuzzle(), Move.LEFT);

        verify(puzzlePersistenceService).save(puzzleAfterMove);
        assertThat(actualPuzzleAfterMove)
                .isPresent();
        assertThat(actualPuzzleAfterMove.get())
                .isEqualTo(puzzleAfterMove);

    }

    private Puzzle getPuzzle() {
        return new Puzzle(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 0}
        });
    }

    private Puzzle getPuzzleAfterMoveLeft() {
        return new Puzzle(new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 0, 15}
        });
    }

    private static Set<Map.Entry<Move, Puzzle>> getMoveToPuzzleMap() {
        return Map.of(
                Move.UP, new Puzzle(new int[][]{
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 0}
                }),
                Move.RIGHT, new Puzzle(new int[][]{
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 0, 15}
                }),
                Move.DOWN, new Puzzle(new int[][]{
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 0, 12},
                        {13, 14, 11, 15}
                }),
                Move.LEFT, new Puzzle(new int[][]{
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 0, 15}
                })
        ).entrySet();
    }

    private void clearMoveMapping() throws NoSuchFieldException, IllegalAccessException {
        var moveMap = DefaultGameService.class.getDeclaredField("moveMap");
        moveMap.setAccessible(true);
        moveMap.set(this.defaultGameService, new HashMap<>());
    }
}