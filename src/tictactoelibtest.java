import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class tictactoelibtest {
    private tictactoelib lib;

    @BeforeEach
    void setup() {
        lib = new tictactoelib();
        lib.initializeBoard(); // Initialize before each test
    }


    // Example usage in a test
    @Test
    void testBoardInitialization() {
        lib.initializeBoard();
        char[][] board = lib.getBoard();
        assertNotNull(board, "Board should not be null after initialization");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertEquals(' ', board[i][j], "Board cell [" + i + "][" + j + "] should be empty");
            }
        }
    }

    @Test
    void testMakeMoveValid() {
        assertTrue(lib.makeMove(0, 0, 'X'), "Move should be allowed in empty cell");
        assertEquals('X', lib.getBoard()[0][0], "Cell should contain 'X' after move");
    }

    @Test
    void testMakeMoveInvalid() {
        lib.makeMove(0, 0, 'X');
        assertFalse(lib.makeMove(0, 0, 'O'), "Move should be rejected in occupied cell");
    }

    @Test
    void testRowWinCondition() {
        lib.makeMove(0, 0, 'X');
        lib.makeMove(0, 1, 'X');
        lib.makeMove(0, 2, 'X');
        lib.makeMove(0, 3, 'X');
        assertEquals('X', lib.checkWin(), "X should win with a row");
    }

    @Test
    void testColumnWinCondition() {
        lib.makeMove(0, 0, 'O');
        lib.makeMove(1, 0, 'O');
        lib.makeMove(2, 0, 'O');
        lib.makeMove(3, 0, 'O');
        assertEquals('O', lib.checkWin(), "O should win with a column");
    }

    @Test
    void testDiagonalWinConditionTopLeftToBottomRight() {
        lib.makeMove(0, 0, 'X');
        lib.makeMove(1, 1, 'X');
        lib.makeMove(2, 2, 'X');
        lib.makeMove(3, 3, 'X');
        assertEquals('X', lib.checkWin(), "X should win with a diagonal");
    }

    @Test
    void testDiagonalWinConditionTopRightToBottomLeft() {
        lib.makeMove(0, 4, 'O');
        lib.makeMove(1, 3, 'O');
        lib.makeMove(2, 2, 'O');
        lib.makeMove(3, 1, 'O');
        assertEquals('O', lib.checkWin(), "O should win with a diagonal");
    }

    @Test
    void testTieCondition() {

        char[][] moves = {
                {'X', 'X', 'X', 'O', 'X'},
                {'O', 'X', 'O', 'X', 'X'},
                {'X', 'O', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X', 'O'},
                {'X', 'O', 'X', 'X', 'X'}
        };

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertTrue(lib.makeMove(i, j, moves[i][j]), "Move should be valid for cell (" + i + ", " + j + ")");
            }
        }

        assertEquals('T', lib.checkWin(), "Game should end in a tie");
    }

    @Test
    void testResetBoard() {
        lib.makeMove(0, 0, 'X');
        lib.resetBoard();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                assertEquals(' ', lib.getBoard()[i][j], "Board should be empty after reset");
            }
        }
    }

    @Test
    void testAllRowWins() {
        for (int i = 0; i < 5; i++) {
            lib.initializeBoard();
            for (int j = 0; j < 4; j++) lib.makeMove(i, j, 'X');
            assertEquals('X', lib.checkWin(), "X should win with row " + i);
        }
    }

    @Test
    void testAllColumnWins() {
        for (int j = 0; j < 5; j++) {
            lib.initializeBoard();
            for (int i = 0; i < 4; i++) lib.makeMove(i, j, 'O');
            assertEquals('O', lib.checkWin(), "O should win with column " + j);
        }
    }

    @Test
    void testAllTopLeftToBottomRightDiagonals() {
        lib.makeMove(1, 1, 'X');
        lib.makeMove(2, 2, 'X');
        lib.makeMove(3, 3, 'X');
        lib.makeMove(4, 4, 'X');
        assertEquals('X', lib.checkWin(), "X should win on lower diagonal");
    }

    @Test
    void testAllTopRightToBottomLeftDiagonals() {
        lib.makeMove(1, 3, 'O');
        lib.makeMove(2, 2, 'O');
        lib.makeMove(3, 1, 'O');
        lib.makeMove(4, 0, 'O');
        assertEquals('O', lib.checkWin(), "O should win on lower anti-diagonal");
    }


    @Test
    void testEmptyCellAfterInitialization() {
        assertEquals(' ', lib.getBoard()[4][4], "Bottom-right should be empty after initialization");
    }

    @Test
    void testNoWinnerOnNewBoard() {
        assertEquals(' ', lib.checkWin(), "New board should have no winner");
    }

    @Test
    void testSingleMoveDoesNotWin() {
        lib.makeMove(0, 0, 'X');
        assertEquals(' ', lib.checkWin(), "Single move should not win");
    }

    @Test
    void testOnlyPartialRow() {
        lib.makeMove(2, 0, 'X');
        lib.makeMove(2, 1, 'X');
        assertEquals(' ', lib.checkWin(), "Partial row should not win");
    }

    @Test
    void testOnlyPartialColumn() {
        lib.makeMove(0, 3, 'O');
        lib.makeMove(1, 3, 'O');
        assertEquals(' ', lib.checkWin(), "Partial column should not win");
    }
    @Test
    void testFullRowButNoWin() {
        lib.makeMove(1, 0, 'X');
        lib.makeMove(1, 1, 'O');
        lib.makeMove(1, 2, 'X');
        lib.makeMove(1, 3, 'O');
        lib.makeMove(1, 4, 'X');
        assertEquals(' ', lib.checkWin(), "Full row with mixed symbols should not result in a win");
    }

    @Test
    void testFullColumnButNoWin() {
        lib.makeMove(0, 2, 'X');
        lib.makeMove(1, 2, 'O');
        lib.makeMove(2, 2, 'X');
        lib.makeMove(3, 2, 'O');
        lib.makeMove(4, 2, 'X');
        assertEquals(' ', lib.checkWin(), "Full column with mixed symbols should not result in a win");
    }

    @Test
    void testPartialDiagonalNoWin() {
        lib.makeMove(0, 0, 'X');
        lib.makeMove(1, 1, 'O');
        lib.makeMove(2, 2, 'X');
        lib.makeMove(3, 3, 'O');
        assertEquals(' ', lib.checkWin(), "Partial diagonal should not result in a win");
    }

    @Test
    void testMixedMovesNoWinner() {
        lib.makeMove(0, 0, 'X');
        lib.makeMove(1, 1, 'O');
        lib.makeMove(2, 2, 'X');
        lib.makeMove(3, 4, 'O');
        lib.makeMove(4, 3, 'X');
        assertEquals(' ', lib.checkWin(), "No win or tie expected with mixed moves");
    }

    @Test
    void testResetAfterWin() {
        lib.makeMove(0, 0, 'X');
        lib.makeMove(0, 1, 'X');
        lib.makeMove(0, 2, 'X');
        lib.makeMove(0, 3, 'X');
        assertEquals('X', lib.checkWin(), "X should win with a row");

        lib.resetBoard();
        assertEquals(' ', lib.checkWin(), "Board should be reset and have no winner");
        assertEquals(' ', lib.getBoard()[0][0], "Board cell [0][0] should be empty after reset");
    }

    @Test
    void testWinningAfterReset() {
        lib.makeMove(0, 0, 'X');
        lib.makeMove(0, 1, 'X');
        lib.makeMove(0, 2, 'X');
        lib.makeMove(0, 3, 'X');
        lib.resetBoard();

        lib.makeMove(1, 1, 'O');
        lib.makeMove(2, 2, 'O');
        lib.makeMove(3, 3, 'O');
        lib.makeMove(4, 4, 'O');
        assertEquals('O', lib.checkWin(), "O should win with a diagonal after reset");
    }

    @Test
    void testCornerWinCondition() {
        lib.makeMove(0, 0, 'X');
        lib.makeMove(1, 1, 'X');
        lib.makeMove(2, 2, 'X');
        lib.makeMove(3, 3, 'X');
        assertEquals('X', lib.checkWin(), "X should win with a top-left to bottom-right diagonal");
    }

    @Test
    void testEdgeCaseSingleColumn() {
        lib.makeMove(0, 4, 'O');
        lib.makeMove(1, 4, 'O');
        lib.makeMove(2, 4, 'O');
        lib.makeMove(3, 4, 'O');
        assertEquals('O', lib.checkWin(), "O should win with a single edge column");
    }

    @Test
    void testEdgeCaseSingleRow() {
        lib.makeMove(4, 0, 'X');
        lib.makeMove(4, 1, 'X');
        lib.makeMove(4, 2, 'X');
        lib.makeMove(4, 3, 'X');
        assertEquals('X', lib.checkWin(), "X should win with a single edge row");
    }

    @Test
    void testInvalidMoveCoordinates() {
        assertFalse(lib.makeMove(5, 5, 'X'), "Move should be rejected with out-of-bounds coordinates");
    }

    @Test
    void testOverwritingMove() {
        lib.makeMove(2, 2, 'X');
        assertFalse(lib.makeMove(2, 2, 'O'), "Move should be rejected for an occupied cell");
    }

    @Test
    void testSingleMoveAndReset() {
        lib.makeMove(0, 0, 'X');
        lib.resetBoard();
        assertEquals(' ', lib.getBoard()[0][0], "Board should be empty after reset following a single move");
    }

}

