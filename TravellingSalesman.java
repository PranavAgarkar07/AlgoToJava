// Ass 11: Optimal tour of a directed graph (Travelling Salesperson Problem)
// Based on Horowitz & Sahni, Fundamentals of Computer Algorithms,
// Sec 5.6 "The Traveling Salesperson Problem" — DP with bitmask memoization
// of the book's g(i, S) recurrence:
//
//   g(i, empty)      = c[i][1]              (return to start, 1-indexed)
//   g(i, S)          = min over j in S { c[i][j] + g(j, S - {j}) }
//
// Answer = g(1, {2..n}). Vertices numbered 1..n, start/end at 1.
// Works for DIRECTED graphs (cost matrix need not be symmetric).
// Time: O(n^2 * 2^n), Space: O(n * 2^n). Keep n <= ~12 for lab machines.

import java.util.Arrays;
import java.util.Scanner;

public class TravellingSalesman {

    static final int INF = 9999;
    static int n;
    static int[][] cost; // 0-indexed internally, displayed 1-indexed
    static int[][] memo; // memo[pos][mask], -1 = uncomputed
    static int ALL;

    // g(pos, mask): min cost to start at pos, visit all UNvisited cities, return to 0
    static int g(int pos, int mask) {
        if (mask == ALL)
            return cost[pos][0]; // all visited -> go home (INF if no edge)
        if (memo[pos][mask] != -1)
            return memo[pos][mask];
        int best = INF;
        for (int city = 0; city < n; city++) {
            if ((mask & (1 << city)) == 0 && cost[pos][city] < INF) {
                int sub = g(city, mask | (1 << city));
                if (sub < INF)
                    best = Math.min(best, cost[pos][city] + sub);
            }
        }
        return memo[pos][mask] = best;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices (n): ");
        n = sc.nextInt();
        if (n < 2) {
            System.out.println("Need at least 2 vertices.");
            sc.close();
            return;
        }
        if (n > 15) {
            System.out.println("Warning: DP is O(n^2*2^n); n > 15 may be very slow.");
        }

        cost = new int[n][n];
        System.out.println("Enter cost matrix (" + n + "x" + n + "), use " + INF + " if no edge:");
        System.out.println("(Directed graph allowed: cost[i][j] may differ from cost[j][i])");
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                cost[i][j] = sc.nextInt();
                if (cost[i][j] < 0)
                    cost[i][j] = INF;
            }

        ALL = (1 << n) - 1;
        memo = new int[n][1 << n];
        for (int[] row : memo)
            Arrays.fill(row, -1);

        int answer = g(0, 1); // start at city 1 (index 0), mask has city 1 visited

        if (answer >= INF) {
            System.out.println("No Hamiltonian tour exists (graph not strongly connected).");
            sc.close();
            return;
        }

        // Rebuild optimal tour by re-walking the recurrence
        StringBuilder tour = new StringBuilder();
        tour.append("1");
        int pos = 0, mask = 1;
        while (mask != ALL) {
            int next = -1, bestVal = INF;
            for (int city = 0; city < n; city++) {
                if ((mask & (1 << city)) == 0 && cost[pos][city] < INF) {
                    int sub = g(city, mask | (1 << city));
                    if (sub < INF && cost[pos][city] + sub < bestVal) {
                        bestVal = cost[pos][city] + sub;
                        next = city;
                    }
                }
            }
            if (next == -1)
                break; // should not happen since answer < INF
            tour.append(" -> ").append(next + 1);
            pos = next;
            mask |= (1 << pos);
        }
        tour.append(" -> 1");

        System.out.println("\nOptimal tour: " + tour);
        System.out.println("Minimum tour cost = " + answer);

        sc.close();
    }
}
