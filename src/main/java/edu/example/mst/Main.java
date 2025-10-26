package edu.example.mst;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String inputPath = "ass_3_input.json"; // input file path
        if (args.length >= 1) inputPath = args[0];
        String outputPath = "ass_3_output.json";
        if (args.length >= 2) outputPath = args[1];

        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> root = mapper.readValue(new File(inputPath),
                new TypeReference<Map<String,Object>>() {});
        @SuppressWarnings("unchecked")
        List<Map<String,Object>> graphs = (List<Map<String,Object>>) root.get("graphs");

        List<ObjectNode> results = new ArrayList<>();
        for (Map<String,Object> g : graphs) {
            GraphInput gi = mapper.convertValue(g, GraphInput.class);
            // run prim and kruskal
            MSTResult prim = PrimMST.run(gi.nodes, gi.edges);
            MSTResult kruskal = KruskalMST.run(gi.nodes, gi.edges);

            ObjectNode graphRes = mapper.createObjectNode();
            graphRes.put("graph_id", gi.id);
            ObjectNode inStats = mapper.createObjectNode();
            inStats.put("vertices", gi.nodes.size());
            inStats.put("edges", gi.edges.size());
            graphRes.set("input_stats", inStats);

            // prim node
            ObjectNode primNode = mapper.createObjectNode();
            primNode.set("mst_edges", mapper.valueToTree(prim.mstEdges));
            primNode.put("total_cost", prim.totalCost);
            primNode.put("operations_count", prim.operationsCount);
            primNode.put("execution_time_ms", round(prim.executionTimeMs));
            graphRes.set("prim", primNode);

            // kruskal node
            ObjectNode krusNode = mapper.createObjectNode();
            krusNode.set("mst_edges", mapper.valueToTree(kruskal.mstEdges));
            krusNode.put("total_cost", kruskal.totalCost);
            krusNode.put("operations_count", kruskal.operationsCount);
            krusNode.put("execution_time_ms", round(kruskal.executionTimeMs));
            graphRes.set("kruskal", krusNode);

            results.add(graphRes);
        }

        ObjectNode outRoot = mapper.createObjectNode();
        outRoot.set("results", mapper.valueToTree(results));
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputPath), outRoot);
        System.out.println("Wrote results to " + outputPath);
    }

    private static double round(double v) {
        return Math.round(v*100.0)/100.0;
    }
}
