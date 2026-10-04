// Ass 10: 0/1 Knapsack by Dynamic Programming
// Based on Horowitz & Sahni, Fundamentals of Computer Algorithms,
// Sec 5.5 "0/1 Knapsack" — DP recurrence (tabular form of f_i(y)):
//
//   V[0][y] = 0 for all y
//   V[i][y] = V[i-1][y]                              if w[i] > y
//   V[i][y] = max(V[i-1][y], v[i] + V[i-1][y-w[i]])   otherwise
//
// Answer = V[n][M]. Selected items found by tracing back through `keep`.
// Time: O(n*M), Space: O(n*M). (Book also shows tuple-set optimization;
// this table version is what labs/vivas expect.)

import java.util.Scanner;

public class ZeroOneKnapsack {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items (n): ");
        int n = sc.nextInt();
        int[] v = new int[n + 1]; // profits, 1-indexed like book (p1..pn)
        int[] w = new int[n + 1]; // weights

        System.out.println("Enter profit and weight for each item:");
        for (int i = 1; i <= n; i++) {
            System.out.print("Item " + i + " profit weight: ");
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }

        System.out.print("Enter knapsack capacity (M): ");
        int M = sc.nextInt();
        if (n <= 0 || M < 0) {
            System.out.println("Nothing to do.");
            sc.close();
            return;
        }

        // ---- DP table (book's f_i(y)) ----
        int[][] V = new int[n + 1][M + 1];
        boolean[][] keep = new boolean[n + 1][M + 1];

        for (int i = 1; i <= n; i++) {
            for (int y = 0; y <= M; y++) {
                if (w[i] > y) {
                    V[i][y] = V[i - 1][y];
                } else if (V[i - 1][y] >= v[i] + V[i - 1][y - w[i]]) {
                    V[i][y] = V[i - 1][y];
                } else {
                    V[i][y] = v[i] + V[i - 1][y - w[i]];
                    keep[i][y] = true; // item i taken for capacity y
                }
            }
        }

        System.out.println("\nDP table V[i][y] (max profit using first i items, capacity y):");
        System.out.print("i\\y ");
        for (int y = 0; y <= M; y++)
            System.out.printf("%4d", y);
        System.out.println();
        for (int i = 0; i <= n; i++) {
            System.out.printf("%3d ", i);
            for (int y = 0; y <= M; y++)
                System.out.printf("%4d", V[i][y]);
            System.out.println();
        }

        // ---- Traceback to recover solution vector x[1..n] ----
        int[] x = new int[n + 1];
        int y = M;
        for (int i = n; i >= 1; i--) {
            if (keep[i][y]) {
                x[i] = 1;
                y -= w[i];
            }
        }

        int totalProfit = V[n][M];
        int totalWeight = 0;
        System.out.print("\nItems selected (1 = taken): ");
        for (int i = 1; i <= n; i++) {
            System.out.print(x[i] + " ");
            if (x[i] == 1)
                totalWeight += w[i];
        }
        System.out.println("\nTotal profit = " + totalProfit);
        System.out.println("Total weight = " + totalWeight + " / " + M);

        sc.close();
    }
}
