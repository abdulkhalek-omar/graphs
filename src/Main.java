public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge("A", "B", 100);
        graph.addEdge("A", "C", 150);
        graph.addEdge("A", "D", 120);

        graph.addEdge("B", "A", 80);
        graph.addEdge("B", "C", 120);
        graph.addEdge("B", "D", 180);


        graph.getNodes();
        graph.getEdges();
        graph.getWeight("A", "B");
        graph.removeEdge("A", "B");
        graph.getWeight("A", "B");
        graph.getWeight("A", "D");
    }
}