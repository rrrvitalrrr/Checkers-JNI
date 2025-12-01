#include "TicTacToeModel.h"
#include "TicTacToe.h"
// -------------------------------------------------------------------------------------------JNI

// make a move
JNIEXPORT jboolean JNICALL Java_TicTacToeModel_makeMove(JNIEnv *env, jobject obj, jint row, jint col) {
    if (TicTacToe::getElement(row, col) != 0) {
        return JNI_FALSE;  // Invalid move
    }

    int playerValue = TicTacToe::isXTurn ? 1 : 2;  // 1 for X, 2 for O
    TicTacToe::setElement(playerValue, row, col);

    TicTacToe::isXTurn = !TicTacToe::isXTurn;  // Switch turns
    return JNI_TRUE;
}

// get board value
JNIEXPORT jchar JNICALL Java_TicTacToeModel_getBoardValue(JNIEnv *env, jobject obj, jint row, jint col) {
    int value = TicTacToe::getElement(row, col);
    if (value == 1) return 'X';
    else if (value == 2) return 'O';
    else return ' ';  // Return space if cell is empty
}


// check if win
JNIEXPORT jboolean JNICALL Java_TicTacToeModel_checkWin(JNIEnv *env, jobject obj) {
    return TicTacToe::checkWin() ? JNI_TRUE : JNI_FALSE;
}

// reset board
JNIEXPORT void JNICALL Java_TicTacToeModel_resetBoard(JNIEnv *env, jobject obj) {
    TicTacToe::resetBoard();
}

// check turn (X or O)
JNIEXPORT jboolean JNICALL Java_TicTacToeModel_isXTurn(JNIEnv *env, jobject obj) {
    return TicTacToe::isXTurn ? JNI_TRUE : JNI_FALSE;
}

// function checkDraw
JNIEXPORT jboolean JNICALL Java_TicTacToeModel_checkDraw(JNIEnv *env, jobject obj) {
    return TicTacToe::checkDraw();
}
// -------------------------------------------------------------------------------------------JNI