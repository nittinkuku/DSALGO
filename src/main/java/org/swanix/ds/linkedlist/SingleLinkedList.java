package org.swanix.ds.linkedlist;

public class SingleLinkedList {
    private Node head;

    private class Node {
        private int data;
        private Node next;
    }

    private void insertAtBeginning(int data) {
        Node node = new Node();
        node.data = data;

        if (head == null) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
    }

    private void insertAtEnd(int data) {
        Node node = new Node();
        node.data = data;

        if (head == null) {
            head = node;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }
    }

    private int deleteFromBeginning() {
        if (head == null) {
            System.out.println("LinkedList is Empty");
            return -1;
        } else {
            int data = head.data;
            head = head.next;
            return data;
        }
    }

    private int deleteFromEnd() {
        if (head == null) {
            System.out.println("LinkedList is Empty");
            return -1;
        } else if (head.next == null) {
            int data = head.data;
            head = null;
            return data;
        } else {
            Node temp = head;
            Node prev = null;
            while (temp.next != null) {
                prev = temp;
                temp = temp.next;
            }
            int data = temp.data;
            prev.next = null;
            return data;
        }
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
