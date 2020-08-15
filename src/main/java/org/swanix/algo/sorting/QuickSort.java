package org.swanix.algo.sorting;

import org.swanix.util.DSAlgoUtil;

public class QuickSort {
    public static void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int j = partition(array, lo, hi);  // Partition (see page 291).
        sort(array, lo, j - 1);              // Sort left part a[lo .. j-1].
        sort(array, j + 1, hi);              // Sort right part a[j+1 .. hi].
    }

    public static int partition(int[] array, int lo, int hi) {  // Partition into a[lo..j-1], a[j], a[j+1..hi] and return j.
        int i = lo, j = hi + 1;            // left and right scan indices
        int v = array[lo];            // partitioning item
        while (true) {  // Scan right, scan left, check for scan complete, and exchange.
            while (array[++i] < v) {
                if (i == hi) {
                    break;
                }
            }
            while (v < array[--j]) {
                if (j == lo) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            DSAlgoUtil.swap(array, i, j);
        }
        DSAlgoUtil.swap(array, lo, j);       // Put partitioning item v into a[j].
        return j;             // with a[lo..j-1] <= a[j] <= a[j+1..hi].
    }


    private static void threeWayQuicksort(int[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, i = lo + 1, gt = hi;
        int v = a[lo];
        while (i <= gt) {
            int cmp = a[i] - v;
            if (cmp < 0) {
                DSAlgoUtil.swap(a, lt++, i++);
            } else if (cmp > 0) {
                DSAlgoUtil.swap(a, i, gt--);
            } else {
                i++;
            }
        }  // Now a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }
}
