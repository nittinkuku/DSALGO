package org.swanix.dsalgo.graph;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;

    public DepthFirstPaths(Graph graph, int s) {
        this.s = s;
        marked = new boolean[graph.vertices()];
        edgeTo = new int[graph.edges()];
        for (int i = 0; i < marked.length; i++) {
            marked[i] = false;
        }
        for (int i = 0; i < edgeTo.length; i++) {
            edgeTo[i] = -1;
        }
        dfs(graph, s);
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        for (int w : graph.adjacent(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(graph, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public List<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return Collections.emptyList();
        }
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }
}
