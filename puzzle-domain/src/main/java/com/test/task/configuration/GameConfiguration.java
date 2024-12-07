package com.test.task.configuration;

import com.test.task.in.GameService;
import com.test.task.out.PuzzlePersistenceService;
import com.test.task.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfiguration {

    private final int SHUFFLE_STEP_COUNT = 110;
    private final int DEFAULT_MATRIX_SIZE = 4;

    @Bean
    public SquareArrayPuzzleMatrixGenerator defaultSquareArrayPuzzleMatrixGenerator() {
        return new DefaultSquareArrayPuzzleMatrixGenerator();
    }

    @Bean
    public PuzzleShuffleService dandomMovesPuzzlePuzzleShuffleService() {
        return new RandomMovesPuzzlePuzzleShuffleService(SHUFFLE_STEP_COUNT);
    }

    @Bean
    public PuzzleValidationService defaultPuzzleValidationService() {
        return new DefaultPuzzleValidationService();
    }

    @Bean
    public PuzzleGenerator defaultPuzzleGenerator(SquareArrayPuzzleMatrixGenerator defaultSquareArrayPuzzleMatrixGenerator,
                                                  PuzzleShuffleService dandomMovesPuzzlePuzzleShuffleService,
                                                  PuzzleValidationService defaultPuzzleValidationService) {
        return new DefaultPuzzleGenerator(defaultSquareArrayPuzzleMatrixGenerator, dandomMovesPuzzlePuzzleShuffleService, defaultPuzzleValidationService);
    }

    @Bean
    public GameService defaultGameService(PuzzleGenerator defaultPuzzleGenerator,
                                          PuzzlePersistenceService puzzlePersistenceService) {
        return new DefaultGameService(DEFAULT_MATRIX_SIZE, defaultPuzzleGenerator, puzzlePersistenceService);
    }
}
