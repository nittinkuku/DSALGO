package org.swanix.dsalgo.graph.weighted;

import org.swanix.dsalgo.unionfind.QuickFind;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class KruskalMST {
    private Queue<Edge> mst = new ArrayDeque<>();

    public KruskalMST(EdgeWeightedGraph graph) {
        PriorityQueue<Edge> minPQ = new PriorityQueue<>();
        for (Edge e : graph.edges()) {
            minPQ.add(e);
        }

        QuickFind unionFind = new QuickFind(graph.vertices());
        while (!minPQ.isEmpty() && mst.size() < graph.vertices() - 1) {
            Edge e = minPQ.poll();
            int v = e.either();
            int w = e.other(v);
            if (!unionFind.isConnected(v, w)) {
                unionFind.union(v, w);
                mst.add(e);
            }
        }
    }

    public Queue<Edge> edges() {
        return mst;
    }
}
