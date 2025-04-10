package maxflow;

public class Edge {
    private final int from, to, capacity;
    private int flow;

    public Edge(int from, int to, int capacity){
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = flow;
    }

    public int from(){
        return from;
    }
    public int to(){
        return to;
    }
    public int other(int vertex){
        if (vertex == from)
            return to;
        else if (vertex == to) return from;
        throw new IllegalArgumentException("Invalid Vertex");
    }

    public int capacity(){
        return capacity;
    }
    public int flow(){
        return flow;
    }
    public int residualCapacityTo(int vertex){
        if (vertex == to) return capacity - flow;
        else if (vertex == from) return flow;
        else throw new IllegalArgumentException();
    }

    public void addResidualFlowTo(int vertex, int delta){
        if (vertex == to) flow += delta;
        else if (vertex == from) flow -= delta;
        else throw new IllegalArgumentException();
    }

    @Override
    public String toString(){
        return String.format("%d -> %d (%d/%d)", from, to, flow, capacity);
    }
}
