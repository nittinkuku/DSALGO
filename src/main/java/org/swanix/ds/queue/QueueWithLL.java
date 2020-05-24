package org.swanix.ds.queue;

public class QueueWithLL {
    private Node head;
    private Node tail;

    private class Node {
        private int data;
        private Node next;
    }

    public void enqueue(int data) {
        Node node = new Node();
        node.data = data;
        node.next = null;
        if (isEmpty()) {
            tail = head = node;
        } else {
            tail.next = node;
            tail = node;
        }
        System.out.println(data + " is Stored in Queue");
    }

    public int dequeue() {
        if (!isEmpty()) {
            int data = head.data;
            head = head.next;
            System.out.println(data + " retrieved from Queue");
            if (head == null) {
                head = tail = null;
            }
            return data;
        } else {
            System.out.println("Queue is Empty!!!");
            return -1;
        }
    }

    public boolean isEmpty() {
        if (head == null) {
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
            Node tempNode = head;
            while (tempNode != null) {
                size++;
                tempNode = tempNode.next;
            }
            System.out.println("Queue size : " + size);
            return size;
        }
    }

    public static void main(String[] args) {
        QueueWithLL queueWithLL = new QueueWithLL();
        queueWithLL.dequeue();
        queueWithLL.dequeue();
        queueWithLL.size();
        queueWithLL.enqueue(1);
        queueWithLL.enqueue(2);
        queueWithLL.size();
        queueWithLL.enqueue(3);
        queueWithLL.size();
        queueWithLL.dequeue();
        queueWithLL.dequeue();
        queueWithLL.size();
        queueWithLL.dequeue();
        queueWithLL.size();
        queueWithLL.dequeue();
    }
}
