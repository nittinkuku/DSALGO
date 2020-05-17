package org.swanix.ds.stack;

public class StackWithLL {
    private Node curr;

    private class Node {
        private int data;
        private Node next;
    }

    public StackWithLL() {
        curr = null;
    }

    public void push(int data) {
        Node dataNode = new Node();
        dataNode.data = data;
        dataNode.next = curr;
        curr = dataNode;
    }

    public int pop() {
        if (!isEmpty()) {
            int noPop = curr.data;
            curr = curr.next;
            System.out.println(noPop + " popped out of stack");
            return noPop;
        } else {
            System.out.println("Stack is Empty!!!");
            return -1;
        }
    }


    public boolean isEmpty() {
        if (curr == null) {
            return true;
        } else {
            return false;
        }
    }

}
