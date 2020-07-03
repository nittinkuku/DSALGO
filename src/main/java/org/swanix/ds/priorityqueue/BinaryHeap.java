package org.swanix.ds.priorityqueue;

import org.swanix.util.DSAlgoUtil;

public class BinaryHeap {
    private int index;
    private int[] array;

    public void insert(int data) {
        array[++index] = data;
        swim(index);
    }

    public int delMax() {
        int max = array[1];
        DSAlgoUtil.swap(array, 1, index--);
        sink(1);
        array[index + 1] = -1;
        return max;
    }

    public boolean isEmpty(){
        return index==0;
    }

    private void swim(int k) {
        while (k > 1 && array[k / 2] < array[k]) {
            DSAlgoUtil.swap(array, k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= index) {
            int j = 2 * k;
            if (j < index && array[j] < array[j + 1]) {
                j++;
            }
            if (!(array[k] < array[j])) {
                break;
            }
            DSAlgoUtil.swap(array, k, j);
            k = j;
        }
    }
}
