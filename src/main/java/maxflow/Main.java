package maxflow;

/**
 * Main class to run the Max FLow Solver
 */
public class Main {
    public static void main(String[] args) {
        String[] benchmarks = {
                //List of benchmark files to test
                //"bridge_1.txt",
//                "bridge_5.txt",
//                "ladder_1.txt",
//                "ladder_10.txt",
                "ladder_20.txt"
        };

        //Processing each benchmark
        for (String filename : benchmarks) {
            try {
                System.out.println("Running benchmark: " + filename);
                Graph graph = Parser.parseFile("src/main/resources/" + filename);
                MaxFlowSolver solver = new MaxFlowSolver(graph, 0, graph.V() - 1);
                System.out.println("Max flow: " + solver.getMaxFlow());
                System.out.println("=================================\n");
            } catch (Exception e) {
                System.err.println("Error processing file: " + filename);
                e.printStackTrace();
            }
        }
    }
}
