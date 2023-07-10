
public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge("A", "B", 100);
        graph.addEdge("A", "C", 150);
        graph.addEdge("A", "D", 200);

        graph.getEdges();
        System.out.println("End");
    }
}