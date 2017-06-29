package utils;


public class Pair {
    public final int first;
    public final int second;
    
    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public static Pair create(int first, int second) {
        return new Pair(first, second);
    }
}
