package maxflow;

/**
 *Edge class representing a direct connection between two vertices
 * Supports flow augmentation and tracking for residual capacities
 */
public class Edge {
    private final int from, to, capacity;
    private int flow;
    private Edge residual; //Pointer to residual edge

    public Edge(int from, int to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
    }

    /**
     * Sets the corresponding residual edge
     * @param residual
     */
    public void setResidual(Edge residual) {
        this.residual = residual;
    }

    public int from() {
        return from;
    }

    public int to() {
        return to;
    }

    /**
     * Returns the other vertex connected by this edge
     * @param vertex
     * @return capacity, flow
     */
    public int other(int vertex) {
        if (vertex == from) return to;
        else if (vertex == to) return from;
        throw new IllegalArgumentException("Invalid Vertex");
    }

    public int capacity() {
        return capacity;
    }

    public int flow() {
        return flow;
    }

    /**
     * Return the residual capacity towards the given vertex
     * @param vertex
     * @return
     */
    public int residualCapacityTo(int vertex) {
        if (vertex == to) return capacity - flow; //Forward edge
        else if (vertex == from) return flow; //Backward edge
        else throw new IllegalArgumentException();
    }

    /**
     * Adds residual flow towards the given vertex
     * @param vertex
     * @param delta
     */
    public void addResidualFlowTo(int vertex, int delta) {
        if (vertex == to) {
            flow += delta;
            residual.flow -= delta;
        } else if (vertex == from) {
            flow -= delta;
            residual.flow += delta;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return String.format("%d -> %d (%d/%d)", from, to, flow, capacity);
    }
}
