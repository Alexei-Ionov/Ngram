package ngordnet.main;

import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;
import ngordnet.plotting.Plotter;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class HistoryHandler extends NgordnetQueryHandler {
    private NGramMap ng;

    public HistoryHandler(NGramMap map) {
        ng = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        List<TimeSeries> timeMaps = new ArrayList<>();
        int index = 0;
        for (String word: words) {
            timeMaps.add(index, ng.weightHistory(word));
            index += 1;
        }
        XYChart chart = Plotter.generateTimeSeriesChart(words, timeMaps);
        String encodedImage = Plotter.encodeChartAsString(chart);
        return encodedImage;
    }
}
