package library;

/**
 *
 * @author Abhishek Shankhadhar
 */
class hashing {

    public static void main(String[] args) {

    }
    static final int MODULES = 2;

    int modulo[] = {1000000007, 1000000009};
    int prime[] = {41, 43};
    int len; // length of string of which hashing to be done
    long hash[][];
    long p[][];
//    n  = s.length;
//    p  = new long[2][n + 1];
//    hash  = new long[2][n + 1];      Initialize in main

    void doHash(char[] s) {
        for (int j = 0; j < MODULES; j++) {
            p[j][0] = 1;
            for (int i = 1; i <= len; i++) {
                p[j][i] = p[j][i - 1] * prime[j];
                p[j][i] %= modulo[j];
            }
        }
        for (int j = 0; j < MODULES; j++) {
            for (int i = 0; i < len; i++) {
                if (i > 0) {
                    hash[j][i] = hash[j][i - 1];
                }
                hash[j][i] += p[j][i] * (s[i] - 'a' + 1);
                hash[j][i] %= modulo[j];
            }
        }
    }

    int getHash(int j, int l, int r) {
        long h = hash[j][r];
        if (l > 0) {
            h = (h - hash[j][l - 1] + modulo[j]) % modulo[j];
        }
        h *= p[j][len - l - 1];
        h %= modulo[j];
        return (int) h;
    }

    Pair getHash(int l, int r) {
        return new Pair(getHash(0, l, r), getHash(1, l, r));
    }

    class Pair {

        int a, b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int hashCode() {
            return a * 31 + b;
        }

        @Override
        public boolean equals(Object arg0) {
            Pair arg = (Pair) arg0;
            return a == arg.a && b == arg.b;
        }

    }
}
