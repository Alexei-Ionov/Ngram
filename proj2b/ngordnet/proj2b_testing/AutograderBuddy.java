package ngordnet.proj2b_testing;

import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.main.HyponymsHandler;
import ngordnet.ngrams.NGramMap;


public class AutograderBuddy {
    /** Returns a HyponymHandler */
    public static NgordnetQueryHandler getHyponymHandler(
            String wordFile, String countFile,
            String synsetFile, String hyponymFile) {
        NGramMap ng = new NGramMap(wordFile, countFile);
        return new HyponymsHandler(hyponymFile, synsetFile, ng);
    }
}
