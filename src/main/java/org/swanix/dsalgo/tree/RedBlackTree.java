package org.swanix.dsalgo.tree;

public class RedBlackTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    private class Node {
        private int key;
        private int val;
        private Node left, right;
        private boolean color;

        public Node(int key, int val, boolean color) {
            this.key = key;
            this.val = val;
            this.color = color;
        }
    }

    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }


    private Node put(Node h, int key, int val) {
        if (h == null) {
            return new Node(key, val, RED);
        }
        if (key < h.key) {
            h.left = put(h.left, key, val);
        } else if (key > h.key) {
            h.right = put(h.right, key, val);
        } else if (key == h.key) {
            h.val = val;
        }

        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        return h;
    }


    private Node rotateLeft(Node h) {
        assert isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        assert isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h) {
        assert !isRed(h);
        assert isRed(h.left);
        assert isRed(h.right);
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public int get(int key) {
        Node x = root;
        while (x != null) {
            if (key == x.key) {
                return x.val;
            } else if (key > x.key) {
                x = x.right;
            } else if (key < x.key) {
                x = x.left;
            }
        }
        return -1;
    }
}
