// Ass 9: Minimum-cost s-t path in a Multistage Graph
// Based on Horowitz & Sahni, Fundamentals of Computer Algorithms,
// Sec 5.2 Multistage Graphs — Algorithm BGRAPH (backward reasoning).
//
// Recurrence (book): bcost[n] = 0
//   bcost[j] = min over r (j < r <= n, <j,r> is an edge) { c[j][r] + bcost[r] }
//   d[j] = r that gives the minimum.
// Path is rebuilt forward: path[1] = 1 (s), path[i] = d[path[i-1]] till n (t).
// Time: O(n^2), Space: O(n). Vertices MUST be numbered so edges go j -> r, j < r.

import java.util.Scanner;

public class MultistageGraph {

    static final int INF = 9999;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices (n, source=1, sink=n): ");
        int n = sc.nextInt();
        if (n < 2) {
            System.out.println("Need at least 2 vertices.");
            sc.close();
            return;
        }

        // cost matrix, 1-indexed. Enter 0/INF sentinel for "no edge".
        int[][] c = new int[n + 1][n + 1];
        System.out.println("Enter cost matrix (" + n + "x" + n + "), use " + INF + " if no edge:");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                c[i][j] = sc.nextInt();
                if (c[i][j] <= 0 && i != j)
                    c[i][j] = INF; // treat 0 / negative as "no edge"
            }
        }

        // ---- BGRAPH (book) ----
        int[] bcost = new int[n + 1]; // bcost[j] = min cost j -> n
        int[] d = new int[n + 1];     // d[j] = next vertex on optimal j->n path
        bcost[n] = 0;

        for (int j = n - 1; j >= 1; j--) {
            bcost[j] = INF;
            d[j] = -1;
            for (int r = j + 1; r <= n; r++) {
                if (c[j][r] != INF && c[j][r] + bcost[r] < bcost[j]) {
                    bcost[j] = c[j][r] + bcost[r];
                    d[j] = r;
                }
            }
        }

        if (bcost[1] >= INF) {
            System.out.println("No path from source (1) to sink (" + n + ").");
            sc.close();
            return;
        }

        // Rebuild path: 1 = path[1] -> d[path[1]] -> ... -> n
        System.out.println("\nVertex : bcost (min cost to sink), d (next vertex)");
        for (int j = 1; j <= n; j++) {
            if (j == n)
                System.out.println("  " + j + "    : " + bcost[j] + ", -");
            else
                System.out.println("  " + j + "    : " + bcost[j] + ", " + d[j]);
        }

        System.out.print("\nMinimum-cost path 1 -> " + n + " : 1");
        int v = 1;
        while (v != n && v != -1) {
            v = d[v];
            System.out.print(" -> " + v);
        }
        System.out.println("\nMinimum cost = " + bcost[1]);

        sc.close();
    }
}
