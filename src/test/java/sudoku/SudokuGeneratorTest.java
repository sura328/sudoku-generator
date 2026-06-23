package sudoku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuGeneratorTest {

    @Test
    public void testValid() {
        SudokuGenerator gen = new SudokuGenerator();

        gen.board[0][3] = 5;

        assertFalse(gen.isValid(0, 0, 5));

    }
}
