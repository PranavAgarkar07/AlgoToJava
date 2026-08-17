public class knapsackFractional {

    public static void quickSort(Number[][] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    public static int partition(Number[][] arr, int low, int high) {
        double pivot = arr[high][2].doubleValue();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j][2].doubleValue() >= pivot) {  
                i++;
                Number[] temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Put the pivot in its final position
        Number[] temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void fknap(Number[][] arr, int length, int size) {
        for (int i = 0; i < length; i++) {
            float relative = arr[i][0].floatValue() / arr[i][1].floatValue();
            arr[i][2] = relative;
        }

        quickSort(arr, 0, length - 1);

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
