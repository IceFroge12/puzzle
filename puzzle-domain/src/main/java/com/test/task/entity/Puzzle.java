package com.test.task.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import com.test.task.utils.ArrayUtils;

@Builder(toBuilder = true)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Data
public class Puzzle {

    int[][] elements;
    int blankPositionX;
    int blankPositionY;

    public Puzzle(final int[][] board) {
        int blankX = 0;
        int blankY = 0;
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                if (board.length != board[row].length) {
                    throw new IllegalArgumentException("Array should be square");
                }
                if (board[row][column] == 0) {
                    blankX = row;
                    blankY = column;
                }
            }
        }
        this.elements = board;
        this.blankPositionX = blankX;
        this.blankPositionY = blankY;
    }

    public boolean canMoveUp() {
        return blankPositionX - 1 >= 0;
    }

    public boolean canMoveDown() {
        return blankPositionX + 1 < elements.length;
    }

    public boolean canMoveRight() {
        return blankPositionY + 1 < elements.length;
    }

    public boolean canMoveLeft() {
        return blankPositionY - 1 >= 0;
    }

    public Puzzle moveUp() {
        var updatedElements = swapBlankWithElementOnPosition(blankPositionX - 1, blankPositionY);
        return this.toBuilder()
                .elements(updatedElements)
                .blankPositionX(blankPositionX - 1)
                .build();
    }

    public Puzzle moveRight() {
        var updatedElements = swapBlankWithElementOnPosition(blankPositionX, blankPositionY + 1);
        return this.toBuilder()
                .elements(updatedElements)
                .blankPositionY(blankPositionY + 1)
                .build();
    }

    public Puzzle moveDown() {
        var updatedElements = swapBlankWithElementOnPosition(blankPositionX + 1, blankPositionY);
        return this.toBuilder()
                .elements(updatedElements)
                .blankPositionX(blankPositionX + 1)
                .build();
    }

    public Puzzle moveLeft() {
        var updatedElements = swapBlankWithElementOnPosition(blankPositionX, blankPositionY - 1);
        return this.toBuilder()
                .elements(updatedElements)
                .blankPositionY(blankPositionY - 1)
                .build();
    }

    public int size() {
        return elements.length;
    }

    public Position getBlankElementPosition() {
        return new Position(blankPositionX, blankPositionY);
    }

    public int getValueAtPosition(Position position) {
        return elements[position.x][position.y];
    }

    private int[][] swapBlankWithElementOnPosition(int elementX, int elementY) {
        var newElements = ArrayUtils.deepCopy(elements);
        newElements[elementX][elementY] = 0;
        newElements[blankPositionX][blankPositionY] = elements[elementX][elementY];
        return newElements;
    }

    public record Position(int x, int y) {

    }
}
