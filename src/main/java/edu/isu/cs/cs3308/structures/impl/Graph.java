package edu.isu.cs.cs3308.structures.impl;

import java.util.LinkedList;
import java.util.List;

public class Graph<E>
{
    private boolean isWeighted;
    private boolean isDirected;
    private List<Edge<E>> edgeList;
    private List<Vertex<E>> vertices;

    public Graph(boolean isWeighted, boolean isDirected)
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

        Edge<E> temp = new Edge<>(src, dest, weight);
        if(edgeList.contains(temp) == false)
        {
            edgeList.add(temp);
            src.addOutgoing(temp);
            dest.addIncoming(temp);
        }

        if(getIsDirected() == false)
        {
            Edge<E> opp = new Edge<>(dest, src);
            if(edgeList.contains(opp) == false)
            {
                edgeList.add(opp);
                dest.addOutgoing(opp);
                src.addIncoming(opp);
            }
        }


    }

    public List<Vertex<E>> getVertexList()
    {
        List<Vertex<E>> vertices = new LinkedList<>();

        for(Edge<E> edge : edgeList)
        {
            if(vertices.contains(edge.getSource()) == false)
            {
                vertices.add(edge.getSource());
            }
            if(vertices.contains(edge.getDestination()) == false)
            {
                vertices.add((edge.getDestination()));
            }
        }
        return vertices;
    }

    public void addUnweightedEdge(E src, E dest)
    {
        if(src == null || dest == null)
        {
            return;
        }

        Vertex<E> srcVert = findVert(src);
        if(srcVert == null)
        {
            srcVert = new Vertex<>(src);
        }

        Vertex<E> destVert = findVert(dest);
        if(destVert == null)
        {
            destVert = new Vertex<>(dest);
        }

        addEdge(srcVert, destVert, 1);
    }

    public void addWeightedEdge(E src, E dest, int weight)
    {
        if(src == null || dest == null)
        {
            return;
        }

        Vertex<E> srcVert = findVert(src);
        if(srcVert == null)
        {
            srcVert = new Vertex<>(src);
        }

        Vertex<E> destVert = findVert(dest);
        if(destVert == null)
        {
            destVert = new Vertex<>(dest);
        }

        addEdge(srcVert, destVert, weight);
    }

    public Vertex<E> findVert(E element)
    {
        for(Edge<E> edge : edgeList)
        {
            if(edge.getSource().getElement().equals(element))
            {
                return edge.getSource();
            }
            if(edge.getDestination().getElement().equals(element))
            {
                return edge.getDestination();
            }
        }
        return null;
    }

    /*
    public void printVerticies()
    {
        List<Vertex<E>> vertices = getVertexList();
        for(Vertex<E> vertex : vertices)
        {
            System.out.println(vertex.getElement().toString());
        }
    }

    public void printEdges()
    {
        for(Edge<E> edge : edgeList)
        {
            System.out.println(edge.getSource().getElement() + " -> " + edge.getDestination().getElement() + " (" + edge.getWeight() + ")");
        }
    }
    */

    public void printAdjacents()
    {
        List<Vertex<E>> vertices = getVertexList();
        for(Vertex<E> vertex : vertices)
        {
            for(Edge<E> edge : vertex.getOutgoingList())
            {
                String weightBuffer = isWeighted ? " (" + edge.getWeight() + ")" : "";
                System.out.println(edge.getSource().getElement().toString() + " -> " + edge.getDestination().getElement().toString() + weightBuffer);
            }
        }
    }



}


