public class NqueenExhaustive {
    private int N;
    private int[] queens;

    public NqueenExhaustive(int N) {
        this.N = N;
        queens = new int[N];
    }

    public void solve() {
        placeQueens(0); // Start placing queens from the first row (row 0).
    }

    private void placeQueens(int row) {
        if (row == N) {
            printSolution(); // If all queens are placed, print the solution.
            return;
        }

        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) { // Check if it's safe to place a queen in this cell.
                queens[row] = col; // Place the queen in the current cell.
                placeQueens(row + 1); // Recursively try to place the next queen in the next row.
            }
        }
    }

    private boolean isSafe(int row, int col) {
        // Check if it's safe to place a queen in the current cell.
        for (int i = 0; i < row; i++) {
            if (queens[i] == col || queens[i] - i == col - row || queens[i] + i == col + row) {
                return false;
            }
        }
        return true;
    }

    private void printSolution() {
        // Print the current board configuration as a solution.
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (queens[row] == col) {
                    System.out.print("Q "); // Print 'Q' for a queen.
                } else {
                    System.out.print(". "); // Print '.' for an empty cell.
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int N = 4; // Change N to the desired board size
        NqueenExhaustive nqueen = new NqueenExhaustive(N);
        nqueen.solve(); // Start solving the N-Queens problem.
    }
}
