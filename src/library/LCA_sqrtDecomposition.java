package library;

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;

class LCA_sqrtDecomposition {

    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new LCA_sqrtDecomposition().ace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (StackOverflowError e) {
                    System.out.println("RTE");
                }
            }
        }, "1", 1 << 26).start();
    }
    //Solution !!

    void solution() {
//        int t = inti();
//        for (int tt = 1; tt <= t; tt++) {
        int n = inti();
        int from[] = new int[n - 1];
        int to[] = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            from[i] = inti() - 1;
            to[i] = inti() - 1;
        }
        int g[][] = packU(n, from, to);
        int level[] = bfs(g, 0)[0];
        int parent[] = bfs(g, 0)[1];
        visit = new boolean[n];
        rootOfSubtree = new int[n];
        int height = -1;
        for (int i : level) {
            height = Math.max(height, i);
        }
        int h = (int) Math.sqrt(height);
        dfs(0, h, g, level, parent);
        for (int Q = inti(); Q > 0; Q--) {
            int root = inti() - 1, u = inti() - 1, v = inti() - 1;
            int minDist = Integer.MAX_VALUE / 2, lca = root;
            int node[] = {root, u, v, LCA(level, parent, u, v), LCA(level, parent, u, root), LCA(level, parent, v, root)};

            for (int x : node) {
                int dist = distance(parent, level, x, u);
                dist += distance(parent, level, x, v);
                dist += distance(parent, level, x, root);
                if (dist < minDist) {
                    minDist = dist;
                    lca = x;
                }
            }
            out.println(lca + 1);
        }
//        }
    }

    int[][] bfs(int[][] graph, int source) {
        int len = graph.length;
        int shortest_path[] = new int[len];
        int parent[] = new int[len];
        for (int i = 0; i < len; i++) {
            shortest_path[i] = Integer.MAX_VALUE / 2;
        }
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
        return new int[][]{shortest_path, parent};
    }

    int LCA(int level[], int parent[], int x, int y) {
        //as long as the node in the next section of x and y is not one common ancestor we get the node situated on the smaller lever closer
        while (rootOfSubtree[x] != rootOfSubtree[y]) {
            if (level[x] > level[y]) {
                x = rootOfSubtree[x];
            } else {
                y = rootOfSubtree[y];
            }
        }
        //now they are in the same section, so we trivially compute the LCA
        while (x != y) {
            if (level[x] > level[y]) {
                x = parent[x];
            } else {
                y = parent[y];
            }
        }
        return x;
    }

//    function for finding the root of subtrees
    boolean visit[];
    int rootOfSubtree[];

    void dfs(int node, int h, int g[][], int level[], int parent[]) {
        visit[node] = true;
        if (level[node] < h) {
//            rootOfSubtree[node] = 0;
            rootOfSubtree[node] = 1;
        } else if (level[node] % h == 0) {
            rootOfSubtree[node] = parent[node];
        } else {
            rootOfSubtree[node] = rootOfSubtree[parent[node]];
        }
        for (int i = 0; i < g[node].length; i++) {
            int v = g[node][i];
            if (!visit[v]) {
                dfs(v, h, g, level, parent);
            }
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

    int distance(int parent[], int level[], int x, int y) {
        //in rooted tree distance=level
        int lca = LCA(level, parent, x, y);
        return level[x] + level[y] - 2 * level[lca];
    }

    //------->ends !!
    void ace() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
//        out=new PrintWriter("C:\\Users\\Abhishek Shankhadhar\\Desktop\\a\\in1b.txt");
//        obj=check.isEmpty() ? new FileInputStream("C:\\Users\\Abhishek Shankhadhar\\Desktop\\a\\g2.in") : new ByteArrayInputStream(check.getBytes());
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

    private static void tr(Object... o) {
        System.out.println(Arrays.deepToString(o));
    }
}
