/******************************************************************************
 *  Compilation:  javac DirectedEdge.java
 *  Execution:    java DirectedEdge
 *  Dependencies: StdOut.java
 *
 *  Immutable bandwidthed directed edge.
 *
 ******************************************************************************/
/**
 *  The {@code DirectedEdge} class represents a bandwidthed edge in an
 *  {@link EdgebandwidthedDigraph}. Each edge consists of two integers
 *  (naming the two vertices) and a real-value bandwidth. The data type
 *  provides methods for accessing the two endpoints of the directed edge and
 *  the bandwidth.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/44sp">Section 4.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */

public class DirectedEdge {
  private final int v; //one vertex
  private final int w; //another
  private final double bandwidth; //bandwidth of the edge
  private final double length; //length of the edge
  private final String cable; //type of cable

    /**
     * Initializes a directed edge from vertex {@code v} to vertex {@code w} with
     * the given {@code bandwidth}.
     * @param v the tail vertex
     * @param w the head vertex
     * @param bandwidth the bandwidth of the directed edge
     * @throws IllegalArgumentException if either {@code v} or {@code w}
     *    is a negative integer
     * @throws IllegalArgumentException if {@code bandwidth} is {@code NaN}
     */
    public DirectedEdge(int v, int w, double bandwidth, double l, String c) {
        if (v < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
        if (w < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
        if (Double.isNaN(bandwidth)) throw new IllegalArgumentException("bandwidth is NaN");
        if (Double.isNaN(l)) throw new IllegalArgumentException("length is NaN");
        this.v = v;
        this.w = w;
        this.bandwidth = bandwidth;
        this.length = l;
        this.cable = c;
    }

    /**
     * Returns the tail vertex of the directed edge.
     * @return the tail vertex of the directed edge
     */
    public int from() {
        return v;
    }

    /**
     * Returns the head vertex of the directed edge.
     * @return the head vertex of the directed edge
     */
    public int to() {
        return w;
    }

    /**
     * Returns the bandwidth of the directed edge.
     * @return the bandwidth of the directed edge
     */
    public double bandwidth() {
        return bandwidth;
    }

    /**
     * Returns the length of this edge.
     *
     * @return the length of this edge
     */
    public double length() {
        return length;
    }

    /**
     * Returns the speed of this edge, where speed = length/bandwidth
     *
     * @return the length of this edge
     */
    public double time() {
        return (length/bandwidth);
    }

    /**
     * Returns the cable type of this edge.
     *
     * @return the cable type of this edge
     */
    public String cable() {
        return cable;
    }

    /**
     * Returns a string representation of the directed edge.
     * @return a string representation of the directed edge
     */
    public String toString() {
      String s = new String("An edge of length " + length + " with bandwidth " + bandwidth + " connects vertex " + v + " to vertex " + w);
      return s;
    }
}
