import java.util.*;

public class PrimMST {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int vertices = sc.nextInt();

        System.out.print("Enter number of edges: ");
        int edges = sc.nextInt();

        int graph[][] = new int[vertices][vertices];

        System.out.println("Enter source, destination and cost:");

        for (int i = 0; i < edges; i++) {

            int source = sc.nextInt();
            int destination = sc.nextInt();
            int cost = sc.nextInt();

            graph[source][destination] = cost;
            graph[destination][source] = cost;
        }

        boolean visited[] = new boolean[vertices];

        int totalCost = 0;

        visited[0] = true;

        System.out.println("\nMinimum Cost Spanning Tree:");

        for (int count = 0; count < vertices - 1; count++) {

            int minCost = Integer.MAX_VALUE;
            int source = -1;
            int destination = -1;

            for (int i = 0; i < vertices; i++) {

                if (visited[i]) {

                    for (int j = 0; j < vertices; j++) {

                        if (!visited[j] &&
                            graph[i][j] != 0 &&
                            graph[i][j] < minCost) {

                            minCost = graph[i][j];
                            source = i;
                            destination = j;
                        }
                    }
                }
            }
            visited[destination] = true;

            System.out.println(
                source + " -- " +
                destination + " = " +
                minCost
            );

            totalCost += minCost;
        }
        System.out.println("\nMinimum Cost = " + totalCost);
        sc.close();
    }
}