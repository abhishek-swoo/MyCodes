
/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Arrays;

class SegmentTree {

    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new SegmentTree().ace();
    }
    int lcp[];

    //Solution !!
    void solution() {
        int t = inti();
        while (t > 0) {
            t--;
            int n = inti();
            int q = inti();
            int arr[] = arri(n);
            lcp = lpf(1000001);
            lcp[0] = lcp[1] = 1;
            SegmentTreeRMQ st = new SegmentTreeRMQ(arr, n);
//        parr(st.tree);
            for (int i = 0; i < q; i++) {
                int a = inti(), b = inti() - 1, c = inti() - 1;
                if (a == 1) {
                    out.print(lcp[st.query(b, c)] + " ");

                } else {
                    st.update(b, c);
//                arr[b]/=lcp[arr[b]];
//                parr(st.tree);
                }
            }
            out.println();
        }
    }

    public static int[] lpf(int n) {
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

    final class SegmentTreeRMQ {

//    this Segment tree part of code is taken from the uwi's older submission
        int tree[], a[], n;

        int ops(int a, int b) {
            if (lcp[a] > lcp[b]) {
                return a;
            }
            return b;
//            return Math.max(lcp[a], lcp[b]);
        }

        SegmentTreeRMQ(int a[], int n) {
            tree = new int[4 * n];
            this.a = new int[n];
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

//        void update(int idx, int sfrom, int sto, int qfrom, int qto) {
//            int ss = sto - sfrom;
//            if (tree[idx] == ss) {
//                return;
//            }
//            if (sfrom == sto) {
//                tree[idx] = tree[idx]/lcp[tree[idx]];
//                return;
//            }
//            int mid = (sfrom + sto) >> 1;
//            int left = idx << 1;
//            int right = left + 1;
//            if (qto <= mid) {
//                update(left, sfrom, mid, qfrom, qto);
//            } else if (qfrom > mid) {
//                update(right, mid + 1, sto, qfrom, qto);
//            } else {
//                update(left, sfrom, mid, qfrom, mid);
//                update(right, mid + 1, sto, mid + 1, qto);
//            }
//            tree[idx] = ops(tree[left], tree[right]);
//        }
        void update(int x, int v) {
            update2(0, n - 1, 0, x, v);
        }

        void update2(int s, int e, int c, int l, int r) {
            if (tree[c] == 1) {
                return;
            }
            if (s == e) {
                tree[c] = tree[c] / lcp[tree[c]];
                return;
            }
            int m = (s + e) >> 1;
            if (r <= m) {
                update2(s, m, 2 * c + 1, l, r);
            } else if (l > m) {
                update2(m + 1, e, 2 * c + 2, l, r);
            } else {
                update2(s, m, 2 * c + 1, l, m);
                update2(m + 1, e, 2 * c + 2, m + 1, r);
            }
            tree[c] = ops(tree[2 * c + 1], tree[2 * c + 2]);
        }

        int query2(int s, int e, int c, int l, int r) {
            if (s == l && e == r) {
                return tree[c];
            }
            int m = (s + e) >> 1;
            if (r <= m) {
                return query2(s, m, 2 * c + 1, l, r);
            }
            if (l > m) {
                return query2(m + 1, e, 2 * c + 2, l, r);
            }
            return ops(query2(s, m, 2 * c + 1, l, m), query2(m + 1, e, 2 * c + 2, m + 1, r));
        }

        int query(int l, int r) {
            return query2(0, n - 1, 0, l, r);
        }

    }

    //------->ends !!
    void ace() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        solution();
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

    @SuppressWarnings("empty-statement")
    int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b));
        return b;
    }

    @SuppressWarnings("empty-statement")
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

    @SuppressWarnings("empty-statement")
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

    private static void parr(Object... o) {
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

}
