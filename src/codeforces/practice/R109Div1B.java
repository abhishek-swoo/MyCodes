package codeforces.practice;

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;

public class R109Div1B {

    InputStream obj;
    PrintWriter out;
    String check = "";

    //Solution !!
    void solution() {
        int n = inti(), m = inti();

        int haveFactors[][] = new int[n + 1][2];
        int lpf[] = enumLowestPrimeFactors(100000);
        boolean status[] = new boolean[n + 1];
        for (int i = 0; i < m; i++) {
            char c = chi();
            int num = inti();
            if (c == '+') {
                if (status[num]) {
                    out.println("Already on");
                    continue;
                }
                
                int factor[][] = factorFast(num, lpf);
                boolean flag = false;
                int conflict = -1;
                for (int j = 0; j < factor.length; j++) {
                    if (haveFactors[factor[j][0]][0] > 0) {
                        flag = true;
                        conflict = haveFactors[factor[j][0]][1];
                        break;
                    }
                }
                if (flag) {
                    out.println("Conflict with " + conflict);
                    continue;
                }
                for (int j = 0; j < factor.length; j++) {
                    haveFactors[factor[j][0]][0] = 1;
                    haveFactors[factor[j][0]][1] = num;
                }
                status[num] = true;
                out.println("Success");
            } else if (c == '-') {
                if (!status[num]) {
                    out.println("Already off");
                    continue;
                }
                int factor[][] = factorFast(num, lpf);
                for (int j = 0; j < factor.length; j++) {
                    haveFactors[factor[j][0]][0] = 0;
                    haveFactors[factor[j][0]][1] = 0;
                }
                status[num] = false;
                out.println("Success");
            }
        }
    }

    public static int[][] factorFast(int n, int[] lpf) {
        int[][] f = new int[9][];
        int q = 0;
        while (lpf[n] > 0) {
            int p = lpf[n];
            if (q == 0 || p != f[q - 1][0]) {
                f[q++] = new int[]{p, 1};
            } else {
                f[q - 1][1]++;
            }
            n /= p;
        }
        if (n > 1) {
            // big prime
            return new int[][]{{n, 1}};
        }
        return Arrays.copyOf(f, q);
    }

    public static int[] enumLowestPrimeFactors(int n) {
        int tot = 0;
        int[] lpf = new int[n + 1];
        int u = n + 32;
        double lu = Math.log(u);
        int[] primes = new int[(int) (u / lu + u / lu / lu * 1.5)];
        for (int i = 2; i <= n; i++) {
            lpf[i] = i;
        }
        for (int p = 2; p <= n; p++) {
            if (lpf[p] == p) {
                primes[tot++] = p;
            }
            int tmp;
            for (int i = 0; i < tot && primes[i] <= lpf[p]
                    && (tmp = primes[i] * p) <= n; i++) {
                lpf[tmp] = primes[i];
            }
        }
        return lpf;
    }
    //------->ends !!

    public static void main(String[] args) throws IOException {
        new R109Div1B().ace();
    }

    void ace() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
//        obj=check.isEmpty() ? new FileInputStream("location of file") : new ByteArrayInputStream(check.getBytes());
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
