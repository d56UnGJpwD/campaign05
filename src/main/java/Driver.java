
import edu.isu.cs.cs3308.structures.impl.Graph;
import edu.isu.cs.cs3308.structures.impl.Pathfinder;
import edu.isu.cs.cs3308.structures.impl.Vertex;

import javax.annotation.processing.SupportedSourceVersion;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.List;

public class Driver
{
    public static void main(String[] args) throws IOException
    {
        new Driver();
    }

    private String currentFile = "data/test.graph";
    private Graph<String> graph;

    public Driver() throws IOException
    {
        System.out.print("Enter File Name: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        currentFile = br.readLine();

        FileToGraph fp = new FileToGraph();
        try
        {
            graph = fp.parseGraph(fp.readFile(currentFile));
        }
        catch(Exception e)
        {
            System.out.println("Unknown file");
        }
        br.close();

    }

    private String UserInput(BufferedReader br) throws IOException
    {
        System.out.println(
                "\nMENU\n" +
                        "0: Print the current network configuration\n" +
                        "1: View Routing Table for a node\n" +
                        "2: Find the shortest distance between two nodes\n" +
                        "3: Quit\n"
        );
        System.out.print("Enter your selection: ");
        String input = br.readLine();
        br.close();
        return input;
    }

    private void start(String input, BufferedReader br) throws IOException
    {


        if(input == "0")
        {
            PrintConfig();
        }
        if(input == "1")
        {
            GetRoutingTable(br);
        }
        if(input == "2")
        {
            GetShortestPath(br);
        }
        if(input == "3")
        {
            System.out.println("Quitting...");
        }
        else
        {
            System.out.println("Unknown command");
        }
    }

    private void PrintConfig()
    {
        graph.printAdjacents();
    }

    private void GetRoutingTable(BufferedReader br) throws IOException
    {
        System.out.print("\nEnter the node you want to view: ");
        String input = br.readLine();
        Vertex<String> src = graph.findVert(input);
        if(src == null)
        {
            System.out.println(input + " is invalid");
            return;
        }

        System.out.println("Finding....");
        Pathfinder pathfinder = new Pathfinder();
        List<Vertex<String>> vertices = graph.getVertexList();
        for(Vertex<String> dest : vertices)
        {
            if(dest.equals(src))
            {
                continue;
            }

            List<String> path = pathfinder.findShortestPath(graph, src, dest);

            if(path.size() == 1)
            {
                System.out.println("Can not get to destination");
            }
            else
            {
                System.out.println(dest.getElement()+ " is next to " + path.get(1));
            }
        }
        br.close();

    }

    private void GetShortestPath(BufferedReader br) throws IOException
    {
        System.out.print("\nEnter your first node: ");
        String firstInput = br.readLine();
        Vertex<String> firstNode = graph.findVert(firstInput);
        if(firstNode == null)
        {
            System.out.println(firstNode + " is invalid");
            return;
        }

        System.out.println("\nEnter your second node: ");
        String secondInput = br.readLine();
        Vertex<String> secondNode = graph.findVert(secondInput);
        if(secondNode == null)
        {
            System.out.println(secondNode + " is invalid");
            return;
        }

        Pathfinder pathfinder = new Pathfinder();
        List<String> path = pathfinder.findShortestPath(graph, firstNode, secondNode);
        System.out.println("Shortest path from " + firstNode.getElement() + " to " + secondNode.getElement() + ":");
        if(path.size() == 1)
        {
            System.out.println("Cannot reach: " + path.get(0));
        }
        else
        {
            for(String node : path)
            {
                System.out.println(node);
            }
        }



    }




}
