import java.util.*;

class Edge {
    int source, destination, cost;

    Edge(int source, int destination, int cost) {
        this.source = source;
        this.destination = destination;
        this.cost = cost;
    }
}

public class MinimumCostSpanningTree {

    static int find(int parent[], int vertex) {
        while (parent[vertex] != vertex) {
            vertex = parent[vertex];
        }
        return vertex;
    }

    static void union(int parent[], int u, int v) {
        int rootU = find(parent, u);
        int rootV = find(parent, v);

        parent[rootV] = rootU;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int vertices = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int edgesCount = sc.nextInt();

        Edge[] edges = new Edge[edgesCount];

        System.out.println("Enter source, destination and cost:");

        for (int i = 0; i < edgesCount; i++) {
            int source = sc.nextInt();
            int destination = sc.nextInt();
            int cost = sc.nextInt();

            edges[i] = new Edge(source, destination, cost);
        }

        Arrays.sort(edges, Comparator.comparingInt(e -> e.cost));

        int[] parent = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            parent[i] = i;
        }

        int totalCost = 0;
        int edgeCount = 0;

        System.out.println("\nMinimum Cost Spanning Tree:");

        for (Edge edge : edges) {

            int rootSource = find(parent, edge.source);
            int rootDestination = find(parent, edge.destination);

            if (rootSource != rootDestination) {

                System.out.println(
                    edge.source + " -- " +
                    edge.destination + " = " +
                    edge.cost
                );

                totalCost += edge.cost;
                edgeCount++;

                union(parent, rootSource, rootDestination);
            }

            if (edgeCount == vertices - 1) {
                break;
            }
        }

        System.out.println("\nMinimum Cost = " + totalCost);

        sc.close();
    }
}