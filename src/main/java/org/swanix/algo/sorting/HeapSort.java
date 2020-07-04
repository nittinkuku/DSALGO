package org.swanix.algo.sorting;

import org.swanix.util.DSAlgoUtil;

public class HeapSort {
    private int[] array;
    private int size;

    public void sort(){
        for(int k=size/2;k>=1;k--){
            sink(array,k,size);
        }

        while(size>1){
            DSAlgoUtil.swap(array,1,size);
            sink(array,1,--size);
        }
    }

    private void sink(int[] array,int k,int size) {
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
