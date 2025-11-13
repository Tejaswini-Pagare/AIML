import java.util.*;

public class MergeSortOrders {

    // Generate random timestamps
    static long[] generateTimestamps(int n) {
        long[] a = new long[n];
        Random r = new Random();

        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(1_000_000);  // random timestamp
        }
        return a;
    }

    // Merge Sort Wrapper
    static void mergeSort(long[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    // Recursive Merge Sort
    static void mergeSort(long[] arr, int left, int right) {
        if (left < right) {

            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    // Merge function
    static void merge(long[] arr, int left, int mid, int right) {

        int n1 = mid - left + 1;
        int n2 = right - mid;

        long[] L = new long[n1];
        long[] R = new long[n2];

        // Copy
        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];

        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        // Merge two sorted halves
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }

        // Copy leftover
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // Print first and last few elements
    static void printSample(long[] arr, int count) {
        System.out.println("First " + count + ":");
        for (int i = 0; i < count && i < arr.length; i++)
            System.out.println(arr[i]);

        System.out.println("Last " + count + ":");
        for (int i = arr.length - count; i < arr.length; i++)
            if (i >= 0) System.out.println(arr[i]);
    }

    public static void main(String[] args) {

        int n = 100000;   // 1 Lakh timestamps (you can increase this)
        long[] arr = generateTimestamps(n);

        System.out.println("Sample BEFORE sorting:");
        printSample(arr, 5);

        long start = System.nanoTime();
        mergeSort(arr);
        long end = System.nanoTime();

        System.out.println("\nSample AFTER sorting:");
        printSample(arr, 5);

        System.out.println("\nTime taken: " + (end - start) / 1e6 + " ms");
    }
}
