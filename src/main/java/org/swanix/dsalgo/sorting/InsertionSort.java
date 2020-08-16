package org.swanix.dsalgo.sorting;

import static org.swanix.util.DSAlgoUtil.swap;

public class InsertionSort {
// works faster if the array is partially sorted
    public int[] sort(int[] array) {
        int sizeOfArray = array.length;

        for (int i = 1; i < sizeOfArray; i++)
        {
            for (int j = i; j > 0 && (array[j] < array[j-1]); j--)
                swap(array, j, j-1);
        }

        return array;

    }

}
