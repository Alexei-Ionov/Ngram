package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {

        timeGetLast();
    }

    public static void timeGetLast() {
        SLList linked_lst = new SLList();
        int n = 1000;
        int max = 64000;
        int M = 10000;
        AList Ns = new AList();
        AList time = new AList();
        AList opcounts = new AList();

        for (int i = 0; i <= max; i ++){
            linked_lst.addLast(i);

            if (i == n){
                Ns.addLast(i);
                opcounts.addLast(M);
                Stopwatch sw = new Stopwatch();
                for (int j = 0; j <= M; j ++){
                    linked_lst.getLast();
                }
                double timeInSeconds = sw.elapsedTime();
                time.addLast(timeInSeconds);
                n *=2;

            }




        }
        printTimingTable(Ns, time, opcounts);







    }

}
