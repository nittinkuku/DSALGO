package org.swanix.dsalgo.graph.undirected;

import java.util.ArrayDeque;
import java.util.Queue;

public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;

    public BreadthFirstPaths(Graph graph, int s) {
        this.s = s;
        marked = new boolean[graph.vertices()];
        edgeTo = new int[graph.edges()];
        for (int i = 0; i < marked.length; i++) {
            marked[i] = false;
        }
        for (int i = 0; i < edgeTo.length; i++) {
            edgeTo[i] = -1;
        }
        bfs(graph, s);
    }

    private void bfs(Graph graph, int s) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(s);
        marked[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (int w : graph.adjacent(v)) {
                if (!marked[w]) {
                    queue.add(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                }
            }
        }

    }
}
