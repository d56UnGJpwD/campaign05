package edu.isu.cs.cs3308.structures.impl;

import java.util.LinkedList;
import java.util.List;

public class Graph<E>
{
    private boolean isWeighted;
    private boolean isDirected;
    private List<Edge<E>> edgeList;
    private List<Vertex<E>> vertexList;

    public Graph(boolean isDirected, boolean isWeighted)
    {
        this.isDirected = isDirected;
        this.isWeighted = isWeighted;
        edgeList = new LinkedList<>();
    }


    public boolean getIsDirected()
    {
        return isDirected;
    }

    public boolean getIsWeighted()
    {
        return isWeighted;
    }

    private void addEdge(Vertex<E> src, Vertex<E> dest, int weight)
    {
        if(src == null||dest==null)
        {
            return;
        }

        Edge<E> temp = new Edge(src, dest, weight);
        if(edgeList.contains(temp) == false)
        {
            edgeList.add(temp);
            src.addOutgoing(temp);
            dest.addIncoming(temp);
        }

        if(getIsDirected() == false)
        {
            Edge<E> opp = new Edge(dest, src);
            if(edgeList.contains(opp) == false)
            {
                edgeList.add(opp);
                dest.addOutgoing(opp);
                src.addIncoming(opp);
            }
        }


    }

    public void addUnweightedEdge(E src, E dest)
    {
        if(src == null || dest == null)
    }


}
