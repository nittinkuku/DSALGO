package org.swanix.dsalgo.graph.nonweighted.undirected;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final int noOfVertices;
    private int noOfEdges;
    private List<Integer>[] adjacent;

    public Graph(int noOfVertices) {
        this.noOfVertices = noOfVertices;
        adjacent = (List<Integer>[]) new List[noOfVertices];
        for (int v = 0; v < noOfVertices; v++) {
            adjacent[v] = new ArrayList<>();
        }
    }

    public void addEdge(int v, int w) {
        adjacent[v].add(w);
        adjacent[w].add(v);
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

    public static int degree(Graph graph, int v) {
        return graph.adjacent(v).size();
    }

    public static int maxDegree(Graph graph) {
        int max = 0;
        for (int v = 0; v < graph.vertices(); v++) {
            if (degree(graph, v) > max) {
                max = degree(graph, v);
            }
        }
        return max;
    }

    public static double averageDegree(Graph graph) {
        return 2.0 * graph.edges() / graph.vertices();
    }

    public static int numberOfSelfLoops(Graph graph) {
        int count = 0;
        for (int v = 0; v < graph.vertices(); v++) {
            for (int w : graph.adjacent(v)) {
                if (v == w) {
                    count++;
                }
            }
        }
        return count / 2;
    }
}
