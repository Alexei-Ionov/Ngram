package ngordnet.ngrams;
import java.util.*;
import java.util.TreeMap;

/** An object for mapping a year number (e.g. 1996) to numerical data. Provides
 *  utility methods useful for data analysis.
 */
public class TimeSeries extends TreeMap<Integer, Double> {
    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
        this.clear();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        this.clear();
        for (Integer year : ts.keySet()) {
            if (year >= startYear && year <= endYear) {
                this.put(year, ts.get(year));
            }
        }
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        List<Integer> yearList = new ArrayList<>();
        int index = 0;
        for (Integer year: this.keySet()) {
            yearList.add(index, year);
            index += 1;
        }
        return yearList;
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        List<Integer> years = years();
        List<Double> dataList = new ArrayList<>();
        for (int i = 0; i < years.size(); i++) {
            dataList.add(i, get(years.get(i)));
        }
        return dataList;
    }

    /**
     * Returns the yearwise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     */
    public TimeSeries plus(TimeSeries ts) {
        TimeSeries newTs = new TimeSeries();
        HashSet visited = new HashSet();
        for (Integer year : this.keySet()) {
            Double val = ts.get(year);
            if (val != null) {
                newTs.put(year, this.get(year) + val);
            } else {
                newTs.put(year, this.get(year));
            }
            visited.add(year);
        }
        for (Integer year2 : ts.keySet()) {
            if (!visited.contains(year2)) {
                newTs.put(year2, ts.get(year2));
            }
        }
        return newTs;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. If TS is missing a year that exists in this TimeSeries,
     * throw an IllegalArgumentException. If TS has a year that is not in this TimeSeries, ignore it.
     * Should return a new TimeSeries (does not modify this TimeSeries).
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        TimeSeries newTs = new TimeSeries();
        for (Integer year : this.keySet()) {
            Double val = ts.get(year);
            if (val == null) {
                throw new IllegalArgumentException();
            } else {
                newTs.put(year, (this.get(year) / val));
            }
        }
        return newTs;
    }
}
