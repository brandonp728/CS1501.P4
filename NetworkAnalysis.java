import java.util.*;
import java.io.*;
public class NetworkAnalysis
{
  private static EdgeWeightedDigraph graph = null;

  public static void main(String[] args) throws IOException
  {
    File f = new File(args[0]);
    Scanner fileScan = new Scanner(f);
    int vertices = Integer.parseInt(fileScan.nextLine());
    graph = new EdgeWeightedDigraph(vertices);
    while(fileScan.hasNext())
    {
      String[] split = fileScan.nextLine().split(" ");
      DirectedEdge v = new DirectedEdge(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Double.parseDouble(split[3]), Double.parseDouble(split[4]), split[2]);
      graph.addEdge(v);
    }
    Scanner input = new Scanner(System.in);
    int selection = -1;
    System.out.println("Welcome to the Network Driver!");
    System.out.println("Please select what you would like to accomplish");
    while (selection != 0)
    {
        System.out.println();
        System.out.println("1. Lowest latency path between two points");
        System.out.println("2. Is the graph connected by copper cables only?");
        System.out.println("3. Maximum data by vertex");
        System.out.println("4. Lowest average latency spanning tree");
        System.out.println("5. Determining if any two points cause the graph to fail");
        System.out.println("0. Quit");
        System.out.print("Selection: ");

        try {
            selection = input.nextInt();
        } catch (NoSuchElementException e) {
            selection = -1;
        } catch (IllegalStateException e) {
            selection = -1;
        }
        input.nextLine();

        switch (selection) {
            case 1:
                lowestLatencyPath();
                break;
            case 2:
                copperOnly();
                break;
            case 3:
                maxDataByVertex();
                break;
            case 4:
                lowestAvgSpanningTree();
                break;
            case 5:
                graphConnected();
            case 0:
                break;
            default:
                // Invalid, just ignore and let loop again
                break;
        }
    }
  }

  private static void lowestLatencyPath()
  {
    Scanner verts = new Scanner(System.in);
    System.out.println("What vertices would you like to check?");
    System.out.print("Vertex 1: ");
    int v = verts.nextInt();
    verts.nextLine();
    System.out.print("Vertex 2: ");
    int w = verts.nextInt();
    verts.nextLine();
    DijkstraAllPairsSP pathAlg = new DijkstraAllPairsSP(graph);
    if(!pathAlg.hasPath(v, w))
    {
      System.out.println("A path does not exist between those two vertices");
    }
    else
    {
      double minBandwidth = Double.MAX_VALUE;
      Iterator<DirectedEdge> itr = pathAlg.path(v, w).iterator();
      Deque<DirectedEdge> edgeStack = new ArrayDeque<DirectedEdge>();
      while(itr.hasNext())
      {
        DirectedEdge d = itr.next();
        edgeStack.push(d);
        minBandwidth = Math.min(minBandwidth, d.bandwidth());
      }
      for(int i=0; i<=edgeStack.size(); i++)
      {
        System.out.println(edgeStack.pop());
      }
      System.out.println("Minimum bandwidth along this path: " + minBandwidth);
    }


  }

  private static void copperOnly()
  {
    CC components = new CC(graph);
    Iterator<DirectedEdge> itr = graph.edges().iterator();
    EdgeWeightedDigraph copperGraph = new EdgeWeightedDigraph(graph.V());
    while(itr.hasNext())
    {
      DirectedEdge edge = itr.next();
      if(edge.cable().equals("copper"))
      {
        copperGraph.addEdge(edge);
      }
    }
    components = new CC(copperGraph);
    if(components.count()==1)
    {
      System.out.println("This graph is copper-only connected");
    }
    else
    {
      System.out.println("This graph is NOT copper-only connected");

    }
  }

  private static void maxDataByVertex()
  {
    Scanner verts = new Scanner(System.in);
    System.out.println("What vertices do you wish to find the max bandwidth of?");
    System.out.print("Vertex 1: ");
    int v = Integer.parseInt(verts.nextLine());
    System.out.print("Vertex 2: ");
    int w = Integer.parseInt(verts.nextLine());
    FlowNetwork network = new FlowNetwork(graph.V());
    Iterator<DirectedEdge> itr = graph.edges().iterator();
    while(itr.hasNext())
    {
      DirectedEdge edge = itr.next();
      FlowEdge flow = new FlowEdge(edge.from(), edge.to(), edge.bandwidth());
      network.addEdge(flow);
    }
    FordFulkerson ff = new FordFulkerson(network, v, w);
    double max = ff.value();
    System.out.println("The max amount of data available to push from vertex " + v + " " + " to " + w + " is " + max);
  }

  private static void lowestAvgSpanningTree()
  {
    PrimMST prim = new PrimMST(graph);
    System.out.println("The lowest average spanning tree contains the folllowing edges:");
    Iterator<DirectedEdge> itr = prim.edges().iterator();
    while(itr.hasNext())
    {
      System.out.println(itr.next());
    }
  }

  //brute force approach
  private static void graphConnected()
  {
    for(int i=0; i<graph.V(); i++)
    {
      for(int j=i+1; j<graph.V(); j++)
      {
          CC components = new CC(graph, i , j);
          if(components.count()!=1)
          {
            System.out.println("Removing vertices " + i + " " + j + " will cause a graph failure");
            break;
          }
      }
    }
  }


}
