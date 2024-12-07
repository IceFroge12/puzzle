package com.test.task.configuration;

import com.test.task.out.PuzzlePersistenceService;
import com.test.task.repository.InMemoryPuzzleRepository;
import com.test.task.repository.InMemoryStorage;
import com.test.task.repository.PuzzleRepository;
import com.test.task.service.DefaultPuzzlePersistenceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfiguration {

    @Bean
    public InMemoryStorage inMemoryStorage() {
        return new InMemoryStorage();
    }

    @Bean
    public PuzzleRepository inMemoryPuzzleRepository(InMemoryStorage inMemoryStorage) {
        return new InMemoryPuzzleRepository(inMemoryStorage);
    }

    @Bean
    PuzzlePersistenceService defaultPuzzlePersistenceService(PuzzleRepository inMemoryPuzzleRepository) {
        return new DefaultPuzzlePersistenceService(inMemoryPuzzleRepository);
    }
}
