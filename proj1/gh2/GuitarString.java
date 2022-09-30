package gh2;
import deque.ArrayDeque;
import deque.Deque;
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    private Deque<Double> buffer;

    public GuitarString(double frequency) {
        int n = (int) (Math.round(SR / frequency));
        buffer = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            buffer.addFirst(0.0);
        }
    }
    public void pluck() {
        for (int j = 0; j < buffer.size(); j++) {
            double r = Math.random() - 0.5;
            buffer.removeFirst();
            buffer.addLast(r);
        }
    }
    public void tic() {
        Double val = buffer.removeFirst();
        val = ((val + buffer.get(0)) / 2) * DECAY;
        buffer.addLast(val);
    }
    public double sample() {
        return buffer.get(0);
    }
}