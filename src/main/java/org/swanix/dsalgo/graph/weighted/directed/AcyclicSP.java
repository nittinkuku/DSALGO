package org.swanix.dsalgo.graph.weighted.directed;

public class AcyclicSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicSP(EdgeWeightedDigraph digraph, int s) {
        edgeTo = new DirectedEdge[digraph.vertices()];
        distTo = new double[digraph.vertices()];

        for (int v = 0; v < digraph.vertices(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        TopologicalOrder topologicalOrder = new TopologicalOrder(digraph);
        for (int v : topologicalOrder.order()) {
            for (DirectedEdge e : digraph.adjacent(v)) {
                relax(e);
            }
        }
    }

    private void relax(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }

    //Not Yet Implemented
    public class TopologicalOrder {
        TopologicalOrder(EdgeWeightedDigraph digraph) {
        }

        public int[] order() {
            return new int[0];
        }

    }
}
