package com.test.task.service;

import com.test.task.entity.Move;
import lombok.Getter;

import java.util.Map;

public class InputToMoveMapper {

    @Getter
    private static final Map<String, Move> moveMap;
    private static final String IO_UP_INPUT = "up";
    private static final String IO_RIGHT_INPUT = "right";
    private static final String IO_DOWN_INPUT = "down";
    private static final String IO_LEFT_INPUT = "left";

    static {
        moveMap = Map.of(
                IO_UP_INPUT, Move.UP,
                IO_RIGHT_INPUT, Move.RIGHT,
                IO_DOWN_INPUT, Move.DOWN,
                IO_LEFT_INPUT, Move.LEFT
        );
    }

}
