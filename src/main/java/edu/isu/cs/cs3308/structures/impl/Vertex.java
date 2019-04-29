package edu.isu.cs.cs3308.structures.impl;

import java.util.LinkedList;
import java.util.List;

public class Vertex<V>
{
    private V element;
    private List<Edge<V>> incomingList;
    private List<Edge<V>> outgoingList;

    public Vertex(V element)
    {
        this.element = element;
        incomingList = new LinkedList<>();
        outgoingList = new LinkedList<>();
    }

    public V getElement()
    {
        return element;
    }

    public List<Edge<V>> getIncomingList()
    {
        return incomingList;
    }

    public List<Edge<V>> getOutgoingList()
    {
        return outgoingList;
    }

    public void addIncoming(Edge<V> toAdd)
    {
        if(toAdd != null && outgoingList.contains(toAdd) == false)
        {
            incomingList.add(toAdd);
        }
    }

    public void addOutgoing(Edge<V> toAdd)
    {
        if(toAdd != null && outgoingList.contains(toAdd) == false)
        {
            outgoingList.add(toAdd);
        }
    }
}
