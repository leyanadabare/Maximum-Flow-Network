package maxflow;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Parser {
    public static Graph parseFile(String path) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(path));
        int V = scanner.nextInt();
        Graph graph = new Graph(V);

        while (scanner.hasNextInt()){
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int cap = scanner.nextInt();
            graph.addEdge(new Edge(from, to, cap));
        }

        scanner.close();
        return graph;
    }
}
