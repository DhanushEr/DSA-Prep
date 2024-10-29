import java.util.*;

public class TopologicalSort {

  private List<List<Integer>> adjacencyList;

  // Constructor to initialize the graph with 'n' vertices
  public TopologicalSort(int n) {
    adjacencyList = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      adjacencyList.add(new ArrayList<>());
    }
  }

  // Method to add an edge between two vertices (u and v)
  public void addEdge(int u, int v) {
    adjacencyList.get(u).add(v); // Directed graph
  }

  // Kahn's Algorithm for Topological Sort
  public List<Integer> topologicalSort() {
    int n = adjacencyList.size();
    int[] inDegree = new int[n]; // Array to hold in-degrees of vertices
    List<Integer> topoOrder = new ArrayList<>(); // List to hold the topological order
    Queue<Integer> queue = new LinkedList<>(); // Queue for processing vertices with in-degree 0

    // Calculate in-degrees of all vertices
    for (List<Integer> neighbors : adjacencyList) {
      for (int neighbor : neighbors) {
        inDegree[neighbor]++;
      }
    }

    // Enqueue vertices with in-degree 0
    for (int i = 0; i < n; i++) {
      if (inDegree[i] == 0) {
        queue.offer(i);
      }
    }

    // Process the graph
    while (!queue.isEmpty()) {
      int current = queue.poll(); // Dequeue vertex
      topoOrder.add(current); // Add it to the topological order

      // Decrease the in-degree of neighboring vertices
      for (int neighbor : adjacencyList.get(current)) {
        inDegree[neighbor]--;
        if (inDegree[neighbor] == 0) {
          queue.offer(neighbor); // Enqueue if in-degree becomes 0
        }
      }
    }

    // Check if topological sorting was possible (i.e., if there was a cycle)
    if (topoOrder.size() != n) {
      throw new IllegalArgumentException(
        "Graph is not a DAG. Topological sort not possible."
      );
    }

    return topoOrder;
  }

  public static void main(String[] args) {
    TopologicalSort graph = new TopologicalSort(6); // Example graph with 6 vertices

    // Adding edges to the graph
    graph.addEdge(5, 2);
    graph.addEdge(5, 0);
    graph.addEdge(4, 0);
    graph.addEdge(4, 1);
    graph.addEdge(2, 3);
    graph.addEdge(3, 1);

    // Perform topological sort
    List<Integer> topoOrder = graph.topologicalSort();
    System.out.println("Topological Sort Order: " + topoOrder);
  }
}
/*Topological Sorting is a type of linear ordering of vertices such that
 * if there is edge between U and V, U appears before V in that ordering
 */
