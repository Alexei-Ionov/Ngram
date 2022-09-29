package hw2;
import edu.princeton.cs.algs4.StdRandom;
public class PercolationStats {
    private int[] percolationValues;
  ///  private double meanVal;
    private int percolationValuesLength;
  ///  private double std;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        percolationValues = new int[T];
        for (int i = 0; i < T; i++) {
            Percolation newPercolation = pf.make(N);
            int cnt = 0;
            while (!newPercolation.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                newPercolation.open(row, col);
                cnt += 1;
            }
            percolationValues[i] = cnt;
        }
        percolationValuesLength = T;
        //meanVal = mean();
        ///std = stddev();
    }

    public double mean() {
        double sum = 0;
        for (int i = 0; i < percolationValuesLength; i++) {
            sum += percolationValues[i];
        }
        return sum / percolationValuesLength;
    }

    public double stddev() {
        double mean = mean();
        double sum = 0;
        for (int i = 0; i < percolationValuesLength; i++) {
            sum += Math.pow((percolationValues[i] - mean), 2);
        }
        double sdSqared = sum / (percolationValuesLength - 1);
        return Math.pow(sdSqared, 0.5);
    }
    public double confidenceLow() {
        double mean = mean();
        double sqT = Math.pow(percolationValuesLength, 2);
        return (mean - ((1.96 * stddev()) / sqT));
    }
    public double confidenceHigh() {
        double mean = mean();
        double sqT = Math.pow(percolationValuesLength, 2);
        return (mean + ((1.96 * stddev()) / sqT));
    }
}
