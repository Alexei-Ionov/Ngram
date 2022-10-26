package ngordnet.main;
import java.util.*;

import edu.princeton.cs.algs4.MinPQ;
import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;

import java.util.ArrayList;
import java.util.Collection;

public class HyponymsHandler extends NgordnetQueryHandler {
    private Graph graph;
    private NGramMap ngm;

    public HyponymsHandler(String hyponymFile, String synsetFile, NGramMap ng) {
        graph = new Graph(hyponymFile, synsetFile);
        ngm = ng;
    }

    @Override
    public String handle(NgordnetQuery q) {
        //q has: words, int startYear, int EndYear, int k
        //
        int req = 0;
        HashMap<String, Integer> hashMap = new HashMap<>();

        HashSet<String> seen = new HashSet<>();

        for (String word : q.words()) {
            ArrayList<ArrayList<String>> temp = graph.hyponymsFinder(word);
            if (temp.isEmpty()) {
                continue;
            }
            seen.clear();
            for (ArrayList<String> group : temp) {
                for (String string : group) {
                    if (!seen.contains(string)) {
                        seen.add(string);
                        if (!hashMap.containsKey(string)) {
                            hashMap.put(string, 1);
                        } else {
                            hashMap.put(string, hashMap.get(string) + 1);
                        }
                    }
                }
            }
            req += 1;
        }
        ArrayList<String> res = new ArrayList<>();
        for (String word : hashMap.keySet()) {
            if (hashMap.get(word) == req) {
                res.add(word);
            }
        }
        //first implementation: return simply based off alphabetical
        if (q.k() == 0) {
            Collections.sort(res);
            return res.toString();
        }
        //else return based on popularity + alphabetical ordering

        HashMap<Double, ArrayList<String>> map = new HashMap<>();
        for (String word : res) {
            TimeSeries ts = ngm.countHistory(word, q.startYear(), q.endYear());
            //word not used in b/w start and end year
            if (!ts.isEmpty()) {
                Double freq = 0.0;
                for (Integer year : ts.keySet()) {
                    freq += ts.get(year);
                }
                if (!map.containsKey(freq)) {
                    ArrayList<String> lst = new ArrayList<>();
                    lst.add(word);
                    map.put(freq, lst);
                } else {
                    map.get(freq).add(word);
                }
            }
        }
        // no words at all were added
        if (map.isEmpty()) {
            return "[]";
        }

        MinPQ<Double> minHeap = new MinPQ<>();
        for (Double freq : map.keySet()) {
            minHeap.insert((-1 * freq));
        }
        int i = q.k();
        ArrayList<String> finalAns = new ArrayList<>();
        while (i > 0) {
            for (String word : map.get((-1 * minHeap.delMin()))) {
                if (i <= 0) {
                    break;
                }
                finalAns.add(word);
                if (minHeap.isEmpty()) {
                    //less hyonyms then there are k
                    i = 0;
                    break;
                }
                i -= 1;
            }
        }
        Collections.sort(finalAns);
        return finalAns.toString();
    }
}




        /*


        ArrayList<Double> frequencies = new ArrayList<>(secondMap.keySet());
        Collections.sort(frequencies);
        int i = 0;
        while (i < q.k()) {
            Double val = frequencies.get(i);

            for (int j = 0; j < secondMap.get(val).size(); j++) {
                if (i >= q.k()) {
                    break;
                }
                finalAns.add(secondMap.get(val).get(j));
                i += 1;
            }
        }
        String listString = String.join(", ", res);
        return listString;
    }
}

         */














        /*
        HashMap<Integer, Integer> freq = graph.returnFreqHashMap();
        HashMap<Integer, ArrayList<String>> map = graph.returnIdToWordsLstHashMap();
        ArrayList<String> res = new ArrayList<>();
        for (Integer id : freq.keySet()) {
            // if freq of ID matches the number of words in the query, then it must overlap with all words!
            if (freq.get(id) == req) {
                res.addAll(map.get(id));
            }

        }
        graph.clearFreqHashMap();

        HashSet<String> seen = new HashSet<>();
        ArrayList<String> finalAns = new ArrayList<>();
        for (String word : res) {
            if (!seen.contains(word)) {
                finalAns.add(word);
                seen.add(word);
            }
        }
        Collections.sort(finalAns);
        String listString = String.join(", ", finalAns);
        return listString;
    }
}



        /*
        ArrayList<String> res = new ArrayList<>();
        HashMap<String, Integer> hashmap = new HashMap<>();
        for (ArrayList<String> group : lst) {
            for (String word : group) {
                if (!hashmap.containsKey(word)) {
                    hashmap.put(word, 1);
                } else {
                    hashmap.put(word, hashmap.get(word) + 1);
                }
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

         */

        /*
        ArrayList<ArrayList<String>> lst = new ArrayList<>();
        int req = 0;
        for (String word : q.words()) {
            lst.addAll(graph.hyponymsFinder(word));
            req += 1;
        }
        String listString = String.join(", ", lst);
        return listString;


        HashMap<Integer, Integer> freq = graph.returnFreqHashMap();
        HashMap<Integer, ArrayList<String>> map = graph.returnIdToWordsLstHashMap();
        ArrayList<String> res = new ArrayList<>();
        for (Integer id : freq.keySet()) {
            // if freq of ID matches the number of words in the query, then it must overlap with all words!
            if (freq.get(id) == req) {
                res.addAll(map.get(id));
            }

        }
        graph.clearFreqHashMap();
        Collections.sort(res);
        String listString = String.join(", ", res);
        return listString;

         */
    /*
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
     */

