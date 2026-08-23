import java.util.Scanner;

public class MinMaxDivideConquer {

    static class Result {
        int min;
        int max;

        Result(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    static Result findMinMax(int[] arr, int low, int high) {

        if (low == high) {
            return new Result(arr[low], arr[low]);
        }

        if (high == low + 1) {
            if (arr[low] < arr[high]) {
                return new Result(arr[low], arr[high]);
            } else {
                return new Result(arr[high], arr[low]);
            }
        }

        int mid = (low + high) / 2;

        Result left = findMinMax(arr, low, mid);
        Result right = findMinMax(arr, mid + 1, high);

        int minimum = Math.min(left.min, right.min);
        int maximum = Math.max(left.max, right.max);

        return new Result(minimum, maximum);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Result result = findMinMax(arr, 0, n - 1);

        System.out.println("Minimum element = " + result.min);
        System.out.println("Maximum element = " + result.max);

        sc.close();
    }
}