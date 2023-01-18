package WorkDistribution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkDistribution {
    public static FlowNetwork graph;
    public static List<Vertex> vertexList;
    public static Object[][] input;
    public static final double capacity = 1;
    public static Object[][] outputWorkAssignmentMatrix;
    public static Object[] outputWorkAssignment;
    public static double maxMatching;
    public static FordFulkerson fordFulkerson;
    public static int numOfVertices;

    public void getWorkDistribution(Object[][] inputGraph) {

        // input = new Object[][] { { 0, 1, 0, 0, 1, 0, 1, 1, 0, 1 },
        // { 1, 0, 0, 1, 0, 0, 1, 1, 0, 0 },
        // { 1, 0, 1, 0, 1, 0, 0, 0, 0, 0 },
        // { 0, 1, 0, 0, 1, 1, 0, 0, 0, 0 },
        // { 1, 0, 0, 0, 1, 0, 0, 0, 0, 0 },
        // { 0, 1, 0, 0, 1, 0, 0, 0, 0, 1 },
        // { 1, 1, 1, 0, 0, 0, 0, 1, 1, 0 },
        // { 0, 0, 0, 1, 1, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 1, 1, 1, 0 },
        // { 1, 0, 0, 0, 0, 0, 0, 0, 1, 0 } };

        input = new Object[inputGraph.length][inputGraph.length];

        for (int i = 0; i < inputGraph.length; i++) {
            for (int j = 0; j < inputGraph.length; j++) {
                input[i][j] = Integer.valueOf((String) inputGraph[i][j]);
            }
        }
        numOfVertices = (input.length * 2) + 2;

        // creates a new FlowNetwork object called graph with a specified number of
        // vertices (input.length + 3)
        graph = new FlowNetwork(numOfVertices);
        // creates a new ArrayList of Vertex objects called vertexList, which will be
        // used to store and manage the vertices in the graph
        vertexList = new ArrayList<>();

        addVertexToVertexList();

        addEdgesToSourceAndSink();

        addEdgesToWorkerAndProjects();

        /**
         * 
         * This line creates a new object of the FordFulkerson class, which is used to
         * find the maximum flow in a graph.
         * 
         * @param graph                            - the graph represented as an
         *                                         adjacency matrix
         * @param vertexList.get(0)                - the source vertex
         * @param vertexList.get(vertexList.size() - 1) - the sink vertex The
         *                                         FordFulkerson algorithm will find the
         *                                         maximum flow in the graph from the
         *                                         source vertex to the sink vertex.
         */

        fordFulkerson = new FordFulkerson(graph, vertexList.get(0), vertexList.get(numOfVertices - 1));

        createWorkAssignmentMatrix();

        createWorkAssignment();

        createMaxMatching();

        removeLabelsOfOutputMatrix();

    }

    private void removeLabelsOfOutputMatrix() {
        // remove the first row
        outputWorkAssignmentMatrix = Arrays.copyOfRange(outputWorkAssignmentMatrix, 1,
                outputWorkAssignmentMatrix.length);

        // remove the first element of each following row
        for (int i = 0; i < outputWorkAssignmentMatrix.length; i++) {
            outputWorkAssignmentMatrix[i] = Arrays.copyOfRange(outputWorkAssignmentMatrix[i], 1,
                    outputWorkAssignmentMatrix[i].length);
        }

        // print the modified array
        for (int i = 0; i < outputWorkAssignmentMatrix.length; i++) {
            for (int j = 0; j < outputWorkAssignmentMatrix[i].length; j++) {
                System.out.print(outputWorkAssignmentMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void addVertexToVertexList() {
        // creates a new Vertex object with an id of 0 and a label of "s" and adds it to
        // the vertexList. Source Vertex
        vertexList.add(new Vertex(0, "s"));

        // creates a new Vertex object with an id from 1 to numOfVertices - 1 and a
        // label
        // from "A" to numOfVertices - 1 and adds it to the vertexList.
        for (int i = 1; i < numOfVertices - 1; i++) {

            vertexList.add(new Vertex(i, Character.toString((char) i + 64)));
        }

        // creates a new Vertex object with an id of numOfVertices - 1 and a label of
        // "t"
        // and adds it to the vertexList. Sink Vertex
        vertexList.add(new Vertex(numOfVertices - 1, "t"));

    }

    private static void addEdgesToSourceAndSink() {

        // creates edges between the source node and Worker nodes in the graph
        for (int i = 0; i < (numOfVertices / 2) - 1; i++) {
            // for each iteration of the loop, it creates a new Edge object with a capacity
            // of 1 and adds it to the graph.

            graph.addEdge(new Edge(vertexList.get(0), vertexList.get(i + 1), capacity));
        }

        // creates edges between the Worker node and the sink in the graph
        for (int i = (numOfVertices / 2); i < numOfVertices - 1; i++) {
            // for each iteration of the loop, it creates a new Edge object with a capacity
            // of 1 and adds it to the graph.
            graph.addEdge(new Edge(vertexList.get(i), vertexList.get((numOfVertices - 1)), capacity));

        }

    }

    private static void addEdgesToWorkerAndProjects() {
        // Create Edges between Worker and projects
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                if (!input[i][j].equals(0)) {

                    graph.addEdge(new Edge(vertexList.get(i + 1), vertexList.get(j + (numOfVertices / 2)), capacity));

                }
            }

        }

    }

    private static void createWorkAssignmentMatrix() {
        // Create An object Matrix and fill it with 0
        outputWorkAssignmentMatrix = new Object[numOfVertices + 1][numOfVertices + 1];

        for (int i = 0; i < numOfVertices + 1; i++) {
            for (int j = 0; j < numOfVertices + 1; j++) {
                outputWorkAssignmentMatrix[i][j] = 0;
            }
        }

        // Get Adjacentlist and Fill outputWorkAssignmentMatrix with capacity
        for (Vertex v : vertexList) {
            for (int i = 0; i < graph.getAdjacenciesList(v).size(); i++) {

                outputWorkAssignmentMatrix[graph.getAdjacenciesList(v).get(i).getFromVertex().getId() + 1][graph
                        .getAdjacenciesList(v).get(i).getTargetVertex().getId()
                        + 1] = (int) graph.getAdjacenciesList(v).get(i).getFlow();
            }
        }

        char[] myStrArr = new char[vertexList.size() + 1];
        outputWorkAssignmentMatrix[0][0] = " ";

        myStrArr[0] = 's'; // source
        myStrArr[vertexList.size() - 1] = 't'; // sink
        // A B C D E F G H I ...
        for (int i = 1; i < vertexList.size() - 1; i++) {
            myStrArr[i] = (char) (i + 64);
        }

        for (int i = 0; i < vertexList.size() + 1; i++) {
            if (i < vertexList.size()) {
                outputWorkAssignmentMatrix[i + 1][0] = myStrArr[i]; // Insert in first Row A B C...
            }
            for (int j = 0; j < vertexList.size() + 1; j++) {
                if (j < vertexList.size()) {
                    outputWorkAssignmentMatrix[0][j + 1] = myStrArr[j]; // Insert in first Column A B C...
                }
            }
        }

        for (int i = 0; i < outputWorkAssignmentMatrix.length; i++) {
            for (int j = 0; j < outputWorkAssignmentMatrix.length; j++) {
                System.out.print(outputWorkAssignmentMatrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

    private static void createWorkAssignment() {
        outputWorkAssignment = new Object[(outputWorkAssignmentMatrix.length - 1)
                / 2];

        int resultMatrixLen = ((outputWorkAssignmentMatrix.length - 1) / 2) - 1;
        int matrixRow = 0;
        int matrixCol = 0;
        int k = 1;
        outputWorkAssignment[0] = "The Maximum Matching is: " + maxMatching;

        for (int i = 0; i < resultMatrixLen; i++) {
            matrixRow = i + 2;
            for (int j = 0; j < resultMatrixLen; j++) {
                matrixCol = j + resultMatrixLen + 2;

                if (!outputWorkAssignmentMatrix[matrixRow][matrixCol].equals(0)) {
                    System.out.print((char) (i + 65) + " --> " + (char) (j + 65 + resultMatrixLen));
                    outputWorkAssignment[k] = (char) (i + 65) + " --> " + (char) (j + 72);
                    k++;
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void createMaxMatching() {
        maxMatching = fordFulkerson.getMaxFlow();
        System.out.println("The Maximum Matching is: " + maxMatching);

    }

}
