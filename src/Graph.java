import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ungerichteter gewichteter Graph
 */
public class Graph {
    private final Map<String, HashMap<String, Integer>> adjacencyMap;

    public Graph() {
        this.adjacencyMap = new HashMap<>();
    }

    // Fügt einen neuen Knoten zum Graphen hinzu.
    public void addNode(String node) {
        adjacencyMap.put(node, new HashMap<>());
    }

    //  Fügt eine Kante zwischen zwei Knoten mit einem bestimmten Gewicht hinzu.
    public void addEdge(String node1, String node2, int weight) {

        if (!adjacencyMap.isEmpty()) {
            for (Map.Entry<String, HashMap<String, Integer>> entry : adjacencyMap.entrySet()) {
                String value = entry.getKey();
                if (value.equals(node1)) {
                    HashMap<String, Integer> edgeWeightMap = adjacencyMap.get(value);
                    edgeWeightMap.put(node2, weight);
                } else {
                    adjacencyMap.put(node1, new HashMap<>(Map.of(node2, weight)));
                    break;
                }
            }
        } else {
            adjacencyMap.put(node1, new HashMap<>(Map.of(node2, weight)));
        }
    }


    // Gibt eine Liste aller Knoten im Graphen zurück.
    public List<String> getNodes() {
        List<String> nodes = new ArrayList<>();
        adjacencyMap.forEach((value, map) -> nodes.add(value));
        return nodes;
    }

    //  Gibt eine Liste aller Kanten im Graphen zurück.
    public List<String> getEdges() {
        List<String> edges = new ArrayList<>();
        adjacencyMap.forEach((value, map) -> map.forEach((mapEdge, mapWeight) -> edges.add(mapEdge)));
        return edges;
    }

    // Gibt eine Liste der Nachbarn eines bestimmten Knotens zurück.
    public List<String> getNeighbors(String node) {
        List<String> neighbors = new ArrayList<>();
        adjacencyMap.forEach((value, map) -> {
            if (value.equals(node)) {
                map.forEach((mapEdge, mapWeight) -> neighbors.add(mapEdge));
            }
        });
        return neighbors;
    }

    // Gibt das Gewicht der Kante zwischen zwei Knoten zurück.
    public int getWeight(String node1, String node2) {
        final int[] weight = {-1};
        adjacencyMap.forEach((value, map) -> {
            if (value.equals(node1)) {
                map.forEach((mapNode, mapWeight) -> {
                    if (mapNode.equals(node2)) {
                        weight[0] = mapWeight;
                    }
                });
            }
        });
        return weight[0];
    }

    // Entfernt einen Knoten aus dem Graphen und alle zugehörigen Kanten.
    public void removeNode(String node) {
        adjacencyMap.remove(node);
    }

    // Entfernt die Kante zwischen zwei Knoten.
    public void removeEdge(String node1, String node2) {
        adjacencyMap.forEach((value, map) -> {
            if (value.equals(node1)) {
                map.forEach((mapNode, mapWeight) -> {
                    if (mapNode.equals(node2)) {
                        map.put(node2, null);
                    }
                });
            }
        });
    }

}
