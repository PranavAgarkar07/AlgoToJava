// Ass 14: Hamiltonian Cycle by Backtracking
// Based on Horowitz & Sahni, Fundamentals of Computer Algorithms,
// Sec 7.5 "Hamiltonian Cycles" — Algorithms Hamiltonian(k) and NextValue(k).
//
//   x[1..n] = path vertices, x[1] = 1 fixed (avoids duplicate rotations).
//   NextValue(k): picks next candidate vertex for x[k]:
//     repeatedly x[k] = (x[k]+1) mod (n+1); skip 0; accept if
//     (a) edge <x[k-1], x[k]> exists, (b) x[k] distinct from x[1..k-1],
//     (c) if k == n, edge <x[n], x[1]> (closing edge) exists.
//   Hamiltonian(k): for each NextValue(k) != 0, if k == n print cycle,
//     else recurse k+1. Prints ALL Hamiltonian cycles starting at 1.
// A "round-trip visiting every vertex once and returning to start" exists
// iff at least one such cycle is printed.

import java.util.Scanner;

public class HamiltonianCycle {

    static int n;
    static int[][] a; // adjacency matrix, 1-indexed
    static int[] x;   // path
    static int solutionCount = 0;

    // Book's NextValue(k)
    static void NextValue(int k) {
        int j;
        do {
            x[k] = (x[k] + 1) % (n + 1); // next candidate, 0 means no more
            if (x[k] == 0)
                return;
            if (a[x[k - 1]][x[k]] == 0)
                continue; // no edge x[k-1] -> x[k]
            // check distinctness: x[k] not already in path
            for (j = 1; j < k; j++) {
                if (x[j] == x[k])
                    break;
            }
            if (j == k) { // distinct so far
                if (k < n || (k == n && a[x[n]][x[1]] != 0))
                    return; // accept (closing edge checked when k == n)
            }
        } while (true);
    }

    // Book's Hamiltonian(k)
    static void Hamiltonian(int k) {
        do {
            NextValue(k);
            if (x[k] == 0)
                return; // no new vertex possible -> backtrack
            if (k == n) {
                printCycle();
            } else {
                Hamiltonian(k + 1);
            }
        } while (true);
    }

    static void printCycle() {
        solutionCount++;
        System.out.print("Cycle " + solutionCount + ": ");
        for (int i = 1; i <= n; i++)
            System.out.print(x[i] + " -> ");
        System.out.println(x[1]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices (n): ");
        n = sc.nextInt();
        if (n < 3) {
            System.out.println("Hamiltonian cycle needs n >= 3.");
            sc.close();
            return;
        }

        a = new int[n + 1][n + 1];
        x = new int[n + 1];

        System.out.println("Enter adjacency matrix (" + n + "x" + n + "), 1 = edge, 0 = no edge:");
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++)
                a[i][j] = sc.nextInt();

        x[1] = 1; // fix start vertex to 1
        solutionCount = 0;
        Hamiltonian(2);

        if (solutionCount == 0)
            System.out.println("No Hamiltonian cycle exists (no round-trip visiting every vertex once).");
        else
            System.out.println("Total Hamiltonian cycles (starting at 1): " + solutionCount);

        sc.close();
    }
}
