//JNI
#include "TicTacToeModel.h"

//c++
#include <algorithm>
#include "TicTacToe.h"

//TicTacToe.h methods implementation
void TicTacToe::setElement(int value, int row, int col) {
    board[row][col] = value;
}

int TicTacToe::getElement(int row, int col) {
    return board[row][col];
}

const std::array<std::array<int, TicTacToe::length>, TicTacToe::length> &TicTacToe::getBoard() {
    return board;
}

void TicTacToe::resetBoard() {
    for (auto &row: board) {
        row.fill(0);  // Reset the board to be empty
    }
    isXTurn = true;  // Reset turn to X
}

bool TicTacToe::checkWin() {
    // Check rows
    for (int i = 0; i < length; ++i) {
        if (board[i][0] != 0) {
            bool rowWin = true;
            for (int j = 1; j < length; ++j) {
                if (board[i][j] != board[i][0]) {
                    rowWin = false;
                    break;
                }
            }
            if (rowWin) return true;
        }
    }

    // Check columns
    for (int i = 0; i < length; ++i) {
        if (board[0][i] != 0) {
            bool colWin = true;
            for (int j = 1; j < length; ++j) {
                if (board[j][i] != board[0][i]) {
                    colWin = false;
                    break;
                }
            }
            if (colWin) return true;
        }
    }

    // Check main diagonal
    if (board[0][0] != 0) {
        bool mainDiagWin = true;
        for (int i = 1; i < length; ++i) {
            if (board[i][i] != board[0][0]) {
                mainDiagWin = false;
                break;
            }
        }
        if (mainDiagWin) return true;
    }

    // Check anti-diagonal
    if (board[0][length - 1] != 0) {
        bool antiDiagWin = true;
        for (int i = 1; i < length; ++i) {
            if (board[i][length - 1 - i] != board[0][length - 1]) {
                antiDiagWin = false;
                break;
            }
        }
        if (antiDiagWin) return true;
    }

    return false; // No win condition met
}

bool TicTacToe::checkDraw() {
    for (int i = 0; i < board.size(); ++i) {
        for (int j = 0; j < board.size(); ++j) {
            if (board[i][j] == 0) {
                return JNI_FALSE;
            }
        }
    }
    return JNI_TRUE;
}