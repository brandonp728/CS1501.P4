/******************************************************************************
 *  Compilation:  javac Edge.java
 *  Execution:    java Edge
 *  Dependencies: StdOut.java
 *
 *  Immutable bandwidthed edge.
 *
 ******************************************************************************/

/**
 *  The {@code Edge} class represents a bandwidthed edge in an
 *  {@link EdgebandwidthedGraph}. Each edge consists of two integers
 *  (naming the two vertices) and a real-value bandwidth. The data type
 *  provides methods for accessing the two endpoints of the edge and
 *  the bandwidth. The natural order for this data type is by
 *  ascending order of bandwidth.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/43mst">Section 4.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class Edge implements Comparable<Edge> {

    private final int v; //one vertex
    private final int w; //another
    private final double bandwidth; //bandwidth of the edge
    private final double length; //length of the edge
    private final String cable; //type of cable


    /*
     * Initializes an edge between vertices {@code v} and {@code w} of
     * the given {@code bandwidth}.
     *
     * @param  v one vertex
     * @param  w the other vertex
     * @param  bandwidth the bandwidth of this edge
     * @throws IllegalArgumentException if either {@code v} or {@code w}
     *         is a negative integer
     * @throws IllegalArgumentException if {@code bandwidth} is {@code NaN}
     */
    public Edge(int v, int w, double bandwidth, double l, String c) {
        if (v < 0) throw new IllegalArgumentException("vertex index must be a nonnegative integer");
        if (w < 0) throw new IllegalArgumentException("vertex index must be a nonnegative integer");
        if (Double.isNaN(bandwidth)) throw new IllegalArgumentException("bandwidth is NaN");
        if (Double.isNaN(l)) throw new IllegalArgumentException("length is NaN");
        this.v = v;
        this.w = w;
        this.bandwidth = bandwidth;
        this.length = l;
        this.cable = c;
    }

    /**
     * Returns the bandwidth of this edge.
     *
     * @return the bandwidth of this edge
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
     * Returns the cable type of this edge.
     *
     * @return the cable type of this edge
     */
    public String cable() {
        return cable;
    }

    /**
     * Returns either endpoint of this edge.
     *
     * @return either endpoint of this edge
     */
    public int either() {
        return v;
    }

    /**
     * Returns the endpoint of this edge that is different from the given vertex.
     *
     * @param  vertex one endpoint of this edge
     * @return the other endpoint of this edge
     * @throws IllegalArgumentException if the vertex is not one of the
     *         endpoints of this edge
     */
    public int other(int vertex) {
        if      (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new IllegalArgumentException("Illegal endpoint");
    }

    /**
     * Compares two edges by bandwidth.
     * Note that {@code compareTo()} is not consistent with {@code equals()},
     * which uses the reference equality implementation inherited from {@code Object}.
     *
     * @param  that the other edge
     * @return a negative integer, zero, or positive integer depending on whether
     *         the bandwidth of this is less than, equal to, or greater than the
     *         argument edge
     */
    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.bandwidth, that.bandwidth);
    }

    /**
     * Returns a string representation of this edge.
     *
     * @return a string representation of this edge
     */
    public String toString() {
      StringBuilder s = new StringBuilder();
      s.append("Edge " + v + " connected to edge " + w + " ");
      s.append("by means of a " + cable + " with length " + length + " and bandwidth " + bandwidth);
      return s.toString();
    }
}
