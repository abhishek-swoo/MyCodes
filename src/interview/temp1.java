package interview;

import java.util.*;

public class temp1 {
    public static void main(String args[]) {

        Pair[] pair = new Pair[3];

        pair[0] = new Pair(2,7);
        pair[1] = new Pair(7,7);
        pair[2] = new Pair(1,7);

        Arrays.sort(pair);

        for (int i = 0; i< 3;i++) {
            System.out.println(pair[i].x +" "+ pair[i].y);
        }
    }

    static class Pair implements Comparable<Pair> {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Pair o) {

            if (this.x > o.x) {
                return -1;
            } else {
                return 1;
            }
        }
    }

}
