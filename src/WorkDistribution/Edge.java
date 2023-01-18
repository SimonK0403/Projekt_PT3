package WorkDistribution;

public class Edge {
    private Vertex fromVertex; // Starting vertex of the edge
    private Vertex targetVertex; // Ending vertex of the edge
    private final double capacity; // The maximum flow the edge can carry
    private double flow; // The current flow through the edge

    public Edge(Vertex fromVertex, Vertex targetVertex, double capacity) {
        this.fromVertex = fromVertex;
        this.targetVertex = targetVertex;
        this.capacity = capacity;
        this.flow = 0.0;
    }

    public Edge(Edge edge) {
        // constructor that creates a copy of the edge
        this.fromVertex = edge.getFromVertex();
        this.targetVertex = edge.getTargetVertex();
        this.capacity = edge.getCapacity();
        this.flow = edge.getFlow();
    }

    public Vertex getFromVertex() {
        return fromVertex;
    }

    public void setFromVertex(Vertex fromVertex) {
        this.fromVertex = fromVertex;
    }

    public Vertex getTargetVertex() {
        return targetVertex;
    }

    public void setTargetVertex(Vertex targetVertex) {
        this.targetVertex = targetVertex;
    }

    public double getFlow() {
        return flow;
    }

    public void setFlow(double flow) {
        this.flow = flow;
    }

    public double getCapacity() {
        return capacity;
    }

    public Vertex getOther(Vertex vertex) {
        // returns the vertex at the opposite end of the edge
        if (vertex == fromVertex) {
            return targetVertex;
        } else {
            return fromVertex;
        }
    }

    public double getResidualCapacity(Vertex vertex) {
        // Returns the capacity that can be added to this edge in the residual graph.
        // returns flow for backward edge and capacity-flow for forward edge
        if (vertex == fromVertex) {
            return flow; // backward Edge
        } else {
            return capacity - flow; // forward Edge
        }
    }

    public void addResidualFlowTo(Vertex vertex, double deltaFlow) {
        // Increase/decrease the flow through this edge in the residual graph.
        if (vertex == fromVertex) {
            flow = flow - deltaFlow; // backward Edge
        } else {
            flow = flow + deltaFlow; // forward Edge
        }
    }

    @Override
    public String toString() {
        return fromVertex + "-" + targetVertex + " " + flow + "/" + capacity;
    }
}
