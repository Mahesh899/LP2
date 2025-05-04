
import java.util.Arrays;

public class GraphColoring {

    private final int numVertices;
    private final int[][] graph;
    private int[] colors;
    private int solutionCount;

    public GraphColoring(int[][] graph, int numVertices) {
        if (graph == null || graph.length != numVertices || graph[0].length != numVertices) {
            throw new IllegalArgumentException("Invalid graph dimensions");
        }
        this.graph = graph;
        this.numVertices = numVertices;
        this.colors = new int[numVertices];
        this.solutionCount = 0;
    }

    public boolean solveGraphColoring(int numColors) {
        if (numColors <= 0) {
            throw new IllegalArgumentException("Number of colors must be positive");
        }

        Arrays.fill(colors, 0); // Reset colors

        if (solve(0, numColors)) {
            printSolution();
            return true;
        } else {
            System.out.println("No solution exists with " + numColors + " colors!");
            return false;
        }
    }

    public void findAllSolutions(int numColors) {
        if (numColors <= 0) {
            throw new IllegalArgumentException("Number of colors must be positive");
        }

        solutionCount = 0;
        Arrays.fill(colors, 0);
        findAllSolutions(0, numColors);
        System.out.println("Total solutions found: " + solutionCount);
    }

    private boolean solve(int vertex, int numColors) {
        if (vertex == numVertices) {
            solutionCount++;
            return true;
        }

        for (int color = 1; color <= numColors; color++) {
            if (isSafe(vertex, color)) {
                colors[vertex] = color;
                if (solve(vertex + 1, numColors)) {
                    return true;
                }
                colors[vertex] = 0; // Backtrack
            }
        }
        return false;
    }

    private void findAllSolutions(int vertex, int numColors) {
        if (vertex == numVertices) {
            solutionCount++;
            printSolution();
            return;
        }

        for (int color = 1; color <= numColors; color++) {
            if (isSafe(vertex, color)) {
                colors[vertex] = color;
                findAllSolutions(vertex + 1, numColors);
                colors[vertex] = 0; // Backtrack
            }
        }
    }

    private boolean isSafe(int vertex, int color) {
        for (int i = 0; i < numVertices; i++) {
            if (graph[vertex][i] == 1 && color == colors[i]) {
                return false;
            }
        }
        return true;
    }

    private void printSolution() {
        System.out.println("Solution " + solutionCount + ":");
        for (int i = 0; i < numVertices; i++) {
            System.out.println("Vertex " + i + " --> Color " + colors[i]);
        }
        System.out.println("---");
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0, 1, 1, 1},
            {1, 0, 1, 0},
            {1, 1, 0, 1},
            {1, 0, 1, 0}
        };

        int numVertices = 4;
        int numColors = 3;

        System.out.println("Graph Coloring Problem\n");
        System.out.println("Adjacency Matrix:");
        for (int[] row : graph) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("\nAttempting to color with " + numColors + " colors:");

        GraphColoring coloring = new GraphColoring(graph, numVertices);

        // Find one solution
        coloring.solveGraphColoring(numColors);

        // Uncomment to find all solutions
        // coloring.findAllSolutions(numColors);
    }
}
