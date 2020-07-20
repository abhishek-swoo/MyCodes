
package library;

// for minimum from l to r

/**
 *
 * @author Abhishek
 */
import java.io.*;
import java.util.*;
class lazy_segment_tree{

    InputStream obj;
    PrintWriter out;
    String check = "";

    //Solution !!
    void solution() {
        int n = inti();
        int arr[] = arri(n);
        lazySegmentTree lz=new lazySegmentTree(arr, n);
        int q = inti();
        for (int i = 0; i < q; i++) {
            int u = inti(), l = inti() - 1, r = inti() - 1;
            if (u == 0) {
                int v = inti();
                lz.updateLazy(l, r, v, 0, n - 1, 0);
            } else {
                System.out.println(lz.rangeQueryLazy(l, r, 0, n - 1, 0));
            }
        }
    }
    
    class lazySegmentTree {

        int segmentTree[];
        int lazy[];
        int input[];

        public lazySegmentTree(int input[], int n) {
            segmentTree = new int[4 * n];
            lazy = new int[4 * n];
            this.input=new int[n];
            System.arraycopy(input, 0, this.input, 0, n);
            build(0, n - 1, 0);
        }

        public final void build(int s, int e, int c) {
            if (s == e) {
                segmentTree[c] = input[s];
                return;
            }
            int m = (s + e) >> 1;
            build(s, m, 2 * c + 1);
            build(m + 1, e, 2 * c + 2);
            segmentTree[c] = ops(segmentTree[2 * c + 1], segmentTree[2 * c + 2]);
        }

        void updateLazy(int l, int r, int val, int low, int high, int pos) {
            if (low > high) {
                return;
            }
//        make sure all propogation is done at this position otherwise propogate to the child
            if (lazy[pos] != 0) {
//                for sum update this node to segmentTree[pos] += (high-low+1)*lazy[pos];
                segmentTree[pos] += lazy[pos];
                if (low != high) {
                    lazy[2 * pos + 1] += lazy[pos];
                    lazy[2 * pos + 2] += lazy[pos];
                }
                lazy[pos] = 0;
            }
            if (l > high || r < low) {  // no overlap condition
                return;
            }
            if (l <= low && r >= high) {  // total overlap
//                for sum update this node to segmentTree[pos] += (high-low+1)*val;
                segmentTree[pos] += val;
                if (low != high) {
                    lazy[2 * pos + 1] += val;
                    lazy[2 * pos + 2] += val;
                }
                return;
            }
            // partial overlap
            int mid = (low + high) / 2;
            updateLazy(l, r, val, low, mid, 2 * pos + 1);
            updateLazy(l, r, val, mid + 1, high, 2 * pos + 2);
            segmentTree[pos] = ops(segmentTree[2 * pos + 1], segmentTree[2 * pos + 2]);
        }

        int rangeQueryLazy(int qlow, int qhigh, int low, int high, int pos) {
            if (low > high) {
                return Integer.MAX_VALUE / 2;  // for sum return 0;
            }

            if (lazy[pos] != 0) {
//                for sum update this node to segmentTree[pos] += (high-low+1)*lazy[pos];
                segmentTree[pos] += lazy[pos];
                if (low != high) {
                    lazy[2 * pos + 1] += lazy[pos];
                    lazy[2 * pos + 2] += lazy[pos];
                }
                lazy[pos] = 0;
            }
            if (qlow > high || qhigh < low) { // no overlap
                return Integer.MAX_VALUE / 2;        // for sum return 0;
            }
            if (qlow <= low && qhigh >= high) { //total overlap
                return segmentTree[pos];
            }
            // partial overlap
            int mid = (low + high) / 2;
            return ops(rangeQueryLazy(qlow, qhigh, low, mid, 2 * pos + 1), rangeQueryLazy(qlow, qhigh, mid + 1, high, 2 * pos + 2));

        }

        int ops(int a, int b) {
            return Math.min(a, b);
        }
    }

    //------->ends !!
    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new lazy_segment_tree().ace();
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
