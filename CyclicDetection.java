import java.util.*;

public class CyclicDetection {

  private List<List<Integer>> adjacencyList;

  // Constructor to initialize the graph with 'n' vertices
  public CyclicDetection(int n) {
    adjacencyList = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      adjacencyList.add(new ArrayList<>());
    }
  }

  // Method to add an edge between two vertices (u -> v)
  public void addEdge(int u, int v) {
    adjacencyList.get(u).add(v); // Directed graph
  }

  // Kahn's Algorithm to detect cycle
  public boolean hasCycle() {
    int n = adjacencyList.size();
    int[] inDegree = new int[n]; // Array to hold in-degrees of vertices
    Queue<Integer> queue = new LinkedList<>(); // Queue for vertices with in-degree 0
    int count = 0; // Count of vertices in topological order

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

    // Process vertices in topological order
    while (!queue.isEmpty()) {
      int current = queue.poll();
      count++; // Increment count for each vertex added to the topological order

      // Reduce the in-degree of neighboring vertices
      for (int neighbor : adjacencyList.get(current)) {
        inDegree[neighbor]--;
        if (inDegree[neighbor] == 0) {
          queue.offer(neighbor);
        }
      }
    }

    // If count is not equal to the number of vertices, there is a cycle
    return count != n;
  }

  public static void main(String[] args) {
    CyclicDetection graph = new CyclicDetection(4); // Example graph with 4 vertices

    // Adding edges to form a cycle: 0 -> 1 -> 2 -> 3 -> 1
    graph.addEdge(0, 1);
    graph.addEdge(1, 2);
    graph.addEdge(2, 3);
    graph.addEdge(3, 1);

    if (graph.hasCycle()) {
      System.out.println("The graph has a cycle.");
    } else {
      System.out.println("The graph does not have a cycle.");
    }
  }
}
