package codechef_contests;

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;

class josephland {

    InputStream obj;
    PrintWriter out;
    String check = "";
    int g[][];
    int n, m;
    long dd[] = new long[100010];
    long ans[];
//    int count=0;
    ArrayList<Integer>[] arr;
    SegmentTreeRMQ st;

    //Solution !!
    void solution() {
        n = inti();
        m = inti();
        int from[] = new int[n - 1];
        int to[] = new int[n - 1];
        ans = new long[n + 1];
        st = new SegmentTreeRMQ(dd, 100010);
        Arrays.fill(dd, Long.MAX_VALUE / 10);
        dd[0] = 0;
//        count++;
        for (int i = 0; i < n - 1; i++) {
            from[i] = inti() - 1;
            to[i] = inti() - 1;
        }
        g = packU(n, from, to);
        arr = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int a = inti() - 1, k = inti(), w = inti();
            arr[a].add(k);
            arr[a].add(w);
        }
        dfs(0, -1, 0);
//        pa(ans);
        int q = inti();
        for (int i = 0; i < q; i++) {
            out.println(ans[inti() - 1]);
        }
//        pa(ans);
    }

    void dfs(int source, int p, int count) {
        if (source == 0) {
            st.update(source, 0);
            ans[source] = 0;
        } else if (p != -1) {
            long l=-1;
            for (int i = 0; i < arr[source].size(); i += 2) {;
                l = st.query(Math.max(count - arr[source].get(i), 0), count - 1) + arr[source].get(i + 1);
//                System.out.println(st.query(Math.max(count - arr[source].get(i), 0), count - 1));
//                pa(dd);
//                System.out.println(source);
                if(l<ans[source] || i==0){
                    ans[source]=l;
//                    System.out.println(l);
                }
            }
            st.update(count, ans[source]);
        }
        for (int v : g[source]) {
            if (p == v) {
                continue;
            }
            dfs(v, source, count + 1);
        }
    }

    static int[][] packU(int n, int[] from, int[] to) {
//    this part of code is taken from "uwi" submission of codechef problem KNODES    
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int f : from) {
            p[f]++;
        }
        for (int t : to) {
            p[t]++;
        }
        for (int i = 0; i < n; i++) {
            g[i] = new int[p[i]];
        }
        for (int i = 0; i < from.length; i++) {
            g[from[i]][--p[from[i]]] = to[i];
            g[to[i]][--p[to[i]]] = from[i];
        }
        return g;
    }

    final class SegmentTreeRMQ {

        long tree[], a[], n;

        long ops(long a, long b) {
            return Math.min(a, b);
        }

        SegmentTreeRMQ(long a[], int n) {
            tree = new long[4 * n];
            this.a = new long[n];
            this.n = n;
            System.arraycopy(a, 0, this.a, 0, n);
            build(0, n - 1, 0);
        }

        void build(int s, int e, int c) {
            if (s == e) {
                tree[c] = a[s];
                return;
            }
            int m = (s + e) >> 1;
            build(s, m, 2 * c + 1);
            build(m + 1, e, 2 * c + 2);
            tree[c] = ops(tree[2 * c + 1], tree[2 * c + 2]);
        }

        void update(int x, long v) {
            put(0, (int) n - 1, 0, x, v);
        }

        void put(int s, int e, int c, int x, long v) {
            if (s == e) {
                tree[c] = a[s] = v;
                return;
            }
            int m = (s + e) >> 1;
            if (x <= m) {
                put(s, m, 2 * c + 1, x, v);
            } else {
                put(m + 1, e, 2 * c + 2, x, v);
            }
            tree[c] = ops(tree[2 * c + 1], tree[2 * c + 2]);
        }

        long get(int s, int e, int c, int l, int r) {
            if (s == l && e == r) {
                return tree[c];
            }
            int m = (s + e) >> 1;
            if (r <= m) {
                return get(s, m, 2 * c + 1, l, r);
            }
            if (l > m) {
                return get(m + 1, e, 2 * c + 2, l, r);
            }
            return ops(get(s, m, 2 * c + 1, l, m), get(m + 1, e, 2 * c + 2, m + 1, r));
        }

        long query(int l, int r) {
            return get(0, (int) (n - 1), 0, l, r);
        }

    }

    //------->ends !!
    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new josephland().ace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (StackOverflowError e) {
                    System.out.println("RTE");
                }
            }
        }, "1", 1 << 26).start();
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
