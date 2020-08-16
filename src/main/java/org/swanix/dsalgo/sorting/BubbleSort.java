package org.swanix.dsalgo.sorting;

import static org.swanix.util.DSAlgoUtil.swap;

public class BubbleSort {
    public int[] sort(int[] array) {
        int sizeOfArray = array.length;

        for (int i = sizeOfArray - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
        return array;
    }
}
