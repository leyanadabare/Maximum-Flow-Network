# Maximum Flow Network

This project implements a solution to the **Maximum Flow Problem** using Java. The program reads a directed flow network from a file, builds an internal data representation, and computes the **maximum flow** from a source node to a sink node using the **Ford-Fulkerson algorithm**.

## ✅ Features

- Reads flow networks from files in a defined format.
- Models directed graphs with integer edge capacities.
- Computes the **maximum flow** using the **Ford-Fulkerson** method with DFS.
- Outputs step-by-step augmenting paths and total maximum flow.
- Includes a clear and modular object-oriented codebase.

## 🧠 Algorithm Overview

The algorithm used is **Ford-Fulkerson**, a greedy method that finds augmenting paths in the residual graph using depth-first search (DFS). The flow is iteratively increased until no more augmenting paths exist.

- **Time Complexity**: O(E * max_flow), where E is the number of edges.
- **Space Complexity**: O(V + E), where V is the number of vertices.




