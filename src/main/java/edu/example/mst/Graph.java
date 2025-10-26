package edu.example.mst;

import java.util.*;

public class Graph {
    public String id;
    public List<String> nodes;
    public List<Edge> edges;
    public Map<String, List<Edge>> adj;

    public Graph(String id, List<String> nodes, List<Edge> edges) {
        this.id = id;
        this.nodes = nodes;
        this.edges = edges;
        buildAdj();
    }

    private void buildAdj() {
        adj = new HashMap<>();
        for (String n : nodes) adj.put(n, new ArrayList<>());
        for (Edge e : edges) {
            adj.get(e.from).add(new Edge(e.from, e.to, e.weight));
            adj.get(e.to).add(new Edge(e.to, e.from, e.weight)); // undirected
        }
    }

    public int vertexCount() { return nodes.size(); }
    public int edgeCount() { return edges.size(); } // each input edge
}
