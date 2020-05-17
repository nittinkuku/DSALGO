package org.swanix.ds.stack;

public class StackWithArray {
    private int[] dataArray;
    private int curr;

    public StackWithArray(int sizeOfStack) {
        dataArray = new int[sizeOfStack];
        curr = -1;
    }

    public void push(int dataToPush) {
        if (curr != dataArray.length - 1) {
            System.out.println(dataToPush + " Pushed to Top");
            dataArray[++curr] = dataToPush;
        } else {
            System.out.println("Stack is Full!!!");
        }
    }

    public int pop() {
        if (!isEmpty()) {
            int noPop = dataArray[curr--];
            System.out.println(noPop + " popped out of stack");
            return noPop;
        } else {
            System.out.println("Stack is Empty!!!");
            return -1;
        }
    }

    public boolean isEmpty() {
        if (curr == -1) {
            return true;
        } else {
            return false;
        }
    }

}
