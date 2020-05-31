package org.swanix.ds.linkedlist;

public class DoubleLinkedList {
    private Node head;
    private Node tail;

    private class Node {
        private int data;
        private Node next;
    }

    private void insertAtBeginning(int data) {

    }

    private void insertAtEnd(int data) {

    }

    private int deleteFromBeginning() {
        return -1;
    }

    private int deleteFromEnd() {
        return -1;
    }

    private void reverseLinkedList() {

    }

    private void print() {
        System.out.println("Printing Linked List :");
        for (Node temp = head; temp != null; temp = temp.next) {
            System.out.println("LinkedList : " + temp.data);
        }
    }
}
