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
    private HashMap<String, HashSet<Integer>> wordToIndices;
    private int size;
    private ArrayList<String> tempRes;


    public Graph(String hyponymFile, String synsetFile) {
        // construct the graph from the two files in the constructor


        idToIndex = new HashMap<>();
        wordToIndices = new HashMap<>();
        size = 0;
        tempRes = new ArrayList<>();

        // I need a stringIntegerHashMap where --> key = word, value = ID
        // since I will parse synset first, I can also come up with the exact number of distinct IDs; therefore,
        // I will also know the exact number of indices needed for my adjacency list
        // I need a second stringIntegerHashMap where --> key = ID, value = index in adj. list
        In synIn = new In(synsetFile);
        //first input represents ID
        //comma breaks inputs
        //second input seperated by spaces are the words
        //third input is useless
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
                    HashSet<Integer> indices = wordToIndices.get(word);
                    indices.add(size);
                } else {
                    HashSet<Integer> indices = new HashSet<>();
                    indices.add(size);
                    wordToIndices.put(word, indices);
                }
            }


            Node newNode = new Node(newWordsLst, null);
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
    public ArrayList<String> hyponymsFinder(List<String> words) {
        if (words.isEmpty()) {
            throw new IllegalArgumentException();
        }

        ArrayList<Integer> indices = new ArrayList<>();
        ArrayList<Integer> copy = new ArrayList<>();
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
        
        for (Integer index : indices) {
            dfs(index);
        }


        HashSet<String> seen = new HashSet<>();
        ArrayList<String> res = new ArrayList<>();

        for (String word : tempRes) {
            if (!seen.contains(word)) {
                res.add(word);
                seen.add(word);
            }
        }
        Collections.sort(res);
        return res;

    }

    private void dfs(Integer index) {
        //base case, if no neighbors
        Node node = graph.get(index);
        if (node.neighbors == null) {
            tempRes.addAll(node.synset);
        } else {

            tempRes.addAll(node.synset);
            for (Integer val : node.neighbors) {
                dfs(val);
            }
        }
    }


}





