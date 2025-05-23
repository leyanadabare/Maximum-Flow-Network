package maxflow;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Parser class to read graph input from benchmark files
 */
public class Parser {
    public static Graph parseFile(String path) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(path));
        int V = scanner.nextInt(); //Number of vertices
        Graph graph = new Graph(V);

        //Read edges
        while (scanner.hasNextInt()){
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int cap = scanner.nextInt();
            graph.addEdge(from, to, cap);
        }

        scanner.close();
        return graph;
    }
}
