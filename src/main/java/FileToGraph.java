import edu.isu.cs.cs3308.structures.impl.Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;


//this class takes my file, reads it, and then converts its contents to a graph
public class FileToGraph
{
    //readFIle class, reads the file line by line then returns a list of contents
    public List<String> readFile(String file)
    {
        LinkedList<String> list = new LinkedList<>();
        try
        {
            BufferedReader br;
            FileReader fr = new FileReader(file);
            br = new BufferedReader(fr);

            String line = br.readLine();

            while(line != null)
            {
                list.add(line);

                line = br.readLine();
            }
            br.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

        return list;
    }


    //parse graph breaks up the graph at ( and at : and " "
    public Graph<String> parseGraph(List<String> list)
    {
        //if the graph contains ( then it is weighted
        Graph<String> graph = new Graph<>(list.get(0).contains("("), true);
        for(String node : list)
        {
            //split the source and destination nodes at the :
            String[] splitSrcAndDest = node.split(":");
            String sourceNode = splitSrcAndDest[0];

            //split them again at the space
            String[] splitDestNodes =  splitSrcAndDest[1].split(" ");

            // i = 0 is a space from the previous split, so its skipped
            for(int i = 1; i < splitDestNodes.length; i++)
            {
                String[] splitDestNodeAndWeights = splitDestNodes[i].split("\\(");
                //split the node again so now it is its node and weight
                String destNode = splitDestNodeAndWeights[0];
                //checks for weight
                if(splitDestNodeAndWeights.length > 1)
                {
                    //removes the ) from the end of the nodes
                    String weightStr = splitDestNodeAndWeights[1].substring(0, splitDestNodeAndWeights[1].length() - 1);
                    int weight = Integer.parseInt(weightStr);
                    graph.addWeightedEdge(sourceNode, destNode, weight);
                }
                else
                {
                    graph.addUnweightedEdge(sourceNode, destNode);
                }
            }
        }
        return graph;
    }



}
