package sudoku;

import java.util.*;

public class SudokuGenerator {
    
    private static final int SIZE = 9;
    private int[][] solutionBoard;
    private int[][] puzzleBoard;
    private int[][] playerBoard;

    public SudokuGenerator() {
        solutionBoard = new int[SIZE][SIZE];
        puzzleBoard = new int[SIZE][SIZE];
        playerBoard = new int[SIZE][SIZE];
    }

    /**
     * Getters
     */
    public int[][] getSolutionBoard() {
        return solutionBoard;
    }

    public int[][] getPuzzleBoard() {
        return puzzleBoard;
    }

    public int[][] getPlayerBoard() {
        return playerBoard;
    }


    // check if putting num in position [row, col] is legal
    public boolean isValid(int[][] board, int row, int col, int num) {
        // Check row
        for (int c = 0; c < SIZE; c++) {
            if (board[row][c] == num) {
                return false;
            }
        }

        // Check column
        for (int r = 0; r < SIZE; r++) {
            if (board[r][col] == num) {
                return false;
            }
        }

        // Check 3x3 box
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;

        for (int r = boxRow; r < boxRow + 3; r++) {
            for (int c = boxCol; c < boxCol + 3; c++) {
                if (board[r][c] == num) {
                    return false;
                }
            }
        }

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

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {

                if (solutionBoard[row][col] == 0) {

                    for (int num : shuffledNumbers()) {

                        if (isValid(solutionBoard, row, col, num)) {

                            solutionBoard[row][col] = num;

                            if (fillBoard()) {
                                return true;
                            }

                            solutionBoard[row][col] = 0;
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

    private void copyBoard(int[][] source, int[][] destination) {
        for(int row = 0; row < SIZE; row++) {
            System.arraycopy(source[row], 0, destination[row], 0, SIZE);
        }
    }

    public void createPuzzle(int cellsToRemove) {
        copyBoard(solutionBoard, puzzleBoard);

        Random random = new Random();

        int removed = 0;

        while (removed < cellsToRemove) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);

            if(puzzleBoard[row][col] != 0) {
                puzzleBoard[row][col] = 0;
                removed++;
            }
        }
    }

    public void printBoard(int[][] board) {
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