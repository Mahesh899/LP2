
public class NQueensBranchAndBound {

    private final int n;
    private final boolean[] leftRow;
    private final boolean[] lowerDiagonal;
    private final boolean[] upperDiagonal;
    private final int[][] board;
    private int solutionCount;

    public NQueensBranchAndBound(int n) {
        this.n = n;
        this.board = new int[n][n];
        this.leftRow = new boolean[n];
        this.lowerDiagonal = new boolean[2 * n - 1];
        this.upperDiagonal = new boolean[2 * n - 1];
        this.solutionCount = 0;
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

    private boolean solveNQueens(int col) {
        if (col >= n) {
            solutionCount++;
            return true;
        }

        for (int row = 0; row < n; row++) {
            if (isSafe(row, col)) {
                placeQueen(row, col);
                if (solveNQueens(col + 1)) {
                    return true;
                }
                removeQueen(row, col);
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
                placeQueen(row, col);
                findAllSolutions(col + 1);
                removeQueen(row, col);
            }
        }
    }

    private boolean isSafe(int row, int col) {
        return !leftRow[row] && !lowerDiagonal[row + col] && !upperDiagonal[row - col + n - 1];
    }

    private void placeQueen(int row, int col) {
        board[row][col] = 1;
        leftRow[row] = true;
        lowerDiagonal[row + col] = true;
        upperDiagonal[row - col + n - 1] = true;
    }

    private void removeQueen(int row, int col) {
        board[row][col] = 0;
        leftRow[row] = false;
        lowerDiagonal[row + col] = false;
        upperDiagonal[row - col + n - 1] = false;
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
        System.out.println("Solving " + n + "-Queens Problem using Branch and Bound:\n");

        NQueensBranchAndBound solver = new NQueensBranchAndBound(n);

        // Find just one solution
        solver.solve();

        // Uncomment to find all solutions
        // solver.findAllSolutions();
    }
}
