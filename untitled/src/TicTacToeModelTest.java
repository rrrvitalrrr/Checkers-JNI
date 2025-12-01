import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TicTacToeModelTest {
    public TicTacToeModel model;

    static {
        System.out.println("Loading native library...");
        System.loadLibrary("libCPlusPlus");
        System.out.println("Native library loaded successfully");
    }

    @BeforeEach
    public void setUp() {
        model = new TicTacToeModel();
        model.resetBoard();
    }

    //----------------------------------------------------------------------------------------------checking wins
    //-----------------------------------------------HORIZONTAL WINS
    // wins X
    @ParameterizedTest(name = "\"X\" Wins on row: {0}")
    @ValueSource(ints = {0, 1, 2, 3, 4})
    public void testHorizontalWinConditionForXAllRows(int row) {
        int rowForO = row == 0 ? 1 : 0;
        for (int column = 0; column < 5; column++) {
            model.makeMove(row, column);                 // X
            if (column == 4) break;
            model.makeMove(rowForO, column);             // O
        }
        assertAll(
                () -> assertTrue(model.checkWin()), // check if there was a won
                () -> assertFalse(model.isXTurn())  // false because i`ve changed the state of XTurn in my method makeMove
                                                    // after successfully moved
        );
    }

    // wins O
    @ParameterizedTest(name = "\"O\" Wins on row: {0}")
    @ValueSource(ints = {0, 1, 2, 3, 4})
    public void testHorizontalWinConditionForOAllRows(int row) {
        int rowForX = row == 0 ? 1 : 0;
        for (int column = 0; column < 5; column++) {
            if (row == 1 && column == 4){
                model.makeMove(2, column);  // X
            } else {
                model.makeMove(rowForX, column); // X
            }
            model.makeMove(row, column);
        }
        assertAll(
                () -> assertTrue(model.checkWin()), // check if there was a won
                () -> assertTrue(model.isXTurn())   // true because i`ve changed the state of XTurn in my method makeMove
                                                    // after successfully moved
        );
    }


    //-----------------------------------------------VERTICAL WINS
    // wins X
    @ParameterizedTest(name = "\"X\" Wins on column: {0}")
    @ValueSource(ints = {0, 1, 2, 3, 4})
    public void testVerticalWinConditionForXAllRows(int column) {
        int columnForO = column == 0 ? 1 : 0;
        for (int row = 0; row < 5 ; row++) {
            model.makeMove(row, column); // X
            if (row == 4) break;
            else model.makeMove(row, columnForO); // O
        }
        assertAll(
                () -> assertTrue(model.checkWin()), // check if there was a won
                () -> assertFalse(model.isXTurn())  // false because i`ve changed the state of XTurn in my method makeMove
                                                    // after successfully moved
        );
    }

    // wins O
    @ParameterizedTest(name = "\"O\" Wins on column: {0}")
    @ValueSource(ints = {0, 1, 2, 3, 4})
    public void testVerticalWinConditionForOAllRows(int column) {
        for (int row = 0; row < 5 ; row++) {
            if (row == 4 && column == 2){
                model.makeMove(row, 1);     // X
                model.makeMove(row, column);    // O
                break;
            }
            int columnForX = column == 0 ? 1 : 0;

            if (row == 4) {
                model.makeMove(row, 2); // X
            }
            else {
                model.makeMove(row, columnForX); // X
            }

            model.makeMove(row, column); // O
        }
        assertAll(
                () -> assertTrue(model.checkWin()), // check if there was a won
                () -> assertTrue(model.isXTurn())   // true - because ive changed the state of XTurn in my method makeMove
        );
    }


    //-----------------------------------------------DIAGONAL WINS
    // DIAGONAL WINS FOR X AND O
    @ParameterizedTest(name ="checking win for: {0} ")
    @ValueSource(chars = {'X', 'O'} )
    public void testDiagonalWinConditionForXAndO(char state) {
        if (state == 'X') {
            for (int row = 0; row < 5 ; row++) {
                if (row == 4) {
                    model.makeMove(row, row);                 // X
                    break;
                } else model.makeMove(row, row);              // X
                model.makeMove(row + 1, row);            // O
            }
            assertAll(
                    () -> assertTrue(model.checkWin()), // X should win with a main - diagonal line.
                    () -> assertFalse(model.isXTurn())  // false because i`ve changed the state of XTurn in my method makeMove
                                                        // after successfully moved
            );
        } else {  // O
            for (int row = 0; row < 5 ; row++) {
                if (row == 4) {
                    model.makeMove(2, 3);   // X
                } else model.makeMove(row + 1, row);    // X
                model.makeMove(row, row);   // O
            }
            assertAll(
                    () -> assertTrue(model.checkWin()), //O should win with a main - diagonal line
                    () -> assertTrue(model.isXTurn())   // true because i`ve changed the state of XTurn in my method makeMove
                                                        // after successfully moved
            );
        }
    }

    //-----------------------------------------------ANTI-DIAGONAL WINS
    // ANTI-DIAGONAL WINS FOR X AND O
    @ParameterizedTest(name = "checking win for: {0}")
    @ValueSource(chars = {'X', 'O'})
    public void testAntiDiagonalWinConditionForXAndO(char state){
        if (state == 'X') { // X
            for (int row = 4, col = 0 ; row >= 0 ; row--, col++) {
                if (row == 0){
                    model.makeMove(row, col);       // X
                    break;
                } else model.makeMove(row, col);    // X
                model.makeMove(row, col + 1);   // O
            }
            assertAll(
                    () -> assertTrue(model.checkWin()), // X should win with a main anti - diagonal line
                    () -> assertFalse(model.isXTurn())  // false because i`ve changed the state of XTurn in my method makeMove
                                                        // after successfully moved
            );
        } else {    // O
            for (int row = 4, col = 0 ; row >= 0 ; row--, col++) {
                if (row == 0) {
                    model.makeMove(2, 4); // X
                } else model.makeMove(row, col + 1);    // X
                model.makeMove(row, col);  // O
            }
            assertAll(
                    () -> assertTrue(model.checkWin()),  // X should win with a main anti - diagonal line
                    () -> assertTrue(model.isXTurn())    // true because i`ve changed the state of XTurn in my method makeMove
                                                         // after successfully moved
            );
        }
    }

    //-------------------------------------------------------------------------------------------other checks
    @Test
    public void testBoardInitialization() {
//         Verify the board is empty and ready for a new game.
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                assertEquals(' ', model.getBoardValue(row, col)); // board should be empty after initialization.
            }
        }
        assertTrue(model.isXTurn()); // X should go first after board initialization.
    }

    @Test
    public void testValidMove() {
        model.makeMove(0,0); // X placed at (0, 0)
        assertAll(
                () -> assertEquals('X', model.getBoardValue(0, 0)),  // cell (0, 0) should have X after valid move.
                () -> assertFalse(model.isXTurn())  // it should be O's turn after X makes a move.
        );
    }

    @Test
    public void testInvalidMove() {
        // Place X at (0, 0), then try to place O at the same position
        assertAll(
                () -> assertTrue(model.makeMove(0, 0)), // move at (0, 0) should be valid for X
                () -> assertFalse(model.makeMove(0, 0)) // move at (0, 0) should be invalid for O as it is already taken.
        );
    }

    @Test
    public void testTurnSwitchAfterMove() {
        // Verify that turn switches correctly after each move
        assertAll(
                () -> assertTrue (model.isXTurn()),                 // X should start first.
                () -> assertTrue (model.makeMove(0, 0)),   // First move should be valid.

                () -> assertFalse(model.isXTurn()),                 // After X moves, it should be O's turn.
                () -> assertTrue (model.makeMove(0, 1)),   // Second move should be valid for O.

                () -> assertTrue (model.isXTurn())                  // After O moves, it should be X's turn again.
        );
    }

    @Test
    public void testDrawCondition() {
        // Fill the board to force a draw (5x5) without any win condition
        int[][] moves  = {
                {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4},
                {1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 4},
                {2, 0}, {2, 2}, {2, 3}, {2, 1}, {2, 4},
                {3, 0}, {3, 1}, {3, 3}, {3, 2}, {3, 4},
                {4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4},
        };
        for (int[] move : moves) {
            int row = move[0];     // Get the row from the move array
            int column = move[1];  // Get the column from the move array
            assertTrue(model.makeMove(row, column)); // each move should be valid
        }
        assertAll (
                () -> assertFalse(model.checkWin()), // there should be no win condition - when the game is a draw.
                () -> assertTrue(model.checkDraw()) // should be draw, because all the cells are filled
        );
    }

    @Test
    public void testResetBoard() {
        // make some moves and then reset
        model.makeMove(0, 0); // X
        model.makeMove(0, 1); // O
        model.resetBoard();

        // check that all cells are empty and it is X turn again
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                assertEquals(' ', model.getBoardValue(row, col)); // board should be empty after reset.
            }
        }
        assertTrue(model.isXTurn()); //X should go first after resetting the board.
    }
}