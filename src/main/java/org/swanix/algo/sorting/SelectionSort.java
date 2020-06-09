package org.swanix.algo.sorting;

import org.swanix.util.DSAlgoUtil;

public class SelectionSort {
    public void sort(int[] array) {

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
    }

}
