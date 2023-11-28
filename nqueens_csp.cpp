#include <iostream>
#include <vector>
using namespace std;

int solutionCount = 0;
int n;
vector<vector<int>> chessboard(101, vector<int>(101, 0));

void printChessboard() {
    for (int row = 0; row < n; row++) {
        for (int col = 0; col < n; col++) {
            cout << (chessboard[row][col] ? "Q " : ". ");
        }
        cout << endl;
    }
    cout << "-----------------\n";
}

bool isSafe(int row, int col) {
    // Check horizontal positions
    for (int i = col; i >= 0; i--) {
        if (chessboard[row][i]) return false;
    }
    
    int i = row, j = col;

    // Check the upper left diagonal
    while (i >= 0 && j >= 0) {
        if (chessboard[i][j]) return false;
        i--;
        j--;
    }

    i = row;
    j = col;

    // Check the lower left diagonal
    while (i < n && j >= 0) {
        if (chessboard[i][j]) return false;
        i++;
        j--;
    }

    return true;
}

void solveNQueens(int currentColumn) {
    if (currentColumn >= n) return;

    for (int row = 0; row < n; row++) {
        if (isSafe(row, currentColumn)) {
            chessboard[row][currentColumn] = 1;
            if (currentColumn == n - 1) {
                printChessboard();
                solutionCount++;
            }
            solveNQueens(currentColumn + 1);
            chessboard[row][currentColumn] = 0;  // Backtracking
        }
    }
}

int main() {
    cin >> n;
    solveNQueens(0);
    cout << "Total solutions: " << solutionCount << endl;
    return 0;
}
