import java.util.*;

public class LogistikGrafik {
    private final int vertices;
    private final int[][] adjMatrix;
    private final String[] gudang;

    public LogistikGrafik(String[] gudang) {
        this.vertices = gudang.length;
        this.gudang = gudang;
        this.adjMatrix = new int[vertices][vertices];
    }

    public void addEdge(int src, int dest) {
        adjMatrix[src][dest] = 1; // directed graph
    }

    public void printAdjacencyMatrix() {
        System.out.println("Adjacency Matrix:");
        System.out.print("    ");
        for (String v : gudang) System.out.print(v + " ");
        System.out.println();
        for (int i = 0; i < vertices; i++) {
            System.out.print(gudang[i] + " : ");
            for (int j = 0; j < vertices; j++) {
                System.out.print(adjMatrix[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public void bfs(int start) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.offer(start);

        System.out.print("BFS Traversal: ");
        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(gudang[current] + " ");

            for (int i = 0; i < vertices; i++) {
                if (adjMatrix[current][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.offer(i);
                }
            }
        }
        System.out.println();
    }

    public void dfs(int start) {
        boolean[] visited = new boolean[vertices];
        System.out.print("DFS Traversal: ");
        dfsRecursive(start, visited);
        System.out.println();
    }

    private void dfsRecursive(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(gudang[v] + " ");

        for (int i = 0; i < vertices; i++) {
            if (adjMatrix[v][i] == 1 && !visited[i]) {
                dfsRecursive(i, visited);
            }
        }
    }

    public static void main(String[] args) {
        String[] gudang = {"A ", "B ", "C ", "D ", "E"};
        LogistikGrafik graph = new LogistikGrafik(gudang);

        // Tambahkan edge sesuai soal
        graph.addEdge(0, 1); // A → B
        graph.addEdge(0, 2); // A → C
        graph.addEdge(1, 3); // B → D
        graph.addEdge(2, 3); // C → D
        graph.addEdge(2, 4); // C → E
        graph.addEdge(3, 4); // D → E
        graph.addEdge(4, 0); // E → A

        graph.printAdjacencyMatrix();
        graph.bfs(0); // dari A
        graph.dfs(0); // dari A
    }
}
