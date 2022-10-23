package ngordnet.main;

import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;

import java.util.ArrayList;
public class HyponymsHandler extends NgordnetQueryHandler {
    private Graph graph;
    public HyponymsHandler(String hyponymFile, String synsetFile) {
        graph = new Graph(hyponymFile, synsetFile);

    }

    @Override
    public String handle(NgordnetQuery q) {
        ArrayList<String> lst = graph.hyponymsFinder(q.words());
        String listString = String.join(", ", lst);
        return listString;
    }

}
