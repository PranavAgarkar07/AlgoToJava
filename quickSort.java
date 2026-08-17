public class quickSort {

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static int partition(int[] arr, int low, int high){
        int pivote = arr[low];
        int i = low;
        int j = high;
        while(i<j){
            while(i<=high && arr[i]<=pivote){
                i++;
            }
            while(j>= low+1 && arr[j]>pivote){
                j--;
            }
            if(i<j) swap(arr, i, j);
        }
        swap(arr, low, j);
        return j;
    }

    public static void qs(int[] arr, int low, int high){
        if(low<high){
            int pIndex = partition(arr, low,high);
            qs(arr, low,pIndex-1);
            qs(arr,pIndex+1,high);

        }
    }


    public static void main(String args[]){
        int[] arr = {56,8,0,44,23};
        qs(arr, 0, arr.length-1);
        for (int val : arr) {
            System.out.print(val + " ");
        }
   }

   
}
