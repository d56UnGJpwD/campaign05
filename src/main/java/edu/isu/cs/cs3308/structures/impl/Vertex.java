package edu.isu.cs.cs3308.structures.impl;

import java.util.LinkedList;
import java.util.List;

//simple vertex node class
public class Vertex<V>
{
    //has its element which is the 174.29.143.186
    //and has an incoming list and outgoing list for checking all connected nodes
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
    //checks to see if a node is already in the list then adds it if it is not
    public void addIncoming(Edge<V> toAdd)
    {
        if(toAdd != null && incomingList.contains(toAdd) == false)
        {
            incomingList.add(toAdd);
        }
    }
    //checks to see if a node is already in the list then adds it if it is not
    public void addOutgoing(Edge<V> toAdd)
    {
        if(toAdd != null && outgoingList.contains(toAdd) == false)
        {
            outgoingList.add(toAdd);
        }
    }
}
