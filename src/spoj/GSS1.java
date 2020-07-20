
package spoj;
import java.io.*;
import java.util.*;
class GSS1 {
    InputStream obj;
    PrintWriter out;
    String check = "";
    public static void main(String[] args) throws IOException {
        new GSS1().ace();
    }
    //Solution !!
    void solution() {
        int n = inti();
        int arr[] = arri(n);
        SegmentTreeRMQ st = new SegmentTreeRMQ(arr, n);
        int q = inti();
        for (int i = 0; i < q; i++) {
            out.println(st.query(inti() - 1, inti() - 1).ans);
        }
    }
    class Data {
        int sum, pref, suff, ans;
    }
    final class SegmentTreeRMQ {
        Data tree[];
        int a[], n;
        Data makeData(int v) {
            Data res = new Data();
            res.sum = v;
            res.suff = res.ans = res.pref = v;
            return res;
        }
        Data combine(Data l, Data r) {
            Data res = new Data();
            res.sum = l.sum + r.sum;
            res.pref = Math.max(l.pref, l.sum + r.pref);
            res.suff = Math.max(r.suff, r.sum + l.suff);
            res.ans = Math.max(Math.max(l.ans, r.ans), l.suff + r.pref);
            return res;
        }
        SegmentTreeRMQ(int a[], int n) {
            tree = new Data[4 * n];
            this.a = new int[n];
            this.n = n;
            System.arraycopy(a, 0, this.a, 0, n);
            build(0, n - 1, 0);
        }
        void build(int s, int e, int c) {
            if (s == e) {
                tree[c] = makeData(a[s]);
                return;
            }
            int m = (s + e) >> 1;
            build(s, m, 2 * c + 1);
            build(m + 1, e, 2 * c + 2);
            tree[c] = combine(tree[2 * c + 1], tree[2 * c + 2]);
        }
        void update(int x, int v) {
            put(0, n - 1, 0, x, v);
        }
        void put(int s, int e, int c, int x, int v) {
            if (s == e) {
                tree[c] = makeData(v);
                return;
            }
            int m = (s + e) >> 1;
            if (x <= m) {
                put(s, m, 2 * c + 1, x, v);
            } else {
                put(m + 1, e, 2 * c + 2, x, v);
            }
            tree[c] = combine(tree[2 * c + 1], tree[2 * c + 2]);
        }
        Data get(int s, int e, int c, int l, int r) {
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
            return combine(get(s, m, 2 * c + 1, l, m), get(m + 1, e, 2 * c + 2, m + 1, r));
        }
        Data query(int l, int r) {
            return get(0, n - 1, 0, l, r);
        }
    }
    //------->ends !!
    void ace() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        solution();
        out.close();
    }
    byte inbuffer[] = new byte[1024];
    int lenbuffer = 0, ptrbuffer = 0;
    int readByte() {
        if (lenbuffer == -1)  throw new InputMismatchException();
        if (ptrbuffer >= lenbuffer) {
            ptrbuffer = 0;
            try {
                lenbuffer = obj.read(inbuffer);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
        }
        if (lenbuffer <= 0) return -1;
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
            if (b >= '0' && b <= '9') num = num * 10 + (b - '0');
            else {
                return minus ? -num : num;
            }
            b = readByte();
        }
    }
    int[] arri(int n) {
        int a[] = new int[n];
        for (int i = 0; i < n; i++) a[i] = inti();
        return a;
    }
}
