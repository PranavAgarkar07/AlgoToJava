// Ass 13: Sum of Subsets by Backtracking
// Based on Horowitz & Sahni, Fundamentals of Computer Algorithms,
// Sec 7.3 "Sum of Subsets" — Algorithm SumOfSub(s, k, r).
//
//   w[1..n] sorted nondecreasing, target m.
//   s = sum of chosen items so far (sum w[i]*x[i] for i < k)
//   k = current item index under decision
//   r = sum of remaining items (sum w[j] for j >= k)
//   x[k] = 1 -> include w[k] (left child), x[k] = 0 -> exclude (right child).
// Bounding (prunes hopeless branches):
//   include only if s + w[k] <= m; recurse further only if s+w[k]+w[k+1] <= m
//     (or a solution s+w[k] == m is printed).
//   exclude only if s + r - w[k] >= m AND s + w[k+1] <= m.

import java.util.Arrays;
import java.util.Scanner;

public class SumOfSubsets {

    static int n;
    static int[] w; // 1-indexed weights, sorted
    static int[] x; // inclusion vector
    static int m;   // target sum
    static int solutionCount = 0;

    // Book's SumOfSub(s, k, r)
    static void SumOfSub(int s, int k, int r) {
        x[k] = 1; // generate left child: include w[k]
        if (s + w[k] == m) {
            for (int i = k + 1; i <= n; i++)
                x[i] = 0; // clear stale tail so print shows only x[1..k]
            printSolution();
        } else if (k < n && s + w[k] + w[k + 1] <= m) {
            SumOfSub(s + w[k], k + 1, r - w[k]);
        }
        // generate right child: exclude w[k], only if promising
        if (k < n && s + r - w[k] >= m && s + w[k + 1] <= m) {
            x[k] = 0;
            SumOfSub(s, k + 1, r - w[k]);
        }
    }

    static void printSolution() {
        solutionCount++;
        System.out.print("Solution " + solutionCount + ": { ");
        for (int i = 1; i <= n; i++) {
            if (x[i] == 1)
                System.out.print(w[i] + " ");
        }
        System.out.println("}  (indices: " + indexString() + ")");
    }

    static String indexString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++)
            if (x[i] == 1)
                sb.append(i).append(" ");
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements (n): ");
        n = sc.nextInt();
        w = new int[n + 2]; // +1 sentinel slot for w[k+1] checks
        x = new int[n + 2];

        System.out.println("Enter " + n + " positive weights:");
        for (int i = 1; i <= n; i++)
            w[i] = sc.nextInt();

        System.out.print("Enter target sum (m): ");
        m = sc.nextInt();

        Arrays.sort(w, 1, n + 1); // book requires nondecreasing order
        System.out.print("Sorted weights: ");
        for (int i = 1; i <= n; i++)
            System.out.print(w[i] + " ");
        System.out.println();

        int total = 0;
        for (int i = 1; i <= n; i++)
            total += w[i];

        if (w[1] > m || total < m) {
            System.out.println("No solution possible (w[1] > m or total < m).");
            sc.close();
            return;
        }

        solutionCount = 0;
        SumOfSub(0, 1, total);

        if (solutionCount == 0)
            System.out.println("No subset sums to " + m + ".");
        else
            System.out.println("Total subsets found: " + solutionCount);

        sc.close();
    }
}
