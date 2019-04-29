package edu.isu.cs.cs3308.structures.impl;

public class Edge<V>
{
    //has a source vertex, an destination vertex, and its weight
    private Vertex<V> source;
    private Vertex<V> destination;
    private int weight;


    //initial create edge constructor
    public Edge(Vertex<V> source, Vertex<V> destination)
    {
        this.source = source;
        this.destination = destination;
        weight = 1;
    }

    //another constructor that has a weight parameter
    public Edge(Vertex<V> source, Vertex<V> destination, int weight)
    {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }


    public Vertex<V> getSource()
    {
        return source;
    }

    public Vertex<V> getDestination()
    {
        return destination;
    }

    public int getWeight()
    {
        return weight;
    }

}
