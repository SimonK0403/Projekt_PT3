package WorkDistribution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FordFulkerson {
    private boolean[] marked; // marked[v.getId()] = true s->v in the residual graph
    private Edge[] edgeTo; // edgeTo[v}= edges in the augmenting path
    private double valueMaxFlow; // the value of the max flow
    private ArrayList<String> matrix = new ArrayList<String>(); // array list that contains the edges in the augmenting
                                                                // path

    public FordFulkerson(FlowNetwork flowNetwork, Vertex s, Vertex t) {

        while (hasAugmentingPath(flowNetwork, s, t)) {
            double minValue = Double.POSITIVE_INFINITY;
            // Find the minimum residual capacity in the augmenting path
            for (Vertex v = t; v != s; v = edgeTo[v.getId()].getOther(v)) {
                minValue = Math.min(minValue, edgeTo[v.getId()].getResidualCapacity(v));
            }
            // Add the minimum capacity to the edges in the augmenting path
            for (Vertex v = t; v != s; v = edgeTo[v.getId()].getOther(v)) {
                edgeTo[v.getId()].addResidualFlowTo(v, minValue);
                // System.out.println(edgeTo[v.getId()]);
                this.matrix.add(edgeTo[v.getId()].toString());
            }
            valueMaxFlow = valueMaxFlow + minValue;
        }

    }

    public double getMaxFlow() {
        return this.valueMaxFlow; // returns the value of the max flow
    }

    public ArrayList<String> getMatrix() {
        return matrix; // returns the arraylist with the edges in the augmenting path
    }

    private boolean hasAugmentingPath(FlowNetwork flowNetwork, Vertex s, Vertex t) {
        // This code is a method that looks for an augmenting path in a flow network,
        // starting from a source vertex "s" and ending at a sink vertex "t".
        // initialize the data structures

        edgeTo = new Edge[flowNetwork.getNumOfVertices()]; // array that will store the last edge on the path from the
                                                           // source to a given vertex.
        marked = new boolean[flowNetwork.getNumOfVertices()]; // boolean array that will mark the vertices that have
                                                              // been visited during the search.

        Queue<Vertex> queue = new LinkedList<>(); // queue to store vertices, which will be used in a breadth-first
                                                  // search (BFS)
        queue.add(s); // add the source vertex to the queue

        marked[s.getId()] = true; // mark the source vertex as visited

        // The next step is a while loop that runs as long as there are still vertices
        // in the queue, and the sink vertex has not yet been visited.
        // In each iteration of the loop, a vertex is removed from the front of the
        // queue and checked for available capacity on its outgoing edges.
        while (!queue.isEmpty() && !marked[t.getId()]) {
            Vertex v = queue.remove();
            for (Edge e : flowNetwork.getAdjacenciesList(v)) { // iterate over the edges that are incident to this
                                                               // vertex.
                Vertex w = e.getOther(v); // retrive the other vertex of the current edge.
                if (e.getResidualCapacity(w) > 0) { // check if there is still capacity available on the edge
                    if (!marked[w.getId()]) { // check if the vertex at the other end of the edge has not been visited
                                              // yet
                        edgeTo[w.getId()] = e; // store the current edge as the last edge on the path to this vertex
                        marked[w.getId()] = true; // mark the vertex as visited
                        queue.add(w); // add the vertex to the end of the queue for further exploration
                    }
                }
            }
        }
        return marked[t.getId()]; // returns true if an augmenting path was found from s to t
    }

}
