package maxflow;
import java.util.*;
public class Graph {
    private final int V;
    private final List<Edge>[] adj;

    @SuppressWarnings("unchecked")
    public Graph(int V){
        this.V = V;
        adj = new List[V];
        for (int v = 0; v < V; v++){
            adj[v] = new ArrayList<>();
        }
    }

    public void addEdge(Edge e){
        adj[e.from()].add(e);
        adj[e.to()].add(e);
    }

    public Iterable<Edge> adj(int v){
        return adj[v];
    }

    public int V(){
        return V;
    }

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
