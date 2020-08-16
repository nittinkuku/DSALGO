package org.swanix.dsalgo.sorting;

public class MergeSort {

    private static int[] aux;      // auxiliary array for merges

    public static void topDownMergesort(int[] array) {
        aux = new int[array.length];    // Allocate space just once.
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int lo, int hi) {  // Sort a[lo..hi].
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(array, lo, mid);       // Sort left half.
        sort(array, mid + 1, hi);     // Sort right half.
        merge(array, lo, mid, hi);  // Merge results (code on page 271).
    }

    public static void merge(int[] array, int lo, int mid, int hi) {  // Merge a[lo..mid] with a[mid+1..hi].
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {  // Copy a[lo..hi] to aux[lo..hi].
            aux[k] = array[k];
        }

        for (int k = lo; k <= hi; k++) {  // Merge back to a[lo..hi].
            if (i > mid) {
                array[k] = aux[j++];
            } else if (j > hi) {
                array[k] = aux[i++];
            } else if (aux[j] < aux[i]) {
                array[k] = aux[j++];
            } else {
                array[k] = aux[i++];
            }
        }
    }

    //Bottom up Merge Sort
    public static void bottomUpMergesort(int[] array) {  // Do lg N passes of pairwise merges.
        int sizeOfArray = array.length;
        for (int sz = 1; sz < sizeOfArray; sz = sz + sz) {     // sz: subarray size
            for (int lo = 0; lo < sizeOfArray - sz; lo += sz + sz) {// lo: subarray index
                merge(array, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, sizeOfArray - 1));
            }
        }
    }

}
