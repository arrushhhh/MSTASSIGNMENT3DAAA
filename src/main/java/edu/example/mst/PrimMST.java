package edu.example.mst;

import java.util.*;

public class PrimMST {
    public static class Counter { public long ops = 0; }

    public static MSTResult run(List<String> nodes, List<Edge> edges) {
        int n = nodes.size();
        Map<String, Integer> idx = new HashMap<>();
        for (int i = 0; i < n; i++) idx.put(nodes.get(i), i);

        // adjacency
        List<List<Edge>> adj = new ArrayList<>();
        for (int i=0;i<n;i++) adj.add(new ArrayList<>());
        for (Edge e : edges) {
            int u = idx.get(e.from), v = idx.get(e.to);
            adj.get(u).add(e);
            adj.get(v).add(e);
        }

        long start = System.nanoTime();
        Counter counter = new Counter();

        boolean[] visited = new boolean[n];
        PriorityQueue<PrimEntry> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.weight));
        // start at node 0
        visited[0] = true;
        for (Edge e : adj.get(0)) {
            counter.ops++; // enqueue considered
            pq.add(new PrimEntry(e, e.weight));
        }

        List<Edge> mst = new ArrayList<>();
        int totalCost = 0;
        while (!pq.isEmpty() && mst.size() < n-1) {
            PrimEntry pe = pq.poll(); counter.ops++; // poll counted
            Edge e = pe.edge;
            String a = e.from, b = e.to;
            int ai = idx.get(a), bi = idx.get(b);
            String next = visited[ai] ? b : a;
            int nextIdx = idx.get(next);
            if (visited[nextIdx]) continue;
            // pick edge
            visited[nextIdx] = true;
            mst.add(e);
            totalCost += e.weight;
            // add adjacent edges
            for (Edge ne : adj.get(nextIdx)) {
                counter.ops++; // pushing candidate
                String u = ne.from, v = ne.to;
                int uidx = idx.get(u), vidx = idx.get(v);
                String other = uidx == nextIdx ? v : u;
                if (!visited[idx.get(other)]) pq.add(new PrimEntry(ne, ne.weight));
            }
        }

        long end = System.nanoTime();
        MSTResult res = new MSTResult();
        res.vertices = n;
        res.edges = edges.size();
        res.mstEdges = mst;
        res.totalCost = totalCost;
        res.operationsCount = counter.ops;
        res.executionTimeMs = (end - start)/1_000_000.0;
        return res;
    }

    private static class PrimEntry {
        Edge edge;
        int weight;
        PrimEntry(Edge e, int w) { this.edge = e; this.weight = w; }
    }
}
