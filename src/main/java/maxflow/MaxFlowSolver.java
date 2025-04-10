package maxflow;

import java.util.*;

public class MaxFlowSolver {
    private boolean[] marked;
    private Edge[] edgeTo;
    private int maxFlow;

    public MaxFlowSolver(Graph G, int s, int t) {
        while (hasAugmentingPath(G, s, t)) {
            int bottleneck = Integer.MAX_VALUE;
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                bottleneck = Math.min(bottleneck, edgeTo[v].residualCapacityTo(v));
            }

            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                edgeTo[v].addResidualFlowTo(v, bottleneck);
            }

            maxFlow += bottleneck;
            System.out.println("Augmenting path flow = " + bottleneck);
        }
    }

    public int getMaxFlow() {
        return maxFlow;
    }

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
