#include <iostream>
#include <vector>
using namespace std;

class TicTacToe {
private:
    static const int SIZE = 3;
    static const char EMPTY = ' ';
    static const char HUMAN = 'X';
    static const char COMPUTER = 'O';

    vector<vector<char>> board;
    bool isHumanTurn;
    bool isGameStarted;

public:
    TicTacToe() {
        board = vector<vector<char>>(SIZE, vector<char>(SIZE, ' '));
        isHumanTurn = true;
        isGameStarted = false;
        initializeBoard();
    }

    void initializeBoard() {
        char cellValue = '1';
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = cellValue++;
            }
        }
    }

    void playGame() {
        cout << "Welcome to Tic-Tac-Toe!" << endl;

        while (!isGameOver()) {
            printBoard();

            if (isHumanTurn) {
                makeHumanMove();
            } else {
                makeComputerMove();
            }

            isHumanTurn = !isHumanTurn;
            isGameStarted = true;
        }

        printBoard();
        printResult();
    }

    bool isGameOver() {
        return isBoardFull() || hasPlayerWon(HUMAN) || hasPlayerWon(COMPUTER);
    }

    bool isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isdigit(board[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    bool hasPlayerWon(char player) {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }

        for (int j = 0; j < SIZE; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                return true;
            }
        }

        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }

    void printBoard() {
        cout << "-------------" << endl;
        for (int i = 0; i < SIZE; i++) {
            cout << "| ";
            for (int j = 0; j < SIZE; j++) {
                if (isHumanTurn && board[i][j] != HUMAN && board[i][j] != COMPUTER) {
                    cout << (isGameStarted ? EMPTY : board[i][j]) << " | ";
                } else {
                    cout << board[i][j] << " | ";
                }
            }
            cout << endl << "-------------" << endl;
        }
    }

    void makeHumanMove() {
        int position;
        do {
            cout << "Enter position (1-9): ";
            cin >> position;
        } while (!isValidMove(position));

        int row = (position - 1) / SIZE;
        int col = (position - 1) % SIZE;
        board[row][col] = HUMAN;
    }

    bool isValidMove(int position) {
        int row = (position - 1) / SIZE;
        int col = (position - 1) % SIZE;

        if (position < 1 || position > SIZE * SIZE) {
            cout << "Invalid move! Try again." << endl;
            return false;
        }
        if (!isdigit(board[row][col])) {
            cout << "Cell already occupied! Try again." << endl;
            return false;
        }
        return true;
    }

    void makeComputerMove() {
        vector<int> bestMove = minimax(0, COMPUTER);
        int position = bestMove[0] * SIZE + bestMove[1] + 1;
        board[bestMove[0]][bestMove[1]] = COMPUTER;
        cout << "Computer chose position: " << position << endl;
    }

    vector<int> minimax(int depth, char player) {
        vector<int> bestMove = { -1, -1, 0 };
        int bestScore;

        if (player == COMPUTER) {
            bestScore = -10;
        } else {
            bestScore = 10;
        }

        if (isGameOver()) {
            int score = evaluateBoard();
            return { -1, -1, score };
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isdigit(board[i][j])) {
                    board[i][j] = player;
                    vector<int> currentMove = minimax(depth + 1, (player == COMPUTER) ? HUMAN : COMPUTER);
                    int currentScore = currentMove[2];
                    board[i][j] = board[i][j] = ('0' + i * SIZE + j + 1);

                    if (player == COMPUTER) {
                        if (currentScore > bestScore) {
                            bestScore = currentScore;
                            bestMove[0] = i;
                            bestMove[1] = j;
                        }
                    } else {
                        if (currentScore < bestScore) {
                            bestScore = currentScore;
                            bestMove[0] = i;
                            bestMove[1] = j;
                        }
                    }
                }
            }
        }

        bestMove[2] = bestScore;
        return bestMove;
    }

    int evaluateBoard() {
        if (hasPlayerWon(COMPUTER)) {
            return 1;
        } else if (hasPlayerWon(HUMAN)) {
            return -1;
        } else {
            return 0;
        }
    }

    void printResult() {
        if (hasPlayerWon(HUMAN)) {
            cout << "Congratulations! You won!" << endl;
        } else if (hasPlayerWon(COMPUTER)) {
            cout << "Computer wins!" << endl;
        } else {
            cout << "It's a draw!" << endl;
        }
    }
};

int main() {
    TicTacToe game;
    game.playGame();
    return 0;
}

