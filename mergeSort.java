public class mergeSort {

    public static void MS(int a[], int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            MS(a, low, mid);
            MS(a, mid + 1, high);

            merge(a, low, mid, high);
        }
    }

    public static void merge(int a[], int low, int mid, int high) {
        int h = low;
        int i = low;
        int j = mid + 1;

        int[] b = new int[a.length];

        while ((h <= mid) && (j <= high)) {
            if (a[h] <= a[j]) {
                b[i] = a[h]; 
                h = h + 1;
            } else {
                b[i] = a[j];
                j = j + 1;
            }
            i = i + 1;
        }

        if (h > mid) {

            for (int k = j; k <= high; k++) {
                b[i] = a[k];
                i = i + 1;
            }
        } else {
            for (int k = h; k <= mid; k++) {
                b[i] = a[k];
                i = i + 1;
            }
        }

        for (int k = low; k <= high; k++) {
            a[k] = b[k];
        }
    }

    public static void main(String args[]) {
        int[] arr = {
                342, 89, 412, 23, 174, 495, 61, 287, 12, 199,
                432, 55, 318, 92, 204, 471, 8, 143, 365, 29,
                488, 114, 253, 76, 399, 15, 222, 451, 67, 310,
                181, 404, 43, 269, 98, 354, 500, 131, 211, 84,
                466, 159, 292, 37, 122, 381, 71, 245, 419, 5,
                167, 333, 91, 444, 18, 277, 359, 52, 149, 482,
                103, 391, 64, 231, 427, 11, 188, 305, 79, 369,
                457, 26, 137, 491, 146, 321, 58, 219, 411, 95,
                281, 439, 3, 172, 347, 73, 259, 395, 49, 128,
                476, 82, 201, 314, 448, 34, 155, 385, 299, 119
        };

        MS(arr, 0, arr.length - 1);

        for (int val : arr) {
            System.out.print(val + " ");
        }
    }
}
