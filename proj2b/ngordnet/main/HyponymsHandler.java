package ngordnet.main;

import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.ngrams.TimeSeries;
import ngordnet.plotting.Plotter;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
public class HyponymsHandler extends NgordnetQueryHandler {
    private Graph graph;
    public HyponymsHandler(String hyponymFile, String synsetFile) {

        graph = new Graph(hyponymFile, synsetFile);

    }

    @Override
    public String handle(NgordnetQuery q) {




    }

}
