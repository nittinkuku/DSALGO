package org.swanix.ds.queue;

public class Queue {
    private Node first;
    private Node last;

    private class Node {
        private int data;
        private Node next;
    }

    public void enqueue(int data) {
        Node node = new Node();
        node.data = data;
        node.next = null;
        if (last == null) {
            last = first = node;
        } else {
            last.next = node;
            last = node;
        }
        System.out.println(data + " is Stored in Queue");
    }

    public int dequeue() {
        if (!isEmpty()) {
            int data = first.data;
            first = first.next;
            System.out.println(data + " retrieved from Queue");
            if (first == null) {
                first = last = null;
            }
            return data;
        } else {
            System.out.println("Queue is Empty!!!");
            return -1;
        }
    }

    public boolean isEmpty() {
        if (first == null) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        if (isEmpty()) {
            System.out.println("Queue is empty : size 0");
            return 0;
        } else {
            int size = 0;
            Node tempNode = first;
            while (tempNode != null) {
                size++;
                tempNode = tempNode.next;
            }
            System.out.println("Queue size : " + size);
            return size;
        }
    }

    public static void main(String[] args) {
        Queue queue = new Queue();
        queue.dequeue();
        queue.dequeue();
        queue.size();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.size();
        queue.enqueue(3);
        queue.size();
        queue.dequeue();
        queue.dequeue();
        queue.size();
        queue.dequeue();
        queue.size();
        queue.dequeue();
    }
}
