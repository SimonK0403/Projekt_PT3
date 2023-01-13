package Postman;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Postman {
    public static Object[][] eulerianMatrix;
    public static List<Integer> result;
    public static Object[][] output;

    /**
     * Check if the input array is a square matrix and symmetric matrix indicating
     * that it is an undirected graph
     * 
     * @param input - 2D array representing the graph
     * @return true if the input is an undirected graph, false otherwise
     */
    public static boolean isUndirectedGraph(Object[][] input) {
        int n = input.length;

        // Check that the input array is a square matrix
        for (int i = 0; i < n; i++) {
            if (input[i].length != n) {
                return false;
            }
        }

        // Check that the input array is a symmetric matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!input[i][j].equals(input[j][i])) {
                    return false;
                }
            }
        }
        return true; // the input is an undirected graph
    }

    /**
     * Check if the input graph is an undirected graph and all its vertices have
     * even degree indicating that it has an Eulerian cycle
     * 
     * @param input - 2D array representing the graph
     * @return true if the input has an Eulerian cycle, false otherwise
     */
    public static boolean isEulerianCycle(Object[][] input) {
        int n = input.length;

        // Check that the input is an undirected graph
        if (!isUndirectedGraph(input)) {
            return false;
        }

        // Check that all vertices have even degree
        for (int i = 0; i < n; i++) {
            int degree = 0;
            for (int j = 0; j < n; j++) {
                if (input[i][j].equals(1)) {
                    degree++;
                }
            }
            if (degree % 2 != 0) {
                return false;
            }
        }
        return true; // the input has an Eulerian cycle
    }

    /**
     * Check if the input graph is an undirected graph and all its vertices are
     * reachable from the first vertex
     * 
     * @param input - 2D array representing the graph
     * @return true if the vertices are connected, false otherwise
     */
    public static boolean isConnected(Object[][] input) {
        int n = input.length;

        // Check that the input is an undirected graph
        if (!isUndirectedGraph(input)) {
            return false;
        }

        // Check that all vertices are reachable from the first vertex
        boolean[] visited = new boolean[n];
        dfs(input, visited, 0);
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true; // the vertices are connected
    }

    /**
     * Depth first search traversal of the graph
     * 
     * @param input   - 2D array representing the graph
     * @param visited - boolean array to keep track of visited vertices
     * @param u       - current vertex
     */
    private static void dfs(Object[][] input, boolean[] visited, int u) {

        visited[u] = true;
        for (int v = 0; v < input.length; v++) {
            if (input[u][v].equals(1) && !visited[v]) {
                dfs(input, visited, v);
            }
        }
    }

    /**
     * Find an Eulerian cycle in the input graph, starting at a given vertex using
     * Hierholzer algorithm
     * 
     * @param input - 2D array representing the graph
     * @param start - starting vertex
     * @return list of vertices in the order of the cycle null if the input is not a
     *         valid undirected graph with an Eulerian cycle or the starting vertex
     *         is invalid
     */
    public static List<Integer> findEulerianCycle(Object[][] input, int start) {
        int n = input.length;

        // Check that the input is an undirected graph with an Eulerian cycle
        if (!isUndirectedGraph(input) || !isEulerianCycle(input)) {
            return null;
        }

        // Check that the start vertex is valid
        if (start < 0 || start >= n) {
            return null;
        }

        // Initialize the stack and the result list
        Stack<Integer> stack = new Stack<>();
        result = new ArrayList<>();

        // Start the Hierholzer algorithm at the start vertex
        stack.push(start);

        while (!stack.isEmpty()) {
            int v = stack.peek();
            boolean found = false;
            for (int u = 0; u < n; u++) {
                if (input[v][u].equals(1)) {
                    // Remove the edge (v, u) from the input
                    input[v][u] = input[u][v] = 0;
                    // Push the next vertex onto the stack
                    stack.push(u);
                    found = true;
                    break;
                }
            }
            if (!found) {
                // Add the current vertex to the result list
                result.add(stack.pop());
            }
        }
        return result;
    }

    /**
     * Convert the list of vertices in the order of the cycle to a matrix
     * 
     * @param cycle - list of vertices in the order of the cycle
     * @param input - 2D array representing the graph
     * @return the matrix representation of the cycle
     */

    public static Object[][] toMatrix(List<Integer> cycle, Object[][] input) {
        int n = input.length;
        output = new Object[n + 1][cycle.size() + 1];
        output[0][0] = " ";
        for (int i = 1; i < cycle.size() + 1; i++) {
            for (int j = 1; j <= n; j++) {
                output[j][i] = 0;
            }
        }

        for (int i = 1; i < cycle.size() + 1; i++) {
            output[0][i] = i;
        }

        for (int i = 1; i <= n; i++) {

            output[i][0] = (char) (i + 64);
        }

        for (int i = 0; i < cycle.size(); i++) {
            output[cycle.get(i) + 1][i + 1] = 1;
        }

        return output;
    }

    private static void printMatrix(Object[][] output2) {
        for (Object[] row : output2) {
            for (Object element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Convert the list of vertices in the order of the cycle to a string
     * 
     * @param cycle - list of vertices in the order of the cycle
     * @return the string representation of the cycle
     */
    public static String toString(List<Integer> cycle) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cycle.size(); i++) {
            sb.append((char) (cycle.get(i) + 65)).append("-> ");
        }
        sb.setLength(sb.length() - 3);
        return sb.toString();
    }

    public void getPostman(Object[][] inputGraph) {
        // Object[][] isEulerian = new Object[][] { { 0, 1, 0, 0, 1 }, { 1, 0, 1, 0, 0
        // }, { 0, 1, 0, 1, 0 },
        // { 0, 0, 1, 0, 1 }, { 1, 0, 0, 1, 0 } };
        // Object[][] notUndirected = new Object[][] { { 0, 1, 0, 0, 1 }, { 1, 0, 1, 0,
        // 0 }, { 0, 1, 0, 1, 0 },
        // { 0, 0, 1, 0, 0 }, { 1, 0, 0, 1, 0 } };
        // Object[][] notConected = new Object[][] { { 0, 1, 0, 0, 0 }, { 1, 0, 1, 0, 0
        // }, { 0, 1, 0, 1, 0 },
        // { 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0 } };
        // Object[][] notEulerian = new Object[][] { { 0, 1, 0, 0, 0 }, { 1, 0, 1, 0, 0
        // }, { 0, 1, 0, 1, 0 },
        // { 0, 0, 1, 0, 1 }, { 0, 0, 0, 1, 0 } };
        Object[][] input = new Object[inputGraph.length][inputGraph.length];
        for (int i = 0; i < inputGraph.length; i++) {
            for (int j = 0; j < inputGraph.length; j++) {
                input[i][j] = Integer.valueOf((String) inputGraph[i][j]);
            }
        }

        // Object[][] input = notUndirected;
        // Object[][] input = notConected;
        // Object[][] input = notEulerian;

        int startVertex = 0;
        System.out.println("The given Graph is:");
        if (isUndirectedGraph(input)) {
            System.out.println("+ The given Graph is an Undirected Graph");
            if (isConnected(input)) {
                System.out.println("+ is conected");
                if (isEulerianCycle(input)) {
                    System.out.println("+ is eulerian\n");
                    List<Integer> eulerianCycle = findEulerianCycle(input, startVertex);

                    eulerianMatrix = toMatrix(eulerianCycle, input);

                    printMatrix(eulerianMatrix);

                    System.out.println(toString(eulerianCycle));

                } else {
                    System.out.println("-  Not an Eulerian Cycle Graph");
                }
            } else {
                System.out.println("- Not a Connected Graph");
            }
        } else {
            System.out.println("- Not an Undirected Graph");
        }

    }

}
