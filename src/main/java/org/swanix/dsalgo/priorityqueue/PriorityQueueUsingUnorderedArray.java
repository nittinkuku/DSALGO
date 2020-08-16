package org.swanix.dsalgo.priorityqueue;

import org.swanix.util.DSAlgoUtil;

public class PriorityQueueUsingUnorderedArray {
    private int[] array;
    private int count;

    PriorityQueueUsingUnorderedArray(int capacity) {
        array = new int[capacity];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void insert(int data) {
        array[count++] = data;
    }

    public int deleteMax() {
        int max = 0;
        for (int i = 1; i < count; i++) {
            if (array[max] < array[i]) {
                max = i;
            }
        }
        DSAlgoUtil.swap(array, max, count - 1);
        return array[--count];
    }
}
