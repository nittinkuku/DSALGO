package org.swanix.dsalgo.stack;

public class StackWithArray {
    private int[] dataArray;
    private int curr;

    public StackWithArray(int size) {
        dataArray = new int[size];
        curr = -1;
    }

    public void push(int data) {
        if (curr != dataArray.length - 1) {
            System.out.println(data + " Pushed to Top");
            dataArray[++curr] = data;
        } else {
            System.out.println("Stack is Full!!!");
        }
    }

    public int pop() {
        if (!isEmpty()) {
            int data = dataArray[curr--];
            System.out.println(data + " popped out of stack");
            return data;
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
