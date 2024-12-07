package com.test.task;

import com.test.task.in.GameService;
import com.test.task.service.InputToMoveMapper;
import com.test.task.service.RenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ComponentScan("com.test.task")
public class App implements CommandLineRunner
{
    @Autowired
    GameService gameService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) {
        var sc= new Scanner(System.in);
        while(true){
            RenderService.renderMenu();
            var input = sc.nextLine();
            switch (input) {
                case "start":
                    var puzzle = gameService.startGame();
                    RenderService.render(puzzle.getElements());
                    playGame(sc);
                    break;
                case "continue": {
                    var continuePuzzle = gameService.getCurrentGame();
                    if (continuePuzzle.isPresent()) {
                        RenderService.render(continuePuzzle.get().getElements());
                        playGame(sc);
                    } else {
                        System.out.println("Game not present");
                    }
                    break;
                }
                case "exit":
                    System.exit(0);
            }
        }
    }

    private void playGame(Scanner sc) {
        while (true) {
            RenderService.renderMovesCommand();
            String input = sc.nextLine();
            if (InputToMoveMapper.getMoveMap().containsKey(input.toLowerCase())) {
                var move = InputToMoveMapper.getMoveMap().get(input.toLowerCase());
                var possibleGame = gameService.getCurrentGame();
                if (possibleGame.isPresent()) {
                    var afterMove = gameService.makeMove(possibleGame.get(), move);
                    if (afterMove.isPresent()) {
                        RenderService.render(afterMove.get().getElements());
                    } else {
                        System.out.println("Move is not allowed");
                    }
                }
            } else if (input.equals("end")){
                break;
            }
        }
    }
}
