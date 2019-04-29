package edu.isu.cs.cs3308.structures.impl;
import com.google.common.collect.Table;
import javafx.scene.control.Tab;

import java.util.*;

//https://google.github.io/guava/releases/19.0/api/docs/com/google/common/collect/Table.html
//https://google.github.io/guava/releases/19.0/api/diffs/changes/com.google.common.collect.Table.html
public class Pathfinder
{

    public class TableEntry
    {
        //cost is total distance covered
        int vertex;
        boolean isKnown;
        int cost;
        int pathIndex;


        public TableEntry(int index)
        {
            vertex = index;
            isKnown = false;
            cost = Integer.MAX_VALUE;
            pathIndex = -1;
        }

    }


    //returns a table entry containing path information for the list of vertices
    public <V> TableEntry[] Pathfinder(List<Vertex<V>> vertices, int startPos)
    {
        TableEntry[] table = new TableEntry[vertices.size()];
        for(int i = 0; i < table.length; i++)
        {
            table[i] = new TableEntry(i);
        }

        table[startPos].cost = 0;
        table[startPos].pathIndex = startPos;

        //uses a queue to begin pathfinding
        Queue<Integer> queue = new PriorityQueue<>();
        queue.add(startPos);

        while(queue.isEmpty() == false)
        {
            int sourcePos = queue.poll();
            int sourceCost = table[sourcePos].cost;

            List<Edge<V>> edgeList = vertices.get(sourcePos).getOutgoingList();
            for(Edge<V> edge : edgeList)
            {
                int destIndex = vertices.indexOf(edge.getDestination());
                if(table[destIndex].isKnown == false)
                {
                    queue.add(destIndex);
                }
                //checks costs with weights and finds the saves it
                if(table[destIndex].cost > edge.getWeight() + sourceCost)
                {
                    table[destIndex].cost = edge.getWeight() + sourceCost;
                    table[destIndex].pathIndex = sourcePos;
                }
            }

            table[sourcePos].isKnown = true;
        }

        return table;
    }

    //returns a table entry of data used in findShortestPath
    public <V> TableEntry[][] getData(List<Vertex<V>> vertices)
    {
        int size = vertices.size();
        TableEntry[][] data = new TableEntry[size][size];
        for(int i = 0; i < size; i++)
        {
            data[i] = Pathfinder(vertices, i);
        }
        return data;
    }

    public <V> List<V> findShortestPath(Graph<V> graph, Vertex<V> source, Vertex<V> destination)
    {
        //creates a copy of hte graph vertex list
        List<V> list = new LinkedList<>();
        List<Vertex<V>> vertices = graph.getVertexList();
        TableEntry[][] data = getData(vertices);

        int srcIndex = vertices.indexOf(source);
        int destIndex = vertices.indexOf(destination);

        TableEntry[] srcEntries = data[srcIndex];
        // and uses a stack to reverse the queue used earlier
        Stack<V> stack = new Stack<>();
        TableEntry pathEntry = srcEntries[destIndex];

        stack.push(vertices.get(pathEntry.vertex).getElement());

        try
        {
            pathEntry = srcEntries[pathEntry.pathIndex];
        }
        catch(Exception e)
        {

        }
        V toAdd = vertices.get(pathEntry.vertex).getElement();
        stack.push(toAdd);
        while(pathEntry.vertex != srcIndex)
        {
            try
            {
                pathEntry = srcEntries[pathEntry.pathIndex];
            }
            catch(Exception e)
            {

            }
            toAdd = vertices.get(pathEntry.vertex).getElement();

            stack.push(toAdd);
        }

        while(stack.isEmpty() == false)
        {
            //add it back to the proper position
            list.add(stack.pop());
        }

        return list;
    }


}
