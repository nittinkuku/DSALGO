package org.swanix.algo.searching;

public class BinarySearch {


    public static int search(int[] array, int key) {

        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] < key) {
                low = mid + 1;
            } else if (array[mid] > key) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

}
