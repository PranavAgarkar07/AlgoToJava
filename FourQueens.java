// Ass 12: 4-Queens problem by Backtracking
// Based on Horowitz & Sahni, Fundamentals of Computer Algorithms,
// Sec 7.2 "The 8-Queens Problem" — Algorithms Place(k) and NQueens(k, n).
//
//   x[k] = column of queen in row k (1-indexed).
//   Place(k): returns true iff queen k can be placed, i.e. no i < k with
//             x[i] == x[k] (same column) or |x[i]-x[k]| == |i-k| (diagonal).
//   NQueens(k, n): try every column in row k; if Place(k) holds, recurse k+1.
//             When k > n, a solution vector x[1..n] is found.
// Assignment asks for n = 4 (2 solutions); program accepts any n for viva.

import java.util.Scanner;

public class FourQueens {

    static int n;
    static int[] x; // x[k] = column of queen in row k, 1-indexed
    static int solutionCount = 0;

    // Book's Place(k): is the queen just placed in row k under attack?
    static boolean Place(int k) {
        for (int i = 1; i < k; i++) {
            if (x[i] == x[k] || Math.abs(x[i] - x[k]) == Math.abs(i - k))
                return false;
        }
        return true;
    }

    // Book's NQueens(k, n): place queens row by row
    static void NQueens(int k) {
        for (int col = 1; col <= n; col++) {
            x[k] = col;
            if (Place(k)) {
                if (k == n) {
                    printSolution();
                } else {
                    NQueens(k + 1); // go to next row
                }
            }
            // backtrack is implicit: next col overwrites x[k]
        }
    }

    static void printSolution() {
        solutionCount++;
        System.out.println("\nSolution " + solutionCount + ": (row -> col)");
        for (int i = 1; i <= n; i++)
            System.out.print("  Row " + i + " -> Col " + x[i]);
        System.out.println("\nBoard:");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(x[i] == j ? " Q " : " . ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of queens (n, use 4 for this assignment): ");
        n = sc.nextInt();
        if (n < 1) {
            System.out.println("n must be >= 1.");
            sc.close();
            return;
        }

        x = new int[n + 1];
        solutionCount = 0;

        NQueens(1);

        if (solutionCount == 0)
            System.out.println("No solution exists for n = " + n + ".");
        else
            System.out.println("\nTotal solutions for n = " + n + ": " + solutionCount);

        sc.close();
    }
}
