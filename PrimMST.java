
import java.util.*;

class PrimMST {

    private static final int INF = Integer.MAX_VALUE;

    private static int minKey(int[] key, boolean[] mstSet, int V) {
        int min = INF, minIndex = -1;
        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    private static void printMST(int[] parent, int[][] graph, int V) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
        }
    }

    private static void primMST(int[][] graph, int V) {
        int[] parent = new int[V]; // Array to store the constructed MST
        int[] key = new int[V]; // Key values used to pick minimum weight edge in cut
        boolean[] mstSet = new boolean[V]; // To represent set of vertices included in MST
// Initialize all keys as INFINITE
        Arrays.fill(key, INF);
// Always include first vertex in MST.
        key[0] = 0; // Make key 0 so that this vertex is picked as first vertex
        parent[0] = -1; // First node is always root of MST
        for (int count = 0; count < V - 1; count++) {
// Pick the minimum key vertex from the set of vertices not yet included in MST
            int u = minKey(key, mstSet, V);
// Add the picked vertex to the MST Set
            mstSet[u] = true;
// Update key value and parent index of the adjacent vertices of the picked vertex
            for (int v = 0; v < V; v++) {
// graph[u][v] is non-zero only for adjacent vertices of u
// mstSet[v] is false for vertices not yet included in MST
// Update the key only if graph[u][v] is smaller than key[v]
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }
// Print the constructed MST
        printMST(parent, graph, V);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of vertices:");
        int V = scanner.nextInt();
        int[][] graph = new int[V][V];
        System.out.println("Enter the adjacency matrix of the graph:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }
        primMST(graph, V);
        scanner.close();
    }
}
