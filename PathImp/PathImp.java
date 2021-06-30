/**
 * @author Reiden Rufin
 * @studentNumber 22986337
 */

import CITS2200.*;
import CITS2200.Graph;
import CITS2200.Path;

import java.util.*;
import java.util.PriorityQueue;



public class PathImp implements Path {

    /**
     * Using Prim's Algorithm to find the MST (Minimum Spanning Tree) of (@param graph)
     * @param graph the graph to find an MST
     * @return Weight of MST if it is found, else -1
     */
    public int getMinSpanningTree(Graph graph) {
        // TODO: Implement getMinSpanningTree()
        int nVertices = graph.getNumberOfVertices();
        // just checking if graph is null here
        if(nVertices== 0)
        {
            throw new IllegalValue("Empty graph given");
        }

        int[] parents = new int[nVertices];
        int[] distance = new int[nVertices];
        int[] colour = new int[nVertices];
        int[][] weights = graph.getEdgeMatrix();
        //Set both parents and distances to unknown
        Arrays.fill(parents, -1);
        Arrays.fill(distance, -1);

        PriorityQueue<GraphEdge> pQueue = new PriorityQueue<GraphEdge>();
        pQueue.add(new GraphEdge(0, 0));
        distance[0] = 0;

        while(!pQueue.isEmpty())
        {
            // Store the head of pQueue
            int edgeVertex = pQueue.remove().vertex;

            if(colour[edgeVertex] != 0)
            {
                continue;
            }

            colour[edgeVertex] = 1;

            for(int i = 0; i < nVertices; ++i){
                int edgeCost = weights[edgeVertex][i];
                if(edgeCost > 0 && colour[i] <1){

                    if(distance[i] > edgeCost || distance[i] == -1)
                    {
                        distance[i] = edgeCost;
                        parents[i] = edgeVertex;
                        pQueue.add(new GraphEdge(i, distance[i]));
                    }
                }
            }
        }
        int mstWeight = 0;
        for(int j : distance)
        {
            if(j == -1)
            {
                mstWeight = -1;
                break;
            }
            mstWeight += j;
        }
        return mstWeight;

    }

    /**
     * Using Djikstra's Algorithm to find shortest path
     * @param graph the graph being used to find shortest path
     * @param source the start vertex
     * @return an array of shortest distance from @param source
     */

    public int[] getShortestPaths(Graph graph, int source) {
        // TODO: Implement getShortstPaths()
        int nVertices = graph.getNumberOfVertices();
        if(nVertices == 0)
        {
            throw new IllegalValue("Empty Graph given");
        }
        int[] parent = new int[nVertices];
        int[] distance = new int[nVertices];
        int[] colour = new int[nVertices];
        int[][] weights = graph.getEdgeMatrix();
        // Just like minSpanningTree, set both parent and distances to unknown
        Arrays.fill(parent,-1);
        Arrays.fill(distance, -1);

        PriorityQueue<GraphEdge> pQueue = new PriorityQueue<GraphEdge>();

        pQueue.add(new GraphEdge(source, 0));

        distance[source] = 0;
        while(!pQueue.isEmpty())
        {
            // Storing head of pQueue
            int weight = pQueue.remove().vertex;

            if(colour[weight] != 0) continue;

            colour[weight] = 1;

            for(int i = 0; i < nVertices; ++i)
            {
                int edgeCost = weights [weight][i];
                if(edgeCost > 0 && colour[i] <1){

                    if(distance[i] == -1 || distance[i] > distance[weight] + edgeCost)
                    {
                        distance[i] = distance[weight] + edgeCost;
                        parent[i] = weight;
                       pQueue.add(new GraphEdge(i, distance[i]));
                    }
                }
            }
        }
        return distance;

    }

    /**
     * Implemented an inner class here
     * This represents the graph edges being used in pQueue
     */
    private static class GraphEdge implements Comparable<GraphEdge>
    {
        public int vertex;
        public int weight;

        public GraphEdge(int endVertex, int cost)
        {
            vertex = endVertex;
            weight = cost;
        }

        public int compareTo(GraphEdge edge)
        {
            int storeWeight = edge.weight;

            // return 1 if weight > storeWeight
            // return -1 if weight < storeWeight
            // else 0
            return Integer.compare(weight,storeWeight);
        }
    }


}
