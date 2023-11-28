#include <iostream>
#include <vector>
#include <queue>
#include <cmath>

using namespace std;

// Define a state representation for the N-Queens problem
struct State {
    vector<int> queens; // queens[i] represents the column position of the queen in row i

    // Calculate the heuristic value (number of attacking queen pairs)
    int calculateHeuristic() const {
        int heuristic = 0;
        int n = queens.size();

        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                // Check for attacking pairs in the same row or diagonally
                if (queens[i] == queens[j] || abs(queens[i] - queens[j]) == abs(i - j)) {
                    heuristic++;
                }
            }
        }

        return heuristic;
    }

    // Overload the less than operator for priority queue ordering
    bool operator<(const State& other) const {
        return calculateHeuristic() > other.calculateHeuristic(); // Max heap
    }
};

// A* search algorithm
bool solveNQueens(int n) {
    priority_queue<State> pq;
    State initial;
    initial.queens.resize(n);

    pq.push(initial);

    while (!pq.empty()) {
        State current = pq.top();
        pq.pop();

        if (current.calculateHeuristic() == 0) {
            // Solution found
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    cout << (current.queens[i] == j ? "Q" : ".");
                }
                cout << endl;
            }
            return true;
        }

        // Generate next states by moving one queen at a time
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                State next = current;
                next.queens[i] = j;
                pq.push(next);
            }
        }
    }

    // No solution found
    return false;
}

int main() {
    int n;
    cout << "Enter the number of queens (N): ";
    cin >> n;

    if (solveNQueens(n)) {
        cout << "Solution found!" << endl;
    } else {
        cout << "No solution exists." << endl;
    }

    return 0;
}
