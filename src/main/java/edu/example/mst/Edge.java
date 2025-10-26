package edu.example.mst;

public class Edge {
    public final String from;
    public final String to;
    public final int weight;

    public Edge() { this.from = this.to = null; this.weight = 0; } // for Jackson
    public Edge(String f, String t, int w) { this.from = f; this.to = t; this.weight = w; }

    public String other(String node) {
        return node.equals(from) ? to : from;
    }
}
