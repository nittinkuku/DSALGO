package org.swanix.dsalgo.graph.nonweighted.undirected;

public class ConnectedComponent {
    private boolean[] marked;
    private int[] id;
    private int count;

    public ConnectedComponent(Graph graph) {
        marked = new boolean[graph.vertices()];
        id = new int[graph.vertices()];
        for (int v = 0; v < graph.vertices(); v++) {
            if (!marked[v]) {
                dfs(graph, v);
                count++;
            }
        }
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : graph.adjacent(v)) {
            if (!marked[w]) {
                dfs(graph, w);
            }
        }
    }
}