
import edu.isu.cs.cs3308.structures.impl.Graph;
import edu.isu.cs.cs3308.structures.impl.Pathfinder;
import edu.isu.cs.cs3308.structures.impl.Vertex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Driver
{
    public static void main(String[] args) throws IOException
    {
        new Driver();
    }

    //hardcoded defaults used during testing
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


        UserInput();


    }

    private void UserInput() throws IOException
    {
        System.out.println(
                "\nMENU\n" +
                        "0: Print the current network configuration\n" +
                        "1: View Routing Table for a node\n" +
                        "2: Find the shortest distance between two nodes\n" +
                        "3: Quit\n"
        );
        System.out.print("Enter your selection: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        if(!input.equals("0") && !input.equals("1") && !input.equals("2")&& !input.equals("3"))
        {
            System.out.println("Unknown Command");
            return;
        }
        if(input.equals("0"))
        {
            System.out.println("\nCURRENT NETWORK CONFIGURATION: \n");
            graph.printAdjacents();
        }
        if(input.equals("1"))
        {
            GetRoutingTable(br);
        }
        if(input.equals("2"))
        {
            GetShortestPath(br);
        }
        if(input.equals("3"))
        {
            System.out.println("Quitting...");
        }

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

        System.out.println("\n"+ input + " ROUTING TABLE\n");
        System.out.println("DESTINATION    | NEXT\n");
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
                System.out.println(dest.getElement()+ " | " + path.get(1));
            }
        }

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

        System.out.print("\nEnter your second node: ");
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
                System.out.println(node + " ->");
            }
        }

    }




}
