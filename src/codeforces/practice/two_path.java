package codeforces.practice;

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;

public class two_path {

    InputStream obj;
    PrintWriter out;
    String check = "";

    //Solution !!
    void solution() {
        int n = inti();
        n--;
        int from[] = new int[n];
        int to[] = new int[n];
        for (int i = 0; i < n; i++) {
            from[i] = inti() - 1;
            to[i] = inti() - 1;
        }
        long max = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int f[] = new int[n - 1];
            int t[] = new int[n - 1];
            count = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                f[count] = from[j];
                t[count++] = to[j];
            }
            int g[][] = packU(n + 1, f, t);
            int s[] = bfs(g, 0);
            int in = 0,value=0;
            for (int j = 0; j <= n; j++) {
                if (s[j] != Integer.MAX_VALUE / 2) {
                    if (s[j] >= value) {
                        in = j;
                        value = s[j];
                    }
                }
            }
//            pa(s);
            s = bfs(g, in);
            long max1 = 0, max2 = 0;
            for (int j = 0; j <= n; j++) {
                if (s[j] != Integer.MAX_VALUE / 2) {
                    max1 = Math.max(max1, s[j]);
                }
            }
            for (int j = 0; j <= n; j++) {
                if (s[j] == Integer.MAX_VALUE / 2) {
                    int s2[] = bfs(g, j);
                    int ind = 0, val = 0;
                    for (int k = 0; k <= n; k++) {
                        if (s2[k] != Integer.MAX_VALUE / 2) {
                            if (s2[k] >= val) {
                                ind = k;
                                val = s2[k];
                            }
                        }
                    }
//                    System.out.println(ind+" "+val);
//                    pa(s2);
                    s2 = bfs(g, ind);
//                    pa(s2);
                    for (int k = 0; k <= n; k++) {
                        if (s2[k] != Integer.MAX_VALUE / 2) {
                            max2 = Math.max(max2, s2[k]);
                        }
                    }
//                    pa(s);
//                    pa(s2);
//                    System.out.println(max2);
                    break;
                }
            }
//            System.out.println(max1 + " " + max2);
            max = Math.max(max1 * max2, max);
        }
        out.println(max);
    }

    int[] bfs(int[][] graph, int source) {
        int len = graph.length;
        int shortest_path[] = new int[len];
        int parent[] = new int[len];
        Arrays.fill(shortest_path, Integer.MAX_VALUE / 2);
        shortest_path[source] = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(source);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : graph[u]) {
                if (shortest_path[v] == Integer.MAX_VALUE / 2) {
                    q.add(v);
                    shortest_path[v] = shortest_path[u] + 1;
                    parent[v] = u;
                }
            }
        }
        return shortest_path;
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

    //------->ends !!
    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new two_path().ace();
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
