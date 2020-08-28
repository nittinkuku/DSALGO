package org.swanix.dsalgo.graph.weighted.directed;

import java.util.ArrayList;
import java.util.List;

public class EdgeWeightedDigraph {
    private final int noOfVertices;
    private final List<DirectedEdge>[] adjacent;

    public EdgeWeightedDigraph(int noOfVertices) {
        this.noOfVertices = noOfVertices;
        adjacent = (List<DirectedEdge>[]) new List[noOfVertices];
        for (int v = 0; v < noOfVertices; v++) {
            adjacent[v] = new ArrayList<>();
        }
    }

    public void addEdge(DirectedEdge e) {
        int v = e.from();
        adjacent[v].add(e);
    }

    public List<DirectedEdge> adjacent(int v) {
        return adjacent[v];
    }

    public int vertices() {
        return noOfVertices;
    }
}
