package ngordnet.main;

import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;

import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {
    private NGramMap ng;
    public HistoryTextHandler(NGramMap map) {
        ng = map;
    }
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        String response = "";
        int startYear = q.startYear();
        int endYear = q.endYear();
        for (String word : words) {
            TimeSeries ts = ng.weightHistory(word, startYear, endYear);
            response += word + ":" + " " + ts.toString() + "\n";
        }
        return response;
    }
}
