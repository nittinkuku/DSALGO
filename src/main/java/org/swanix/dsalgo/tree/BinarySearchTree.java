package org.swanix.dsalgo.tree;

public class BinarySearchTree {
    private Node root;

    private class Node {
        private int key;
        private int val;
        private Node left, right;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public void put(int key, int val) {
        root = put(root, key, val);
    }

    private Node put(Node x, int key, int val) {
        if (x == null) {
            return new Node(key, val);
        }
        if (x.key < key) {
            x.right = put(x.right, key, val);
        } else if (x.key > key) {
            x.left = put(x.left, key, val);
        } else {
            x.val = val;
        }
        return x;
    }

    public int get(int key) {
        Node x = root;
        while (x != null) {
            if (x.key < key) {
                x = x.right;
            } else if (x.key > key) {
                x = x.left;
            } else {
                return x.key;
            }
        }
        return -1;
    }

    public void inorder() {
        inorder(root);
    }

    public void preorder() {
        preorder(root);
    }

    public void postorder() {
        postorder(root);
    }

    private void inorder(Node x) {
        if (x == null) {
            return;
        }
        inorder(x.left);
        System.out.println(x.key);
        inorder(x.right);
    }

    private void preorder(Node x) {
        if (x == null) {
            return;
        }
        System.out.println(x.key);
        preorder(x.left);
        preorder(x.right);
    }

    private void postorder(Node x) {
        if (x == null) {
            return;
        }
        postorder(x.left);
        postorder(x.right);
        System.out.println(x.key);
    }

    public int size() {
        return size(root);
    }

    public int size(Node x) {
        if (x == null) {
            return 0;
        }
        return 1 + size(x.left) + size(x.right);
    }

    public int min() {
        Node x = min(root);
        if (x == null) {
            return -1;
        } else {
            return x.val;
        }
    }

    private Node min(Node x) {
        if (x == null) {
            return null;
        } else {
            while (x.left != null) {
                x = x.left;
            }
            return x;
        }
    }

    public int max() {
        Node x = max(root);
        if (x == null) {
            return -1;
        } else {
            return x.val;
        }
    }

    private Node max(Node x) {
        if (x == null) {
            return null;
        } else {
            while (x.right != null) {
                x = x.right;
            }
            return x;
        }
    }

    public int rank(int key) {
        return rank(root, key);
    }

    private int rank(Node x, int key) {
        if (x == null) {
            return 0;
        }
        if (key < x.key) {
            return rank(x.left, key);
        } else if (key > x.key) {
            return 1 + size(x.left) + rank(x.right, key);
        } else {
            return size(x.left);
        }
    }

    public int floor(int key) {
        Node x = floor(root, key);
        if (x == null) {
            return -1;
        }
        return x.key;
    }

    private Node floor(Node x, int key) {
        if (x == null) {
            return null;
        }
        if (x.key == key) {
            return x;
        }
        if (x.key > key) {
            return floor(x.left, key);
        }
        Node t = floor(x.right, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        return x;
    }

    public void delete(int key) {
        root = delete(root, key);
    }

    private Node delete(Node x, int key) {
        if (x == null) {
            return null;
        }
        if (key > x.key) {
            x.right = delete(x.right, key);
        } else if (key < x.key) {
            x.left = delete(x.left, key);
        } else {
            if (x.right == null) {
                return x.left;
            }

            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }

        return x;
    }

}