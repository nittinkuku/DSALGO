package org.swanix.dsalgo.hash;

public class LinearProbingHash {
    private int M = 30001;
    private Object[] vals = new Object[M];
    private Object[] keys = new Object[M];

    private int hash(Object key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(Object key, Object val) {
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                break;
            }
        }
        keys[i] = key;
        vals[i] = val;
    }

    public Object get(Object key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (key.equals(keys[i])) {
                return vals[i];
            }
        }
        return null;
    }

}
