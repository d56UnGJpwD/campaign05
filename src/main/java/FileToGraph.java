import edu.isu.cs.cs3308.structures.impl.Graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class FileToGraph
{
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

    public Graph<String> parseGraph(List<String> list)
    {
        Graph<String> graph = new Graph<>(list.get(0).contains("("), true);
        for(String node : list)
        {
            String[] splitSrcAndDest = node.split(":");
            String sourceNode = splitSrcAndDest[0];

            String[] splitDestNodes =  splitSrcAndDest[1].split(" ");

            for(int i = 1; i < splitDestNodes.length; i++)
            {
                String[] splitDestNodeAndWeights = splitDestNodes[i].split("\\(");

                String destNode = splitDestNodeAndWeights[0];

                if(splitDestNodeAndWeights.length > 1)
                {
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
