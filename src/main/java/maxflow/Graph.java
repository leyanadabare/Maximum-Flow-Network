package maxflow;
import java.util.*;

/**
 * Graph class representing the network with vertices and edges
 */
public class Graph {
    private final int V; //Number of vertices
    private final List<Edge>[] adj; //Number of edges

    @SuppressWarnings("unchecked")
    public Graph(int V){
        this.V = V;
        adj = new List[V];
        for (int v = 0; v < V; v++){
            adj[v] = new ArrayList<>();
        }
    }

    /**
     * Adds a forward and a residual edge between two vertices
     * @param from
     * @param to
     * @param capacity
     */
    public void addEdge(int from, int to, int capacity) {
        Edge forward = new Edge(from, to, capacity);
        Edge backward = new Edge(to, from, 0); // Residual edge with 0 initial capacity

        // Link both edges together
        forward.setResidual(backward);
        backward.setResidual(forward);

        adj[from].add(forward);
        adj[to].add(backward);
    }

    /**
     * Removes an edge from the graph (only removes the forward edge).
     * @param from Source vertex
     * @param to Destination vertex
     * @return True if removed successfully, false otherwise
     */
    public boolean removeEdge(int from, int to) {
        Iterator<Edge> it = adj[from].iterator();
        while (it.hasNext()) {
            Edge e = it.next();
            if (e.from() == from && e.to() == to && e.capacity() > 0) {
                it.remove(); // Remove only the forward edge
                return true;
            }
        }
        return false;
    }

    /**
     * Finds and returns an edge between two vertices (if it exists).
     * @param from Source vertex
     * @param to Destination vertex
     * @return The Edge object if found, else null
     */
    public Edge findEdge(int from, int to) {
        for (Edge e : adj[from]) {
            if (e.from() == from && e.to() == to && e.capacity() > 0) {
                return e;
            }
        }
        return null;
    }

    /**
     * Returns all edges to a vertex
     * @param v
     * @return adj[v]
     */
    public Iterable<Edge> adj(int v){
        return adj[v];
    }

    /**
     * Returns the total number of vertices
     * @return V
     */
    public int V(){
        return V;
    }

    /**
     * Returns all original edges in the graph
     * Excluding residual edges
     * @return edges
     */
    public Iterable<Edge> edges(){
        Set<Edge> edges = new HashSet<>();
        for (int v = 0; v < V; v++){
            for (Edge e : adj [v]){
                if (e.from() == v) edges.add(e);
            }
        }
        return edges;
    }

}
