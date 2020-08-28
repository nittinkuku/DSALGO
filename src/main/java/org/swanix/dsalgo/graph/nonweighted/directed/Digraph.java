package org.swanix.dsalgo.graph.nonweighted.directed;

import java.util.ArrayList;
import java.util.List;

public class Digraph {
    private final int noOfVertices;
    private int noOfEdges;
    private List<Integer>[] adjacent;

    public Digraph(int noOfVertices) {
        this.noOfVertices = noOfVertices;
        adjacent = (List<Integer>[]) new List[noOfVertices];
        for (int v = 0; v < noOfVertices; v++) {
            adjacent[v] = new ArrayList<>();
        }
    }

    public void addEdge(int v, int w) {
        adjacent[v].add(w);
        noOfEdges++;
    }

    public int vertices() {
        return noOfVertices;
    }

    public int edges() {
        return noOfEdges;
    }

    public List<Integer> adjacent(int v) {
        return adjacent[v];
    }

    public Digraph reverse() {
        //not yet implemented
        return null;
    }
}
