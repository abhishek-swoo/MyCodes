package ACMpractice;

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;

public class graph5B {

    InputStream obj;
    PrintWriter out;
    String check = "";

    //Solution !!
    int count = 0;
    int num[];
    int low[];
    int parent[];
    int rootChild = 0;
    int p = 0;

    int root = -1;

    void solution() {
        int n = inti(), m = inti();
        int count1 = 1;
        int as = 0;
        int arr[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = chi();
                if (c == '#') {
                    as++;
                    arr[i][j] = count1++;
                }
            }
        }

        if (as <= 1) {
            out.println(-1);
            return;
        }
        int from[] = new int[(count1 - 1) * 4];
        int to[] = new int[(count1 - 1) * 4];
        int in = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 0) {
                    continue;
                }
                if (i - 1 >= 0 && arr[i - 1][j] != 0) {
                    from[in] = arr[i][j] - 1;
                    to[in++] = arr[i - 1][j] - 1;
                }
//                if(i+1<n && arr[i+1][j]!=0){
//                    from[in]=arr[i][j]-1;
//                    to[in++]=arr[i+1][j]-1;
//                }
//                if(j+1<m && arr[i][j+1]!=0){
//                    from[in]=arr[i][j]-1;
//                    to[in++]=arr[i][j+1]-1;
//                }
                if (j - 1 >= 0 && arr[i][j - 1] != 0) {
                    from[in] = arr[i][j] - 1;
                    to[in++] = arr[i][j - 1] - 1;
                }
            }
        }
        int pre = 1;
        num = new int[count1 - 1];
        for (int i = 0; i < count1 - 1; i++) {
            num[i] = -1;
        }
        low = new int[count1 - 1];
        parent = new int[count1 - 1];
        for (int i = 1; i < (count1 - 1) * 4; i++) {
            if (from[i] == 0) {
                break;
            }
            pre++;
        }
        int from1[] = new int[pre];
        int to1[] = new int[pre];
        for (int i = 0; i < pre; i++) {
            from1[i] = from[i];
            to1[i] = to[i];
        }
        int[][] graph = packU(count1 - 1, from1, to1);
        for (int i = 0; i < graph.length; i++) {
            if (num[i] == -1) {
                bridgeFinding(graph, i);
            }
        }
        if (p == 0) {
            out.println(2);
        } else {
            out.println(1);
        }
    }

    static int[][] packU(int n, int[] from, int[] to) {
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

    void bridgeFinding(int g[][], int u) {
        num[u] = count++;
        low[u] = num[u];
        for (int v : g[u]) {
            if (num[v] == -1) {
                parent[v] = u;
                if (u == root) {
                    rootChild++;
                }
                bridgeFinding(g, v);
                if (low[v] > num[u]) {
//                    a.add(u);
//                    b.add(v);
//                    out.println(u+" "+v);
                    p++;
                }
                low[u] = Math.min(low[u], low[v]);
            } else if (v != parent[u]) {
                low[u] = Math.min(low[u], num[v]);
            }
        }
    }

    //------->ends !!
    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new graph5B().ace();
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
}
