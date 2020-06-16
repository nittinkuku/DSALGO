package org.swanix.prog;

import org.swanix.algo.sorting.QuickSort;

public class KthLargestInArray {

    //Using Quick Sort
    public int findKthLargestElementInTheArray(int[] array, int k) {
        int lo = 0;
        int hi = array.length - 1;

        while (hi > lo) {
            int j = QuickSort.partition(array, lo, hi);
            if (j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                return array[k];
            }
        }
        return array[k];
    }

}
