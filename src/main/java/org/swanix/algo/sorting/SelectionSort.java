package org.swanix.algo.sorting;

import org.swanix.util.DSAlgoUtil;

public class SelectionSort {
    //takes almost same time in every condition. Takes same amount of time on fully unsorted, partially sorted, array with all keys same
    public int[] sort(int[] array) {

        int sizeOfArray = array.length;

        for (int i = 0; i < sizeOfArray - 1; i++) {
            int indexOfMin = i;

            for (int j = i + 1; j < sizeOfArray; j++) {
                if (array[indexOfMin] < array[j]) {
                    indexOfMin = j;
                }
            }
            DSAlgoUtil.swap(array, i, indexOfMin);
        }

        return  array;
    }

}
