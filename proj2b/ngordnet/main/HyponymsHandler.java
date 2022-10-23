package ngordnet.main;
import java.util.*;
import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;

import java.util.ArrayList;
import java.util.Collection;

public class HyponymsHandler extends NgordnetQueryHandler {
    private Graph graph;
    public HyponymsHandler(String hyponymFile, String synsetFile) {
        graph = new Graph(hyponymFile, synsetFile);

    }

    @Override
    public String handle(NgordnetQuery q) {

        ArrayList<String> lst = new ArrayList<>();
        int req = 0;
        for (String word : q.words()) {
            lst.addAll(graph.hyponymsFinder(word));
            req += 1;
        }

        ArrayList<String> res = new ArrayList<>();
        HashMap<String, Integer> hashmap = new HashMap<>();
        for (String word : lst) {
            if (!hashmap.containsKey(word)) {
                hashmap.put(word, 1);
            } else {
                hashmap.put(word, hashmap.get(word) + 1);
            }
        }

        for (String word : hashmap.keySet()) {
            if (hashmap.get(word) == req) {
                res.add(word);
            }
        }
        Collections.sort(res);
        String listString = String.join(", ", res);
        return listString;
    }

}
