package org.swanix.dsalgo.stack;

import java.util.Iterator;

public class StackWithIterable<T> implements Iterable<T> {

    private Node curr;

    private class Node {
        private T data;
        private Node next;
    }

    public StackWithIterable() {
        curr = null;
    }

    public void push(T data) {
        Node node = new Node();
        node.data = data;
        node.next = curr;
        curr = node;
        System.out.println(data + " Pushed to Top");
    }

    public T pop() {
        if (!isEmpty()) {
            T data = curr.data;
            curr = curr.next;
            System.out.println(data + " popped out of stack");
            return data;
        } else {
            System.out.println("Stack is Empty!!!");
            return null;
        }
    }


    public boolean isEmpty() {
        if (curr == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        private Node temp = curr;

        @Override
        public boolean hasNext() {
            if (temp != null) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public T next() {
            T data = temp.data;
            temp = temp.next;
            return data;
        }
    }

    public static void main(String[] args) {
        StackWithIterable<Integer> stackWithIterable = new StackWithIterable<>();
        stackWithIterable.pop();
        stackWithIterable.pop();

        System.out.println("Printing Stack Content: ");
        for (Integer i : stackWithIterable) {
            System.out.println("Stack :" + i);
        }
        stackWithIterable.push(1);
        stackWithIterable.push(2);

        System.out.println("Printing Stack Content: ");
        for (Integer i : stackWithIterable) {
            System.out.println("Stack :" + i);
        }
        stackWithIterable.pop();

        System.out.println("Printing Stack Content: ");
        for (Integer i : stackWithIterable) {
            System.out.println("Stack :" + i);
        }

        stackWithIterable.pop();
        stackWithIterable.pop();

        System.out.println("Printing Stack Content: ");
        for (Integer i : stackWithIterable) {
            System.out.println("Stack :" + i);
        }
    }
}
