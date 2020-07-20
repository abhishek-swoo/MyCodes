package codeforces.practice;

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;

public class String2_E {

    InputStream obj;
    PrintWriter out;
    String check = "";
    static final int MODULES = 2;
    int modulo[] = {1000000007, 1000000009};
    int prime[] = {41, 43};
    int n, k;
    int bads[];
    long hash[][];
    long p[][];

    //Solution !!
    boolean isGood(int l, int r) {
        int sum = bads[r];
        if (l > 0) {
            sum -= bads[l - 1];
        }
        return sum <= k;
    }
    void solution() {
        char s[] = stri().toCharArray();
        char bad[] = stri().toCharArray();
        k = inti();

        n = s.length;
        bads = new int[n];
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                bads[i] = bads[i - 1];
            }
            if (bad[s[i] - 'a'] == '0') {
                bads[i]++;
            }
        }
        
        p = new long[2][n + 1];
        hash = new long[2][n + 1];
        doHash(s);
//        tr(hash);
        int ans = 0;
        for (int len = 1; len <= n; len++) {
            HashSet<Pair> set = new HashSet<>();
            for (int j = 0; j + len - 1 < n; j++) {
                if (isGood(j, j + len - 1)) {
                    set.add(getHash(j, j + len - 1));
                }
            }
            ans += set.size();
        }

        out.println(ans);
    }

    void doHash(char []s){
        for (int j = 0; j < MODULES; j++) {
            p[j][0] = 1;
            for (int i = 1; i <= n; i++) {
                p[j][i] = p[j][i - 1] * prime[j];
                p[j][i] %= modulo[j];
            }
        }
        for (int j = 0; j < MODULES; j++) {
            for (int i = 0; i < n; i++) {
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
        h *= p[j][n - l - 1];
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
    //------->ends !!
    public static void main(String[] args) throws IOException {

        new String2_E().ace();

    }

    void ace() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
//        obj=check.isEmpty() ? new FileInputStream("C:\\\\Users\\\\Abhishek Shankhadhar\\\\Desktop\\\\a\\\\in1b.txt") : new ByteArrayInputStream(check.getBytes());
//        long t1=System.currentTimeMillis();
        solution();
//        long t2=System.currentTimeMillis();
//        out.println(t2-t1);
        out.flush();
        out.close();
    }
    byte inbuffer[] = new byte[1024];
    int lenbuffer = 0, ptrbuffer = 0;

    int readByte() {
        if (lenbuffer == -1) {
            throw new InputMismatchException();
        }
        if (ptrbuffer >= lenbuffer) {
            ptrbuffer = 0;
            try {
                lenbuffer = obj.read(inbuffer);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
        }
        if (lenbuffer <= 0) {
            return -1;
        }
        return inbuffer[ptrbuffer++];
    }

    boolean isSpaceChar(int c) {
        return (!(c >= 33 && c <= 126));
    }

    String stri() {
        int b = skip();
        StringBuilder sb = new StringBuilder();
        while (!(isSpaceChar(b))) // when nextLine, (isSpaceChar(b) && b!=' ')
        {
            sb.appendCodePoint(b);
            b = readByte();
        }
        return sb.toString();
    }

    int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b));
        return b;
    }

    int inti() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    int[][] ar2D(int n, int m) {
        int ark[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ark[i][j] = inti();
            }
        }
        return ark;
    }

    long loni() {
        long num = 0;
        int b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if (b == '-') {
            minus = true;
            b = readByte();
        }
        while (true) {
            if (b >= '0' && b <= '9') {
                num = num * 10 + (b - '0');
            } else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }

    float fl() {
        return Float.parseFloat(stri());
    }

    double dou() {
        return Double.parseDouble(stri());
    }

    char chi() {
        return (char) skip();
    }

    int[] arri(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = inti();
        }
        return a;
    }

    long[] arrl(int n) {
        long a[] = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = loni();
        }
        return a;
    }

    String[] stra(int n) {
        String a[] = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = stri();
        }
        return a;
    }

    private static void pa(Object... o) {
        System.out.println(Arrays.deepToString(o));
    }
//    uwi mod pow function

    public static long pow(long a, long n, long mod) {
//		a %= mod;
        long ret = 1;
        int x = 63 - Long.numberOfLeadingZeros(n);
        for (; x >= 0; x--) {
            ret = ret * ret % mod;
            if (n << 63 - x < 0) {
                ret = ret * a % mod;
            }
        }
        return ret;
    }

    int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }

    long lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }
}
