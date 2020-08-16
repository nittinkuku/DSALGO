package org.swanix.dsalgo.hash;

public class SeparateChainingHash {
    private int M = 97; // number of chains
    private Node[] st = new Node[M]; //array of chains

    private static class Node {
        private Object key;
        private Object val;
        private Node next;
    }

    private int hash(Object key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Object get(Object key) {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.val;
            }
        }
        return null;
    }
}
