package WaterSupply;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import WorkDistribution.Edge;
import WorkDistribution.FlowNetwork;
import WorkDistribution.FordFulkerson;
import WorkDistribution.Vertex;

public class WaterSupply {
    public static Object[][] input;
    public static FlowNetwork graph;
    public static List<Vertex> vertexList;
    public static FordFulkerson fordFulkerson;
    public static Object[][] outputWaterMatrix;
    public static Object[] maxFlow;

    public void getWaterSupply(Object[][] inputGraph) {
        // This line creates a 2D array of Objects to represent an adjacency matrix of a
        // flow network
        // Object[][] input = new Object[][]
        // { { 0, 15, 6, 12, 0, 0, 0, 0, 0 },
        // { 0, 0,0, 0, 8, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 3, 1, 0 },
        // { 0, 0, 0, 0, 0, 5, 0, 5, 0 },
        // { 0, 0, 0, 0,0, 5, 6, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 0, 0, 10 },
        // { 0, 0, 0, 0, 0, 0, 0, 0, 10 },
        // { 0, 0, 0, 0, 0, 0, 0, 0, 7 },
        // { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

        input = new Object[inputGraph.length][inputGraph.length];

        for (int i = 0; i < inputGraph.length; i++) {
            for (int j = 0; j < inputGraph.length; j++) {
                input[i][j] = Integer.valueOf((String) inputGraph[i][j]);
            }
        }
        // Creates a FlowNetwork object with the number of vertices in the network
        graph = new FlowNetwork(input.length);

        // Creates a list of Vertex objects to represent the vertices in the network
        vertexList = new ArrayList<>();

        addVertexToVertexList();

        addEdgesBetweenVertex();

        // creates a FordFulkerson object and runs the algorithm,
        // the algorithm uses graph object, start vertex is vertexList.get(0), end
        // vertex is vertexList.get(vertexList.size() - 1)
        fordFulkerson = new FordFulkerson(graph, vertexList.get(0),
                vertexList.get(vertexList.size() - 1));

        // creates a 2D array of objects to store the result of the flow in a matrix
        // form
        outputWaterMatrix = new Object[vertexList.size() + 1][vertexList.size() + 1];
        // Fill the matrix with zeros
        for (int i = 0; i < vertexList.size() + 1; i++) {
            for (int j = 0; j < vertexList.size() + 1; j++) {
                outputWaterMatrix[i][j] = 0;
            }
        }

        // iterate over all vertices,
        // get the adjacent list for each vertex
        // and fill the outputWaterMatrix with the edge capacity (edge flow)
        for (Vertex v : vertexList) {
            for (int i = 0; i < graph.getAdjacenciesList(v).size(); i++) {
                outputWaterMatrix[graph.getAdjacenciesList(v).get(i).getFromVertex().getId() + 1][graph
                        .getAdjacenciesList(v)
                        .get(i).getTargetVertex().getId() + 1] = (int) graph.getAdjacenciesList(v).get(i).getFlow();
            }
        }
        outputWaterMatrix[0][0] = " ";
        char[] myStrArr = new char[vertexList.size()];
        // A B C D E F G H I ...
        for (int i = 65; i <= vertexList.size() + 64; i++) {
            myStrArr[i - 65] = (char) i;

        }

        for (int i = 0; i < vertexList.size() + 1; i++) {
            if (i < vertexList.size()) {
                outputWaterMatrix[i + 1][0] = myStrArr[i]; // Insert in first Row A B C...
            }
            for (int j = 0; j < vertexList.size() + 1; j++) {
                if (j < vertexList.size()) {
                    outputWaterMatrix[0][j + 1] = myStrArr[j]; // Insert in first Column A B C...
                }
                System.out.print(outputWaterMatrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        removeLabelsOfOutputMatrix();

        System.out.println("The Maximum Flow is: " + fordFulkerson.getMaxFlow());

        maxFlow = new Object[1];
        maxFlow[0] = "The Maximum Flow is: " + fordFulkerson.getMaxFlow();
        System.out.println(maxFlow[0]);
    }

    private void removeLabelsOfOutputMatrix() {
        // remove the first row
        outputWaterMatrix = Arrays.copyOfRange(outputWaterMatrix, 1, outputWaterMatrix.length);

        // remove the first element of each following row
        for (int i = 0; i < outputWaterMatrix.length; i++) {
            outputWaterMatrix[i] = Arrays.copyOfRange(outputWaterMatrix[i], 1, outputWaterMatrix[i].length);
        }

        // print the modified array
        for (int i = 0; i < outputWaterMatrix.length; i++) {
            for (int j = 0; j < outputWaterMatrix[i].length; j++) {
                System.out.print(outputWaterMatrix[i][j] + " ");
            }
            System.out.println();
        }

    }

    private void addEdgesBetweenVertex() {
        // This nested loop adds edges to the FlowNetwork object, representing the
        // connections between vertices in the network
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                // if the input matrix has a value other than 0 at position i, j
                if (!input[i][j].equals(0)) {
                    int capacityInt = (int) input[i][j];
                    double capacity = (double) capacityInt;
                    // create a new edge object with the capacity, the edge connects vertex i to j
                    graph.addEdge(new Edge(vertexList.get(i), vertexList.get(j), capacity));
                }
            }
        }
    }

    private void addVertexToVertexList() {
        // Creating a list Of Vertex depending on the size of input ID: Number, Name:
        // ABC...
        for (int i = 0; i < input.length; i++) {
            // creates a vertex object with an id and a name, based on the position in the
            // input matrix
            vertexList.add(new Vertex(i, Character.toString((char) i + 65)));
        }
    }
}
