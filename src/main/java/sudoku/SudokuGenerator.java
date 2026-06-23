package sudoku;

import java.util.*;

public class SudokuGenerator {
    
    private static final int SIZE = 9;
    public int[][] board;

    public SudokuGenerator() {
        board = new int[SIZE][SIZE];
    }

    // check if putting num in position [row, col] is legal
    public boolean isValid(int row, int col, int num) {
        //check row
        for(int c = 0; c < SIZE; c++) {
            if(board[row][c] == num) {
                return false;
            }
        }

        //check column
        for(int r = 0; r < SIZE; r++) {
            if(board[r][col] == num) {
                return false;
            }
        }

        //check 3x3
        //use mod 3 to find beginning of nearest 3x3 box
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;

        for(int r = boxRow; r < boxRow + 3; r++) {
            for(int c = boxCol; c < boxCol + 3; c++) {
                if(board[r][c] == num) return false;
            }
        }

        //valid placement
        return true;
    }


    /**
     * shuffle numbers
     */
    private List<Integer> shuffledNumbers() {
        List<Integer> nums = new ArrayList<>();
        
        for(int i = 1; i <= SIZE; i++) {
            nums.add(i);
        }

        Collections.shuffle(nums);
        return nums;
    }


    /**
     * create a completed sudoku board by using recursion to find empty
     * cells and fill them to create a valid puzzle
     */
    public boolean fillBoard() {
        for(int row = 0; row < SIZE; row++) {

            for(int col = 0; col < SIZE; col++) {

                if(board[row][col] == 0) {

                
                    for(int num : shuffledNumbers()) {

                        if(isValid(row, col, num)) {
                            board[row][col] = num;

                            if(fillBoard()) {
                                return true;
                            }

                            board[row][col] = 0;
                        }
                    }

                    return false;
                }
            }
        }
        
        return true;
    }

    public void printBoard() {
        for(int row = 0; row < SIZE; row++) {

            if(row % 3 == 0 && row != 0) {
                System.out.println("---------------------");
            }

            for(int col = 0; col < SIZE; col++) {
                
                if(col % 3 == 0 && col != 0) {
                    System.out.print("| ");
                }

                System.out.print(board[row][col] + " ");
            }

            System.out.println();
        }
    }

}