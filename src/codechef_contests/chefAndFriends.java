package codechef_contests;

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;

class chefAndFriends {

    InputStream obj;
    PrintWriter out;
    String check = "";

    //Solution !!
    void solution() {
        for (int tn = inti(); tn > 0; tn--) {
            int n = inti(), m = inti();
            HashSet<String> hs = new HashSet<>();
            for (int i = 0; i < m; i++) {
                int a = inti() - 1, b = inti() - 1;
                if (a != b) {
                    hs.add(a + " " + b);
                    hs.add(b + " " + a);
                }
            }
//            int size = 0;
//            for (int i = 0; i < n; i++) {
//                for (int j = i + 1; j < n; j++) {
//                    if (hs.contains(i + " " + j)) {
//                        continue;
//                    }
//                    size++;
//                }
//            }
            int size = (((n * n) - n) / 2) - (hs.size() / 2);
            int from[] = new int[size];
            int to[] = new int[size];
            int in = 0;
//            System.out.println(size);
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (hs.contains(i + " " + j)) {
                        continue;
                    }
                    from[in] = i;
                    to[in++] = j;
                }
            }
            int g[][] = packU(n, from, to);
            boolean flag = true;
            color = new int[g.length];
//            for (int i = 0; i < color.length; i++) {
//                color[i] = Integer.MAX_VALUE / 2;
            Arrays.fill(color, Integer.MAX_VALUE / 2);
//            }
            for (int i = 0; i < n; i++) {
                if (color[i] == Integer.MAX_VALUE / 2) {
                    flag = isBipartite(g, i);
                    if (!flag) {
                        break;
                    }
                }
            }

            if (flag) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
    }
    int color[];

    boolean isBipartite(int graph[][], int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        boolean bipartite = true;

        color[s] = 0;
        while (!queue.isEmpty() && bipartite) {
            int u = queue.poll();
            for (int v : graph[u]) {
                if (color[v] == Integer.MAX_VALUE / 2) {
                    color[v] = 1 - color[u];
                    queue.add(v);
                } else if (color[v] == color[u]) {
//                    bipartite=false;
//                    break;
                    return false;
                }
            }
        }
        return bipartite;
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

    //------->ends !!
    public static void main(String[] args) throws IOException {
//        new Thread(null, new Runnable() {
//            public void run() {
//                try {
        new chefAndFriends().ace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (StackOverflowError e) {
//                    System.out.println("RTE");
//                }
//            }
//        }, "1", 1 << 26).start();
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
