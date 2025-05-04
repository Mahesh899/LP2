
public class NQueensBacktracking {

    private final int n;
    private int solutionCount = 0;
    private int[][] board;

    public NQueensBacktracking(int n) {
        this.n = n;
        this.board = new int[n][n];
    }

    public void solve() {
        if (solveNQueens(0)) {
            System.out.println("Solution found:");
            printSolution();
        } else {
            System.out.println("No solution exists for " + n + " queens!");
        }
    }

    public void findAllSolutions() {
        solutionCount = 0;
        findAllSolutions(0);
        System.out.println("Total solutions found: " + solutionCount);
    }

    private boolean isSafe(int row, int col) {
        // Check row on left side
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }

        // Check upper diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // Check lower diagonal
        for (int i = row, j = col; i < n && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    private boolean solveNQueens(int col) {
        if (col >= n) {
            solutionCount++;
            return true;
        }

        for (int row = 0; row < n; row++) {
            if (isSafe(row, col)) {
                board[row][col] = 1;
                if (solveNQueens(col + 1)) {
                    return true;
                }
                board[row][col] = 0; // Backtrack
            }
        }
        return false;
    }

    private void findAllSolutions(int col) {
        if (col >= n) {
            solutionCount++;
            printSolution();
            return;
        }

        for (int row = 0; row < n; row++) {
            if (isSafe(row, col)) {
                board[row][col] = 1;
                findAllSolutions(col + 1);
                board[row][col] = 0; // Backtrack
            }
        }
    }

    private void printSolution() {
        System.out.println("Solution " + solutionCount + ":");
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell == 1 ? "Q " : ". ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int n = 8; // Board size
        System.out.println("Solving " + n + "-Queens Problem:\n");

        NQueensBacktracking solver = new NQueensBacktracking(n);

        // Find just one solution
        solver.solve();

        // Uncomment to find all solutions
        // solver.findAllSolutions();
    }
}
