public class knapsackFractional {

    public static void sort(Number arr[][], int length) {
        for (int i = 0; i < length; i++) {
            for (int k = i + 1; k < length; k++) {

                if (arr[i][2].doubleValue() < arr[k][2].doubleValue()) {
                  
                    Number[] temp = arr[i];
                    arr[i] = arr[k];
                    arr[k] = temp;
                }
            }
        }
    }

    public static void fknap(Number[][] arr, int length, int size) {
        for (int i = 0; i < length; i++) {
            float relative = arr[i][0].floatValue() / arr[i][1].floatValue();
            arr[i][2] = relative;
        }

        sort(arr, length);

        double totalProfit = 0;
        int currentWeight = 0;

        System.out.println("Items selected (Ratio order):");
        for (int i = 0; i < length; i++) {
            int profit = arr[i][0].intValue();
            int weight = arr[i][1].intValue();
            double ratio = arr[i][2].doubleValue();

            if (currentWeight + weight <= size) {

                currentWeight += weight;
                totalProfit += profit;
                System.out.printf("Full Item  -> Profit: %d, Weight: %d, Ratio: %.2f\n", profit, weight, ratio);
            } else {
                // Take fraction of item
                int remainingCapacity = size - currentWeight;
                double fractionProfit = profit * ((double) remainingCapacity / weight);
                totalProfit += fractionProfit;
                System.out.printf("Fractional -> Profit: %.2f, Weight: %d, Ratio: %.2f\n", fractionProfit,
                        remainingCapacity, ratio);
                break;
            }
        }
        System.out.printf("\nMaximum Profit in Knapsack: %.2f\n", totalProfit);
    }

    public static void main(String[] args) {
        // {profit, weight, ratio}
        Number[][] arr = {
                { 10, 2, 0 },
                { 5, 3, 0 },
                { 15, 5, 0 },
                { 7, 7, 0 },
                { 6, 1, 0 },
                { 18, 4, 0 },
                { 3, 1, 0 }
        };
        int size = 15;
        fknap(arr, arr.length, size);
    }
}
