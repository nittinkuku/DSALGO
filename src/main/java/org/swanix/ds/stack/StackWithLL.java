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
        Node node = new Node();
        node.data = data;
        node.next = curr;
        curr = node;
        System.out.println(data + " Pushed to Top");
    }

    public int pop() {
        if (!isEmpty()) {
            int data = curr.data;
            curr = curr.next;
            System.out.println(data + " popped out of stack");
            return data;
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
