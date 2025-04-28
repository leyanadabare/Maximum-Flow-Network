package maxflow;
import java.util.*;

/**
 * Implements Ford-Fulkerson (Edmonds-Karp) algorithm to find the maximum flow
 */
public class MaxFlowSolver {
    private boolean[] marked; //Marks visited vertices in BFF
    private Edge[] edgeTo; //Stores paths
    private int maxFlow; //Stores the value of the max flow

    /**
     * Constructs a solver and computes max flow between source (s) and sink (t)
     * @param G
     * @param s
     * @param t
     */
    public MaxFlowSolver(Graph G, int s, int t) {
        int pathCount = 1;
        int steps = 0;
        int maxSteps = 1000; //Safety limit

        while (hasAugmentingPath(G, s, t)) {
            if (++steps > maxSteps) {
                System.out.println("⚠️ Aborted after " + maxSteps + " steps");
                break;
            }

            //Find bottleneck
            int bottleneck = Integer.MAX_VALUE;
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                bottleneck = Math.min(bottleneck, edgeTo[v].residualCapacityTo(v));
            }

            //Augment flow along the path
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                edgeTo[v].addResidualFlowTo(v, bottleneck);
            }

            maxFlow += bottleneck;

            //Print detailed step
            System.out.println("=== Augmenting Path " + pathCount + " ===");
            List<Integer> path = new ArrayList<>();
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                path.add(0, v);
            }
            path.add(0, s);
            System.out.println("Path:   " + String.join(" → ", path.stream().map(String::valueOf).toArray(String[]::new)));
            System.out.println("Bottleneck capacity: " + bottleneck + "\n");

            System.out.println("Flow after augmentation:");
            for (Edge e : G.edges()) {
                if (e.capacity() > 0) {
                    System.out.printf("  ➤ Edge %-2d → %-2d | Flow: %3d / %-3d%n", e.from(), e.to(), e.flow(), e.capacity());
                }
            }

            System.out.println("\nResidual Capacities:");
            for (Edge e : G.edges()) {
                if (e.capacity() == 0 && e.flow() != 0) {
                    System.out.printf("  ▹ Residual %-2d → %-2d | Flow: %3d / %d%n", e.from(), e.to(), e.flow(), e.capacity());
                }
            }

            System.out.println();
            pathCount++;
        }
    }

    /**
     * Returns the computed maximum flow value
     * @return maxFlow
     */
    public int getMaxFlow() {
        return maxFlow;
    }

    /**
     * Runs BFF to find and augmenting path
     * @param G
     * @param s
     * @param t
     * @return marked[t]
     */
    private boolean hasAugmentingPath(Graph G, int s, int t) {
        marked = new boolean[G.V()];
        edgeTo = new Edge[G.V()];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        marked[s] = true;

        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (Edge e : G.adj(v)) {
                int w = e.other(v);
                if (!marked[w] && e.residualCapacityTo(w) > 0) {
                    edgeTo[w] = e;
                    marked[w] = true;
                    queue.add(w);
                }
            }
        }

        return marked[t];
    }
}
