package org.swanix.dsalgo.sorting;

import org.swanix.util.DSAlgoUtil;

public class ShellSort {

    public int[] sort(int[] array) {
        // Sort a[] into increasing order.
        int sizeOfArray = array.length;
        int h = 1;
        while (h < sizeOfArray / 3) {
            h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
        }
        while (h >= 1) {  // h-sort the array.
            for (int i = h; i < sizeOfArray; i++) {  // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
                for (int j = i; j >= h && array[j] < array[j - h]; j -= h)
                    DSAlgoUtil.swap(array, j, j - h);
            }
            h = h / 3;
        }
        return array;
    }
}
