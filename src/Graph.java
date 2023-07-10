import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Ungerichteter gewichteter Graph
 */
public class Graph {
    private final Map<String, List<Edge>> graphMap;

    public Graph() {
        graphMap = new HashMap<>();
    }

    // Fügt einen neuen Knoten zum Graphen hinzu.
    public void addNode(String node) {
        if (!graphMap.containsKey(node)) {
            graphMap.put(node, new ArrayList<>());
        }
    }

    //  Fügt eine Kante zwischen zwei Knoten mit einem bestimmten Gewicht hinzu.
    public void addEdge(String node1, String node2, int weight) {
        addNode(node1);
        addNode(node2);

        List<Edge> edges1 = graphMap.get(node1);
        List<Edge> edges2 = graphMap.get(node2);

        edges1.add(new Edge(node2, weight));
        edges2.add(new Edge(node1, weight));
    }


    // Gibt eine Liste aller Knoten im Graphen zurück.
    public List<String> getNodes() {
        return new ArrayList<>(graphMap.keySet());
    }

    //  Gibt eine Liste aller Kanten im Graphen zurück.
    public List<Edge> getEdges() {
        List<Edge> edges = new ArrayList<>();
        for (List<Edge> list : graphMap.values()) {
            edges.addAll(list);
        }
        return edges;
    }

    // Gibt eine Liste der Nachbarn eines bestimmten Knotens zurück.
    public List<String> getNeighbors(String node) {
        List<String> neighbors = new ArrayList<>();
        if (graphMap.containsKey(node)) {
            List<Edge> edges = graphMap.get(node);
            for (Edge edge : edges) {
                neighbors.add(edge.getNode());
            }
        }
        return neighbors;
    }

    // Gibt das Gewicht der Kante zwischen zwei Knoten zurück.
    public int getWeight(String node1, String node2) {
        if (graphMap.containsKey(node1)) {
            List<Edge> edges = graphMap.get(node1);
            for (Edge edge : edges) {
                if (Objects.equals(edge.getNode(), node2)) {
                    return edge.getWeight();
                }
            }
        }
        return -1; // Return -1 if there is no edge between the nodes
    }

    // Entfernt einen Knoten aus dem Graphen und alle zugehörigen Kanten.
    public void removeNode(String node) {
        if (graphMap.containsKey(node)) {
            List<Edge> edges = graphMap.get(node);
            for (Edge edge : edges) {
                String neighborNode = edge.getNode();
                List<Edge> neighborEdges = graphMap.get(neighborNode);
                neighborEdges.removeIf(e -> Objects.equals(e.getNode(), node));
            }
            graphMap.remove(node);
        }
    }

    // Entfernt die Kante zwischen zwei Knoten.
    public void removeEdge(String node1, String node2) {
        if (graphMap.containsKey(node1)) {
            List<Edge> edges = graphMap.get(node1);
            edges.removeIf(edge -> Objects.equals(edge.getNode(), node2));
        }
        if (graphMap.containsKey(node2)) {
            List<Edge> edges = graphMap.get(node2);
            edges.removeIf(edge -> Objects.equals(edge.getNode(), node1));
        }
    }

    private static class Edge {
        String node;
        int weight;

        public Edge(String node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        public String getNode() {
            return node;
        }

        public void setNode(String node) {
            this.node = node;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }

}
