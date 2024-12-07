package com.test.task.service;

import java.util.Arrays;

public class RenderService {

    public static void render(int[][] array) {
        for(int[] x: array)
            System.out.println(Arrays.toString(x));
    }

    public static void renderMovesCommand() {
        InputToMoveMapper
                .getMoveMap()
                .forEach((key, value) -> System.out.println("Type '" + key + "' for move " + value.toString()));
        System.out.println("Type 'end' to back in main menu");
    }

    public static void renderMenu() {
        System.out.println("Type 'start' to begin/restart play");
        System.out.println("Type 'exit' to end play");
        System.out.println("Type 'continue' if game already started");
    }
}
