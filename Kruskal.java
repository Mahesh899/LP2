import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;
    
    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
    
    @Override
    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
    }
}

class Subset {
    int parent, rank;
}

class Graph {
    int V, E;
    Edge[] edges;
    
    public Graph(int v, int e) {
        V = v;
        E = e;
        edges = new Edge[E];
    }
    
    int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }
    
    void union(Subset[] subsets, int x, int y) {
        int rootX = find(subsets, x);
        int rootY = find(subsets, y);
        
        if (subsets[rootX].rank < subsets[rootY].rank)
            subsets[rootX].parent = rootY;
        else if (subsets[rootX].rank > subsets[rootY].rank)
            subsets[rootY].parent = rootX;
        else {
            subsets[rootY].parent = rootX;
            subsets[rootX].rank++;
        }
    }
    
    void kruskalMST() {
        Edge[] result = new Edge[V];
        int e = 0;
        int i = 0;
        
        // Initialize result array
        for (i = 0; i < V; ++i)
            result[i] = new Edge(0, 0, 0);
            
        // Sort all edges
        Arrays.sort(edges);
        
        // Allocate memory for subsets
        Subset[] subsets = new Subset[V];
        for (i = 0; i < V; ++i) {
            subsets[i] = new Subset();
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }
        
        i = 0;
        
        while (e < V - 1 && i < E) {  // Added check for i < E to prevent ArrayIndexOutOfBounds
            Edge nextEdge = edges[i++];
            
            int x = find(subsets, nextEdge.src);
            int y = find(subsets, nextEdge.dest);
            
            if (x != y) {
                result[e++] = nextEdge;
                union(subsets, x, y);
            }
        }
        
        System.out.println("Edge \tWeight");
        for (i = 0; i < e; ++i)
            System.out.println(result[i].src + " - " + result[i].dest + "\t" + result[i].weight);
    }
}

public class Kruskal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the number of vertices:");
        int V = scanner.nextInt();
        
        System.out.println("Enter the number of edges:");
        int E = scanner.nextInt();
        
        Graph graph = new Graph(V, E);
        
        System.out.println("Enter the edges (source, destination, weight):");
        for (int i = 0; i < E; i++) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.edges[i] = new Edge(src, dest, weight);
        }
        
        graph.kruskalMST();
        scanner.close();
    }
}