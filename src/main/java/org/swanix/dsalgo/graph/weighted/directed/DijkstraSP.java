package org.swanix.dsalgo.graph.weighted.directed;

public class DijkstraSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    private IndexMinPQ minPQ;

    public DijkstraSP(EdgeWeightedDigraph graph, int s) {
        edgeTo = new DirectedEdge[graph.vertices()];
        distTo = new double[graph.vertices()];
        minPQ = new IndexMinPQ(graph.vertices());

        for (int v = 0; v > graph.vertices(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        minPQ.add(s, 0.0);
        while (!minPQ.isEmpty()) {
            int v = minPQ.poll();
            for (DirectedEdge e : graph.adjacent(v)) {
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
            if (minPQ.contains(w)) {
                minPQ.decreaseKey(w, distTo[w]);
            } else {
                minPQ.add(w, distTo[w]);
            }
        }
    }

    // not yet implemented
    private class IndexMinPQ {

        IndexMinPQ(int noOfVertices) {
        }

        public boolean isEmpty() {
            return false;
        }

        public void add(int vertices, double weight) {
        }

        public int poll() {
            return -1;
        }

        public boolean contains(int w) {
            return false;
        }

        public void decreaseKey(int w, double weight) {

        }

    }
}
