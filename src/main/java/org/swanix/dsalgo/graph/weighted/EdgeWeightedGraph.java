package org.swanix.dsalgo.graph.weighted;

import java.util.ArrayList;
import java.util.List;

public class EdgeWeightedGraph {
    private final int noOfVertices;
    private final List<Edge>[] adjacent;

    public EdgeWeightedGraph(int noOfVertices) {
        this.noOfVertices = noOfVertices;
        adjacent = (List<Edge>[]) new List[noOfVertices];
        for (int v = 0; v < noOfVertices; v++) {
            adjacent[v] = new ArrayList<>();
        }
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adjacent[v].add(e);
        adjacent[w].add(e);
    }

    public List<Edge> adjacent(int v) {
        return adjacent[v];
    }

    public List<Edge> edges() {
        return null;
    }

    public int vertices() {
        return noOfVertices;
    }
}
