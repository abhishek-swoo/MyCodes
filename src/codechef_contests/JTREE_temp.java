package codechef_contests;

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;

class JTREE_temp {

    InputStream obj;
    PrintWriter out;
    String check = "";
    int distance[];
    int parent[];
    int g[][];
    ArrayList<Integer>[] arr;
    long dp[][];
    int n, m;
    int max;
    //Solution !!
    void solution() {
        n = inti();
        m = inti();
        int from[] = new int[n - 1];
        int to[] = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            from[i] = inti() - 1;
            to[i] = inti() - 1;
        }
        
        g = packD(n, from, to, n - 1);
        int revG[][] = packD(n, to, from, n - 1);
        arr = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int a = inti() - 1, k = inti(), w = inti();
            arr[a].add(k);
            arr[a].add(w);
        }
        distance = bfs(revG, 0)[0];
        max=0;
        for(int i=0;i<n;i++){
            max=Math.max(max, distance[i]);
        }
        parent = bfs(revG, 0)[1];
        dp = new long[n][max+1];
        initialize();
        int q = inti();
        for (int i = 0; i < q; i++) {
            int a = inti() - 1;
            if (a == 0) {
                out.println(0);
                continue;
            }
            long u = Long.MAX_VALUE;
            for (int j = 0; j < arr[a].size(); j += 2) {
                u = Math.min(u, arr[a].get(1) + solve(parent[a], arr[a].get(0)));
            }
            out.println(u);
        }
    }

    void initialize() {
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
    }

    long solve(int node, int edge) {
        if (edge > max) {
            edge = max;
        }
        if (node == 0) {
            return 0;
        }
        if (dp[node][edge] != -1) {
            return dp[node][edge];
        }
        if (edge == 1) {
            long u = Integer.MAX_VALUE / 2;
            for (int i = 0; i < arr[node].size(); i += 2) {
                u = Math.min(arr[node].get(i + 1) + solve(parent[node], arr[node].get(i)), u);
            }
            return dp[node][edge] = u;
        }
        long u = Integer.MAX_VALUE / 2;
        for (int i = 0; i < arr[node].size(); i += 2) {
            u = Math.min(arr[node].get(i + 1) + solve(parent[node], arr[node].get(i)), u);
        }
        return dp[node][edge] = Math.min(u, solve(parent[node], edge - 1));
    }

    int[][] bfs(int[][] graph, int source) {
        int len = graph.length;
        int shortest_path[] = new int[len];
        int child[] = new int[len];
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
                    child[v] = u;
                }
            }
        }
        return new int[][]{shortest_path, child};
    }

    static int[][] packD(int n, int[] from, int[] to, int max) {
        /* This part of code is picked up from "uwi" previous submission */
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int i = 0; i < max; i++) {
            p[from[i]]++;
        }
        for (int i = 0; i < n; i++) {
            g[i] = new int[p[i]];
        }
        for (int i = 0; i < max; i++) {
            g[from[i]][--p[from[i]]] = to[i];
        }
        return g;
    }

    //------->ends !!
    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new JTREE_temp().ace();
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
