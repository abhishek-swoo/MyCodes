package codechef_contests;

/**
 *
 * @author Abhishek
 */
import java.io.*;
import java.util.*;

class LTIME42B {

    InputStream obj;
    PrintWriter out;
    String check = "";

    //Solution !!
    int lpf[];
    HashMap<Integer, Integer> hm;

    void solution() {
        int t = inti();
        lpf = enumLowestPrimeFactors(1000000);
        for (int tt = 0; tt < t; tt++) {
            int n = inti();
            int max = 0;
            int arr[] = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = inti();
                max = Math.max(max, arr[i]);
            }
            ArrayList<Integer> al=new ArrayList<>();
            hm=new HashMap<>();
//            System.out.println(max);
            for (int i = 0; i < n; i++) {
                int factor[][] = factorFast(arr[i], lpf);
                int len = factor.length;
                for (int j = 0; j < len; j++) {
                    if ((factor[j][1] & 1) != 0) {
                        if (hm.containsKey(factor[j][0])) {
                            int u = hm.get(factor[j][0]);
                            hm.put(factor[j][0], u + 1);
                        } else {
                            hm.put(factor[j][0], 1);
                            al.add(factor[j][0]);
                        }
                    }
                }
            }
            int ans = 0;
//            pa(prime);
            for (int i : al) {
                ans += Math.min(n - hm.get(i), hm.get(i));

            }
            out.println(ans);
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

        new LTIME42B().ace();

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
