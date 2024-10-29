import java.util.*;

public class Graph {

  private List<List<Integer>> adjacencyList;

  // Constructor to initialize the graph with 'n' vertices
  public Graph(int n) {
    adjacencyList = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      adjacencyList.add(new ArrayList<>());
    }
  }

  // Method to add an edge between two vertices (u and v)
  public void addEdge(int u, int v) {
    adjacencyList.get(u).add(v); // Directed graph
    adjacencyList.get(v).add(u); // Uncomment this for an undirected graph
  }

  // BFS Traversal
  public void bfs(int start) {
    boolean[] visited = new boolean[adjacencyList.size()];
    Queue<Integer> queue = new LinkedList<>();

    visited[start] = true;
    queue.add(start);

    while (!queue.isEmpty()) {
      int current = queue.poll();
      System.out.print(current + " ");

      for (int neighbor : adjacencyList.get(current)) {
        if (!visited[neighbor]) {
          visited[neighbor] = true;
          queue.add(neighbor);
        }
      }
    }
  }

  // DFS Traversal
  public void dfs(int node, boolean[] visited) {
    visited[node] = true;
    System.out.print(node + " ");

    for (int neighbor : adjacencyList.get(node)) {
      if (!visited[neighbor]) {
        dfs(neighbor, visited);
      }
    }
  }

  public void dfsStart(int start) {
    boolean[] visited = new boolean[adjacencyList.size()];
    dfs(start, visited);
  }

  public static void main(String[] args) {
    Graph graph = new Graph(5); // Example graph with 5 vertices

    // Adding edges to the graph
    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(1, 3);
    graph.addEdge(2, 3);
    graph.addEdge(3, 4);

    System.out.println("BFS starting from node 0:");
    graph.bfs(0);

    System.out.println("\nDFS starting from node 0:");
    graph.dfsStart(0);
  }
}
