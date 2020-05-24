package org.swanix.ds.queue;

public class QueueWithArray {
    private int[] queueArray;
    private int head;
    private int tail;

    QueueWithArray(int size) {
        queueArray = new int[size];
        head = -1;
        tail = -1;
    }

    public void enqueue(int data) {
        if (tail != queueArray.length - 1) {
            if (isEmpty()) {
                queueArray[++tail] = data;
                ++head;
            } else {
                queueArray[++tail] = data;
            }
            System.out.println(data + " is Stored in Queue");
        } else {
            System.out.println("Queue is full. " + data + " could not be Stored in Queue");
        }
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        } else {
            int data = queueArray[head++];
            if (isEmpty()) {
                head = tail = -1;
            }
            System.out.println(data + " is retrieved from Queue");
            return data;
        }

    }


    public boolean isEmpty() {
        if (head == -1 || head > tail) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        } else {
            int size = tail - head + 1;
            System.out.println("Queue size is " + size);
            return size;
        }
    }

    public static void main(String[] args) {
        QueueWithArray queueWithArray = new QueueWithArray(2);
        queueWithArray.dequeue();
        queueWithArray.dequeue();
        queueWithArray.size();
        queueWithArray.enqueue(1);
        queueWithArray.enqueue(2);
        queueWithArray.size();
        queueWithArray.enqueue(3);
        queueWithArray.size();
        queueWithArray.dequeue();
        queueWithArray.dequeue();
        queueWithArray.size();
        queueWithArray.dequeue();
        queueWithArray.size();
        queueWithArray.dequeue();
    }
}
