package ngordnet.main;

import java.util.ArrayList;

public class DfsTest {
    private int[][] graph;
    public DfsTest() {
        graph = new int[3][];



    }
    private ArrayList<String> dfs(ArrayList<String> res, Integer index) {
        //base case, if no neighbors
        Graph.Node node = graph.get(index);
        if (node.neighbors == null) {
            res.addAll(node.synset);
            return null;
        } else {

            res.addAll(node.synset);
            for (Integer val : node.neighbors) {
                dfs(res, val);
            }
        }
        return res;
    }
}
