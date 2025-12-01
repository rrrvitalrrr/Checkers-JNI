#pragma once
#include <array>

struct TicTacToe {
    const static inline int length = 5;  // Tic-Tac-Toe 5x5
    static inline bool isXTurn = true;  // Track whose turn it is (X starts first)

    // 2D array to represent the board.
    // 0 = empty,
    // 1 = X,
    // 2 = O
    static inline std::array<std::array<int, length>, length> board = {{
                                                                               {0, 0, 0, 0, 0},
                                                                               {0, 0, 0, 0, 0},
                                                                               {0, 0, 0, 0, 0},
                                                                               {0, 0, 0, 0, 0},
                                                                               {0, 0, 0, 0, 0}
                                                                       }};
    //method
    static void setElement(int value, int row, int col);  // Set element at board[row][col]
    static int getElement(int row, int col);  // Get element at board[row][col]
    static const std::array<std::array<int, length>, length>& getBoard();
    static void resetBoard();
    static bool checkWin();
    static bool checkDraw();
};
