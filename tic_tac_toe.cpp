#include <iostream>
#include <vector>

using namespace std;

vector<int> board(10, 2); 
int turn = 1; 

int possWin(int player) {
    int winningMove = 0;

    for (int i = 1; i <= 9; i += 3) {
        int product = board[i] * board[i + 1] * board[i + 2];
        if (product == player * player * 2) {
            if (board[i] == 2) winningMove = i;
            else if (board[i + 1] == 2) winningMove = i + 1;
            else if (board[i + 2] == 2) winningMove = i + 2;
        }
    }

    for (int i = 1; i <= 3; i++) {
        int product = board[i] * board[i + 3] * board[i + 6];
        if (product == player * player * 2) {
            if (board[i] == 2) winningMove = i;
            else if (board[i + 3] == 2) winningMove = i + 3;
            else if (board[i + 6] == 2) winningMove = i + 6;
        }
    }

    if ((board[1] * board[5] * board[9] == player * player * 2) && (board[1] == 2 || board[5] == 2 || board[9]==2))
        winningMove = 1;
    else if ((board[3] * board[5] * board[7] == player * player * 2) && (board[3] == 2 || board[5] == 2 || board[7]==2))
        winningMove = 3;

    return winningMove;
}

void go(int n) {
    if (turn % 2 == 1) 
        board[n] = 3;
    else 
        board[n] = 5;
    
    turn++;
}

void makeMove() {
    int move = 0;

    if (board[5] == 2)
        move = 5;
    else {
        for (int i = 2; i <= 8; i += 2) {
            if (board[i] == 2) {
                move = i;
                break;
            }
        }
    }

    if (move == 0) {
        for (int i = 1; i <= 9; i++) {
            if (board[i] == 2) {
                move = i;
                break;
            }
        }
    }

    go(move);
}

void printBoard() {
    for (int i = 1; i <= 9; i++) {
        if (i % 3 != 0)
            cout << board[i] << " | ";
        else
            cout << board[i] << endl;
    }
}

int main() {
    while (turn <= 9) {
        cout << "Turn: " << turn << endl;
        printBoard();

        if (turn % 2 == 1){
            cout << "Player X's turn." << endl;
            cout<<"Choose your desired position(1-9) : ";
            int hmove;
            cin>>hmove;
            go(hmove);
        }
        else{
            cout << "Player O's turn." << endl;
            makeMove();
        }


        int winner = possWin((turn % 2 == 1) ? 3 : 5);
        if (winner != 0) {
            cout << endl;
            printBoard();
            if (turn % 2 == 1)
                cout << "Player X wins!" << endl;
            else
                cout << "Player O wins!" << endl;
            break;
        }
    }

    if (turn > 9) {
        cout << "The game ended in a draw!" << endl;
    }

    return 0;
}
