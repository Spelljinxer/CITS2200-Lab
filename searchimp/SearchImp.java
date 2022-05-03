/*
 * @author Spelljinxer
 */

import CITS2200.Graph;
import CITS2200.Search;

import java.util.*;

public class SearchImp implements Search {

    // Using colours WHITE, GREY and BLACK to represent the state of the vertex
    private int WHITE = 0;
    private int GREY = 1;
    private int BLACK = 2;
    private int time;
    private int [] colors;
    private int[][] times;
    // Using a Linked List for BFS.
    private LinkedList<int[]> bfsList = new LinkedList<int[]>();


    /**
     * Uses BFS from the root vertex
     * @param testGraph is the graph being tested
     * @param startVertex is the root vertex
     * @return A tree from start vertex
     */
    public int[] getConnectedTree(Graph testGraph, int startVertex) {
        // TODO: Implement getConnectedTree.
        BFS(testGraph,startVertex);
        return bfsList.get(0);
    }

    /**
     * Uses BFS from the root vertex
     * @param g is the graph being tested
     * @param startVertex is the root vertex
     * @return A tree from start vertex with the distances of each vertex from startVertex
     */
    public int[] getDistances(Graph g, int startVertex) {
        // TODO: Implement getDistances.
        BFS(g,startVertex);
        return bfsList.get(1);
    }

    /**
     * Performs the BFS algorithm
     * @param g is graph being analyzed
     * @param startVertex is the first vertex to perform BFS
     */
    public void BFS(Graph g, int startVertex)
    {
        int [][] edgeMatrix = g.getEdgeMatrix();

        int[] colour = new int[g.getNumberOfVertices()];
        colour[startVertex] = GREY;

        int[] parent = new int[g.getNumberOfVertices()];
        Arrays.fill(parent, -1);

        int[]distances = new int[g.getNumberOfVertices()];
        Arrays.fill(distances, -1);

        Queue<Integer> queue = new LinkedList<Integer>();

        queue.add(startVertex);
        distances[startVertex] = 0;

        // This is the BFS Algorithm
        while(!queue.isEmpty())
        {
            int focus = queue.poll();
            for(int i = 0;i < edgeMatrix.length; ++i)
            {
                if (edgeMatrix[focus][i] > 0 && colour[i] < 1)
                {
                    distances[i] = distances[focus] + 1;
                    parent[i] = focus;
                    colour[i] = 1;
                    queue.add(i);
                }
            }
        }
        bfsList.add(0, parent);
        bfsList.add(1,distances);
    }

    /**
     * Does a DFS from root vertex
     * Calls the DFS method
     * @param g is graph being tested
     * @param startVertex is the root vertex
     * @return a 2D array with the time it takes for the start and end of each vertex
     */
    public int[][] getTimes(Graph g, int startVertex) {
        // TODO: Implement getTimes.
        int vertex = g.getNumberOfVertices();
        this.colors = new int[vertex];
        this.times = new int[vertex][2];
        this.time = 0;
        DFS(g.getEdgeMatrix(), startVertex);
        return times;

    }

    /**
     * A recursive function of the DFS algorithm to find the start and end times of each vertex
     * @param edgeMatrix is the 2d matrix of the graph
     * @param vertex is the next vertex to be analyzed
     */
    private void DFS(int [][] edgeMatrix, int vertex)
    {
        colors[vertex] = GREY;
        times[vertex][0] = time;
        time++;

        int[] edges = edgeMatrix[vertex];
        for(int i = 0 ;i < edges.length; i++)
        {
            if (colors[i] == WHITE)
            {
                DFS(edgeMatrix, i);
            }
        }
        colors[vertex] = BLACK;
        times[vertex][1] = time;
        time++;
    }
}



