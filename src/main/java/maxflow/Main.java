package maxflow;

public class Main {
    public static void main(String[] args) {
        try {
            Graph graph = Parser.parseFile("/Users/leyanadaba/IdeaProjects/MaxFlowNetwork/src/main/resources/benchmark1.txt");
            MaxFlowSolver solver = new MaxFlowSolver(graph, 0, graph.V() - 1);
            System.out.println("Max flow: " + solver.getMaxFlow());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
