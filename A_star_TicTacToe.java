
import java.util.Scanner;

public class A_star_TicTacToe {

    // Creating a 3x3 board for the game
    private static char[][] board = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };

    private static Scanner scanner = new Scanner(System.in);
    private static char currentPlayer = 'X';

    // Function to print the game board
    public static void printBoard() {
        System.out.println("\nCurrent Board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("---------");
            }
        }
        System.out.println();
    }

    // Function to check if a player has won
    public static boolean isWin(char player) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        // Check diagonals
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player)
                || (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    // Function to check if the board is full (no more moves possible)
    public static boolean isFull() {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Function to validate and process player move
    public static boolean makeMove(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            System.out.println("Invalid input! Row and column must be between 1 and 3.");
            return false;
        }
        if (board[row][col] != ' ') {
            System.out.println("This cell is already occupied!");
            return false;
        }
        board[row][col] = currentPlayer;
        return true;
    }

    // Main game loop
    public static void main(String[] args) {
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("Player X goes first. Enter row and column numbers (1-3) separated by space.");

        boolean gameWon = false;

        while (!gameWon && !isFull()) {
            printBoard();
            System.out.print("Player " + currentPlayer + ", enter your move (row[1-3] column[1-3]): ");

            try {
                int row = scanner.nextInt() - 1;
                int col = scanner.nextInt() - 1;

                if (makeMove(row, col)) {
                    if (isWin(currentPlayer)) {
                        gameWon = true;
                        printBoard();
                        System.out.println("Congratulations! Player " + currentPlayer + " wins!");
                    } else {
                        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter numbers only.");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        if (!gameWon) {
            printBoard();
            System.out.println("The game is a tie!");
        }

        scanner.close();
    }
}
