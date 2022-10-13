package ngordnet.ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.TreeMap;

/** An object that provides utility methods for making queries on the
 *  Google NGrams dataset (or a subset thereof).
 *
 *  An NGramMap stores pertinent data from a "words file" and a "counts
 *  file". It is not a map in the strict sense, but it does provide additional
 *  functionality.
 *
 */
public class NGramMap {
    /** Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME. */
    private TreeMap wordMap;
    private TimeSeries countMap;
    public NGramMap(String wordsFilename, String countsFilename) {
        // thoughts: each word in wordsFileName should map to its
        // own TimeSeriesMap (year --> #number of times it is seen)
        wordMap = new TreeMap<String, TimeSeries>();
        countMap = new TimeSeries();
        //order in wordsFile is Word(String) --> Year(Integer) --> Count(Double) --> unnecessary
        In in = new In(wordsFilename);
        while (in.hasNextLine()) {
            if (in.isEmpty()) {
                break;
            }
            String word = in.readString();
            Integer year = in.readInt();
            Integer count = in.readInt();
            in.readInt(); //useless column

            if (!wordMap.containsKey(word)) {
                wordMap.put(word, new TimeSeries());
            }
            TimeSeries val = (TimeSeries) wordMap.get(word);
            val.put(year, (double) count);
        }
        //order in countFile is Year(Integer) --> total number of words recorded (Double)
        //for countsFile each next produces a string containting all the information ex; "1470,984,10,1"
        In inCount = new In(countsFilename);
        while (inCount.hasNextLine()) {
            if (inCount.isEmpty()) {
                break;
            }
            String line = inCount.readLine();
            String[] strings = line.split("[,]+");
            countMap.put(Integer.parseInt(strings[0]), (double) (Long.parseLong(strings[1])));
        }
    }

    /** Provides the history of WORD. The returned TimeSeries should be a copy,
     *  not a link to this NGramMap's TimeSeries. In other words, changes made
     *  to the object returned by this function should not also affect the
     *  NGramMap. This is also known as a "defensive copy". */
    public TimeSeries countHistory(String word) {
        TimeSeries res = countHelperMethod(word, 0, 0, false);
        return res;
    }
    private TimeSeries countHelperMethod(String word, int startYear, int endYear, boolean restriction) {
        TimeSeries map = (TimeSeries) wordMap.get(word);
        TimeSeries res = new TimeSeries();
        for (Integer year : map.keySet()) {
            if ((!restriction) || (year >= startYear && year <= endYear)) {
                res.put(year, map.get(year));
            }
        }
        return res;
    }

    /** Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     *  returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other words,
     *  changes made to the object returned by this function should not also affect the
     *  NGramMap. This is also known as a "defensive copy". */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        TimeSeries res = countHelperMethod(word, startYear, endYear, true);
        return res;
    }

    /** Returns a defensive copy of the total number of words recorded per year in all volumes. */
    public TimeSeries totalCountHistory() {
        TimeSeries res = new TimeSeries();
        for (Integer year: countMap.keySet()) {
            res.put(year, countMap.get(year));
        }
        return res;
    }

    /** Provides a TimeSeries containing the relative frequency per year of WORD compared to
     *  all words recorded in that year. */
    public TimeSeries weightHistory(String word) {
        TimeSeries res = weightHelperMethod(word, 0, 0, false);
        return res;
    }

    /** Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     *  and ENDYEAR, inclusive of both ends. */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        TimeSeries res = weightHelperMethod(word, startYear, endYear, true);
        return res;
    }
    private TimeSeries weightHelperMethod(String word, int startYear, int endYear, boolean restriction) {
        TimeSeries res = new TimeSeries();
        TimeSeries map = (TimeSeries) wordMap.get(word);
        for (Integer year : map.keySet()) {
            if ((!restriction) || (year >= startYear && year <= endYear)) {
                res.put(year, map.get(year) / countMap.get(year));
            }
        }
        return res;
    }

    /** Returns the summed relative frequency per year of all words in WORDS. */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        return summedWeightHelper(words, 0, 0, false);
    }
    private TimeSeries summedWeightHelper(Collection<String> words, int startYear, int endYear, boolean restricted) {
        TimeSeries res = new TimeSeries();
        // year --> sum of freq of all words in that year
        for (String word: words) {
            TimeSeries map = weightHistory(word);
            for (Integer year : map.keySet()) {
                if ((!restricted) || (year >= startYear && year <= endYear)) {
                    if (!res.containsKey(year)) {
                        res.put(year, map.get(year));
                    } else {
                        res.put(year, res.get(year) + map.get(year));
                    }
                }
            }
        }
        return res;
    }

    /** Provides the summed relative frequency per year of all words in WORDS
     *  between STARTYEAR and ENDYEAR, inclusive of both ends. If a word does not exist in
     *  this time frame, ignore it rather than throwing an exception. */
    public TimeSeries summedWeightHistory(Collection<String> words, int startYear, int endYear) {
        TimeSeries res = summedWeightHelper(words, startYear, endYear, true);
        return res;
    }
}
