package com.test.task.utils;

import java.util.Arrays;

public class ArrayUtils {

    public static int[][] deepCopy(int[][] input) {
        return Arrays.stream(input)
                .map(int[]::clone)
                .toArray($ -> input.clone());
    }
}
