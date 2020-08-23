package org.swanix.dsalgo.graph.directed;

public class KosarajuSharirSCC {
    private boolean marked[];
    private int[] id;
    private int count;

    public KosarajuSharirSCC(Digraph digraph) {
        marked = new boolean[digraph.vertices()];
        id = new int[digraph.vertices()];
        DepthFirstOrder depthFirstOrder = new DepthFirstOrder(digraph.reverse());
        for (int v : depthFirstOrder.reversePost()) {
            if (!marked[v]) {
                dfs(digraph, v);
                count++;
            }
        }
    }

    private void dfs(Digraph digraph, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : digraph.adjacent(v)) {
            if (!marked[w]) {
                dfs(digraph, w);
            }
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }
}
