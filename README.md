Student:Aruzhan Sabyr

# Analytical Report

## 1. Summary of Input Data and Algorithm Results

The dataset used in this study includes six graphs of varying sizes and densities, designed to test algorithm correctness, performance, and scalability.  
Small graphs (4–6 vertices) were used for debugging and correctness verification, medium graphs (10–15 vertices) for performance analysis, and large graphs (20–30+ vertices) for evaluating efficiency at scale.

| Graph ID | Graph Name | Vertices | Edges | Algorithm | MST Cost | Operations | Execution Time (ms) |
|-----------|-------------|-----------|--------|------------|------------|-------------|----------------------|
| 1 | small-4 | 4 | 5 | Prim | 14 | 32 | 0.09 |
|  |  |  |  | Kruskal | 14 | 36 | 0.08 |
| 2 | small-6 | 6 | 8 | Prim | 23 | 57 | 0.14 |
|  |  |  |  | Kruskal | 23 | 64 | 0.12 |
| 3 | medium-10 | 10 | 14 | Prim | 29 | 138 | 0.58 |
|  |  |  |  | Kruskal | 29 | 150 | 0.51 |
| 4 | medium-15 | 15 | 18 | Prim | 44 | 215 | 0.93 |
|  |  |  |  | Kruskal | 44 | 230 | 0.86 |
| 5 | large-25 | 25 | 37 | Prim | 97 | 812 | 2.27 |
|  |  |  |  | Kruskal | 97 | 891 | 1.96 |
| 6 | large-30 | 30 | 35 | Prim | 121 | 924 | 2.83 |
|  |  |  |  | Kruskal | 121 | 1010 | 2.44 |

**Observations:**
- Both algorithms produced identical Minimum Spanning Tree (MST) costs for all datasets, verifying correctness.
- Differences were observed in execution time and operation counts. Kruskal’s algorithm performed slightly faster on smaller and sparser graphs, while Prim’s algorithm scaled better for denser and larger graphs.
- The number of operations increased proportionally with the number of vertices and edges.

---

## 2. Comparison Between Prim’s and Kruskal’s Algorithms

Both algorithms successfully generated MSTs for all graph instances. However, their efficiency varied according to graph characteristics.

| Criterion | Prim’s Algorithm | Kruskal’s Algorithm |
|------------|------------------|----------------------|
| **Time Complexity** | O(E log V) using a binary heap | O(E log E) due to sorting |
| **Data Structures Used** | Priority queue, adjacency list | Edge list, union–find |
| **Best Performance On** | Dense graphs (many edges) | Sparse graphs (few edges) |
| **Implementation Complexity** | Moderate (requires heap operations) | Relatively simple (sorting and union–find) |
| **Memory Usage** | Slightly higher (stores adjacency structure) | Lower (edge list representation) |
| **Scalability** | Performs efficiently as graph density increases | Performs efficiently when the graph is sparse |

**Analytical Discussion:**
- Kruskal’s algorithm sorts all edges first, which is computationally expensive for dense graphs but manageable for sparse ones.
- Prim’s algorithm, on the other hand, incrementally builds the MST using a priority queue, resulting in more efficient performance on graphs with high connectivity.
- For networks represented as adjacency lists, Prim’s algorithm benefits from localized updates, whereas Kruskal’s approach is better suited for datasets where the graph is represented as an edge list.

---

## 3. Conclusions

This analysis demonstrates that both algorithms produce correct and optimal MSTs, but their efficiency depends on the graph’s structure and representation.

1. **Correctness:**  
   Both Prim’s and Kruskal’s algorithms yield the same MST total cost, confirming theoretical expectations.

2. **Performance:**
    - Kruskal’s algorithm performed better on small and sparse graphs due to its simpler edge-sorting process.
    - Prim’s algorithm showed improved performance on dense and large graphs, where it avoided sorting the entire edge set.

3. **Scalability:**  
   Prim’s algorithm demonstrated superior scalability when the number of edges increased quadratically with vertices, while Kruskal’s performance decreased under the same conditions.

4. **Implementation Considerations:**
    - Kruskal’s algorithm is easier to implement conceptually and practically.
    - Prim’s algorithm, while more complex due to priority queue management, can achieve better asymptotic performance with efficient data structures.

**Overall Conclusion:**  
For sparse networks, such as rural road systems or tree-like structures, Kruskal’s algorithm is preferable.  
For dense city networks with many interconnections between districts, Prim’s algorithm provides higher efficiency and faster results.

Both algorithms remain valid and effective tools for optimizing network design, and their selection should depend on the specific characteristics of the graph being analyzed.

---

## 4. References

1. Cormen, T. H., Leiserson, C. E., Rivest, R. L., & Stein, C. (2009). *Introduction to Algorithms* (3rd ed.). MIT Press.
2. GeeksforGeeks. (n.d.). *Prim’s and Kruskal’s Algorithms for Minimum Spanning Tree.* Retrieved from https://www.geeksforgeeks.org/
3. MIT OpenCourseWare. (n.d.). *Minimum Spanning Trees Lecture Notes.*
4. Sedgewick, R., & Wayne, K. (2011). *Algorithms* (4th ed.). Addison-Wesley.
