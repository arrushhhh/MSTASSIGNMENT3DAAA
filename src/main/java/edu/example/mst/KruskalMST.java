package edu.example.mst;

import java.util.*;

public class KruskalMST {
    public static MSTResult run(List<String> nodes, List<Edge> edges) {
        int n = nodes.size();
        Map<String,Integer> idx = new HashMap<>();
        for (int i=0;i<n;i++) idx.put(nodes.get(i), i);

        // sort edges by weight
        List<Edge> sorted = new ArrayList<>(edges);
        long start = System.nanoTime();
        // count comparisons using wrapper: Java sort uses comparisons internally; we'll approximate by counting during sort comparator calls
        final long[] compareCount = {0};
        sorted.sort((e1, e2) -> {
            compareCount[0]++; // count comparator call
            return Integer.compare(e1.weight, e2.weight);
        });

        UnionFind uf = new UnionFind(n);
        List<Edge> mst = new ArrayList<>();
        int totalCost = 0;
        long ops = compareCount[0];

        for (Edge e : sorted) {
            int u = idx.get(e.from), v = idx.get(e.to);
            // union-find union call will increment uf.operations
            if (uf.union(u, v)) {
                mst.add(e);
                totalCost += e.weight;
            }
            if (mst.size() == n-1) break;
        }

        long end = System.nanoTime();

        MSTResult res = new MSTResult();
        res.vertices = n;
        res.edges = edges.size();
        res.mstEdges = mst;
        res.totalCost = totalCost;
        res.operationsCount = ops + uf.operations;
        res.executionTimeMs = (end - start)/1_000_000.0;
        return res;
    }
}
