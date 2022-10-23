package ngordnet.main;
import edu.princeton.cs.algs4.In;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    private class Node {
        private ArrayList<String> synset;
        private ArrayList<Integer> neighbors;

        private Node(ArrayList<String> syns, ArrayList<Integer> hyponyms) {
            synset = syns;
            neighbors = hyponyms;
        }

    }

    private ArrayList<Node> graph = new ArrayList<>();
    private HashMap<Integer, Integer> idToIndex;
    private HashMap<String, ArrayList<Integer>> wordToIndices;
    private int size;
    private ArrayList<String> tempRes;
    private HashSet<Node> visited;


    public Graph(String hyponymFile, String synsetFile) {
        // construct the graph from the two files in the constructor


        idToIndex = new HashMap<>();
        wordToIndices = new HashMap<>();
        size = 0;
        tempRes = new ArrayList<>();
        visited = new HashSet<>();

        In synIn = new In(synsetFile);

        while (synIn.hasNextLine()) {
            if (synIn.isEmpty()) {
                break;
            }
            String line = synIn.readLine();
            String[] info = line.split("[,]+");
            Integer id = Integer.parseInt(info[0]);
            String words = info[1];
            String[] wordsLst = words.split(" ");
            ArrayList<String> newWordsLst = new ArrayList<>(Arrays.asList(wordsLst));
            for (String word : newWordsLst) {
                if (wordToIndices.containsKey(word)) {
                    ArrayList<Integer> indices = wordToIndices.get(word);
                    indices.add(size);
                } else {
                    ArrayList<Integer> indices = new ArrayList<>();
                    indices.add(size);
                    wordToIndices.put(word, indices);
                }
            }

            ArrayList<Integer> emptyHyponyms = new ArrayList<>();
            Node newNode = new Node(newWordsLst, emptyHyponyms);
            graph.add(newNode);
            idToIndex.put(id, size);
            size += 1;
        }


        In hypoIn = new In(hyponymFile);
        while (hypoIn.hasNextLine()) {
            if (hypoIn.isEmpty()) {
                break;
            }
            String line = hypoIn.readLine();
            String[] hypnoyms = line.split(",");
            Integer ID = Integer.parseInt(hypnoyms[0]);
            Node synNode = graph.get(idToIndex.get(ID));
            for (int index = 1; index < hypnoyms.length; index++) {
                Integer id = Integer.parseInt(hypnoyms[index]);
                synNode.neighbors.add(idToIndex.get(id));
            }
        }
    }
    public ArrayList<String> hyponymsFinder(String goal) {
        /*
        if (words.isEmpty()) {
            throw new IllegalArgumentException();
        }

        HashSet<Integer> indices = new HashSet<>();
        HashSet<Integer> copy = new HashSet<>();
        for (Integer ind : wordToIndices.get(words.get(0))) {
            indices.add(ind);
            copy.add(ind);
        }

        for (String word : words) {
            for (Integer val : copy) {
                if (!wordToIndices.get(word).contains(val)) {
                    indices.remove(val);
                }
            }
            copy = indices;
        }
         */
        ArrayList<Integer> indices = wordToIndices.get(goal);

        ArrayList<String> containsDup = new ArrayList<>();
        for (Integer index : indices) {
            tempRes.clear();
            dfs(index);
            containsDup.addAll(tempRes);
        }


        HashSet<String> seen = new HashSet<>();
        ArrayList<String> res = new ArrayList<>();

        for (String word : containsDup) {
            if (!seen.contains(word)) {
                res.add(word);
                seen.add(word);
            }
        }
        visited.clear();
        return res;

    }

    private void dfs(Integer index) {
        //base case, if no neighbors
        Node node = graph.get(index);
        if (!visited.contains(node)) {
            visited.add(node);
            tempRes.addAll(node.synset);
            for (Integer val : node.neighbors) {
                dfs(val);
            }
        }

    }


}





