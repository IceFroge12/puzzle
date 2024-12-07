package com.test.task.repository;


import com.test.task.utils.ArrayUtils;

public class InMemoryStorage {

    int[][] matrix;

    void save(int[][] matrix) {
        this.matrix = ArrayUtils.deepCopy(matrix);
    }

    int[][] get() {
        return this.matrix;
    }

    void remove() {
        this.matrix = null;
    }
}
