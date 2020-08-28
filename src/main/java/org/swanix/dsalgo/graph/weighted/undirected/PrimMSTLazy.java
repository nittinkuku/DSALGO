package org.swanix.dsalgo.graph.weighted.undirected;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class PrimMSTLazy {
    private boolean[] marked;  //vertices on MST
    private Queue<Edge> mst;   //edges on MST
    private PriorityQueue<Edge> minPQ; //PQ of edges with vertices in MST

    public PrimMSTLazy(EdgeWeightedGraph graph) {
        minPQ = new PriorityQueue<>();
        mst = new ArrayDeque<>();
        marked = new boolean[graph.vertices()];
        visit(graph, 0);

        while (!minPQ.isEmpty()) {
            Edge e = minPQ.poll();
            int v = e.either();
            int w = e.other(v);
            if (marked[v] && marked[w]) {
                continue;
            }
            if (!marked[v]) {
                visit(graph, v);
            }
            if (!marked[w]) {
                visit(graph, w);
            }
        }
    }

    private void visit(EdgeWeightedGraph graph, int v) {
        marked[v] = true;
        for (Edge e : graph.adjacent(v)) {
            if (!marked[e.other(v)]) {
                minPQ.add(e);
            }
        }
    }

    public Queue<Edge> getMST() {
        return mst;
    }
}
