package com.test.task.service;

import com.test.task.entity.Puzzle;

public class DefaultPuzzleValidationService implements PuzzleValidationService{

    @Override
    public boolean isValid(Puzzle puzzle) {
        var puzzleMatrix = puzzle.getElements();

        var arr = new int[puzzle.size() * puzzle.size()];
        var k = 0;
        for (int i = 0; i < puzzle.size(); i++)
            for (int j = 0; j < puzzle.size(); j++)
                arr[k++] = puzzleMatrix[i][j];

        int invCount = getInvCount(arr, puzzleMatrix.length);


        if (puzzle.size() % 2 == 1)
            return invCount % 2 == 0;
        else
        {
            int pos = findXPosition(puzzleMatrix);
            if (pos % 2 == 1)
                return invCount % 2 == 0;
            else
                return invCount % 2 == 1;
        }
    }

    private int getInvCount(int[] arr, int size)
    {
        int inv_count = 0;
        for (int i = 0; i < size * size - 1; i++) {
            for (int j = i + 1; j < size * size; j++) {
                if (arr[j] != 0 && arr[i] != 0
                        && arr[i] > arr[j])
                    inv_count++;
            }
        }
        return inv_count;
    }

    private int findXPosition(int[][] puzzle)
    {
        for (int i = puzzle.length - 1; i >= 0; i--)
            for (int j = puzzle.length - 1; j >= 0; j--)
                if (puzzle[i][j] == 0)
                    return puzzle.length - i;
        return -1;
    }
}
