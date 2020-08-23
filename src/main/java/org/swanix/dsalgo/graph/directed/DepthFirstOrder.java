package org.swanix.dsalgo.graph.directed;

import java.util.Stack;

public class DepthFirstOrder {
    private boolean[] marked;
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph digraph) {
        reversePost = new Stack<>();
        marked = new boolean[digraph.vertices()];
        for (int v = 0; v < digraph.vertices(); v++) {
            if (!marked[v]) {
                dfs(digraph, v);
            }
        }
    }

    private void dfs(Digraph digraph, int v) {
        marked[v] = true;
        for (int w : digraph.adjacent(v)) {
            if (!marked[w]) {
                dfs(digraph, w);
            }
            reversePost.push(v);
        }
    }

    public Stack<Integer> reversePost() {
        return reversePost;
    }
}
