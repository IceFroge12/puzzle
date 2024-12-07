package com.test.task.steps;

import com.test.task.in.GameService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepsConfiguration {

    @Bean
    public GameSteps gameSteps(GameService gameService) {
        return new GameSteps(gameService);
    }
}
