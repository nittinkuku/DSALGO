package org.swanix.ds.priorityqueue;

import org.swanix.util.DSAlgoUtil;

public class BinaryHeap {
    private int size;
    private int[] array;

    public void insert(int data) {
        array[++size] = data;
        swim(size);
    }

    public int delMax() {
        int max = array[1];
        DSAlgoUtil.swap(array, 1, size--);
        sink(1);
        array[size + 1] = -1;
        return max;
    }

    public boolean isEmpty(){
        return size==0;
    }

    private void swim(int k) {
        while (k > 1 && array[k / 2] < array[k]) {
            DSAlgoUtil.swap(array, k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && array[j] < array[j + 1]) {
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
