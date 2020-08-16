package org.swanix.dsalgo.sorting;

import org.swanix.util.DSAlgoUtil;

public class ShuffleSort {
    /*Shuffle an array
    if the array is sorted, we can give random real numbers to the array indexes and sort based on those real numbers.
    The actual array while it is sorted based on the randomly generated numbers for indexes, is actually shuffled randomly.

    This will be costly as it takes extra time for sorting the array based on those numbers
    */


    //another method for shuffling in linear time

    public int[] shuffleArray(int[] array) {
        int sizeOfArray = array.length;
        for (int i = 1; i < sizeOfArray; i++) {
            int r = 1; //generate a random integer between 0 and i (hardcoded to 1 here to compiling the program)
            DSAlgoUtil.swap(array, r, i);
        }
        return array;
    }
}
