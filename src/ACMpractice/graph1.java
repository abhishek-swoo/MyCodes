package ACMpractice;

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;
//import standard.graph;

public class graph1 {

    InputStream obj;
    PrintWriter out;
    String check = "";

    //Solution !!
//    ArrayList<Integer> graph[];

    void solution() {
        int n = inti(), m = inti(), l=inti();
        int from[]=new int[m];
        int to[]=new int[m];
        int wt[]=new int[m];
        WeightedAdjListGraph g = new WeightedAdjListGraph(n, 2 * m);
        for (int i = 0; i < m; i++) {
            int s = inti() - 1;
            int t = inti() - 1;
            int we = inti();
            from[i]=s;
            to[i]=s;
            g.addEdge(s, t, we);
            g.addEdge(t, s, we);
        }
        int source=inti()-1;
        long[] d = g.dijkstra(source)[0];
        int count=0;
        for(int i=0;i<m;i++){
            if(d[from[i]]<l && d[from[i]]+wt[i]>l)
                count++;
            if(d[to[i]]<l && d[to[i]]+wt[i]>l)
                count++;
            if(d[from[i]]<l && d[to[i]]<l && l-d[from[i]]+l-d[to[i]]==wt[i])
                count--;
        }
        for(int i=0;i<n;i++){
            if(d[i]==l)
                count++;
        }
        out.println(count);
    }
    
    
    static final class WeightedAdjListGraph {

        int m, V, E;
        int[] head, next, cost, to;

        public WeightedAdjListGraph(int V, int E) {
            head = new int[V];
            next = new int[E];
            cost = new int[E];
            to = new int[E];
            this.V = V;
            this.E = E;
            clear();
        }

        public void clear() {
            m = 0;
            Arrays.fill(head, -1);
        }

        public void addEdge(int s, int t, int c) {
            next[m] = head[s];
            cost[m] = c;
            head[s] = m;
            to[m++] = t;
        }

        public long[][] dijkstra(final int s) {
            final long[] dist = new long[head.length];
            final long[] prev = new long[head.length];
            final PriorityQueue<int[]> q = new PriorityQueue<>(10, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });

            Arrays.fill(dist, Integer.MAX_VALUE);
            Arrays.fill(prev, -1);
            dist[s] = 0;
            q.add(new int[]{0, s,});
            while (!q.isEmpty()) {
                final int[] xs = q.poll();
                final int v = xs[1];
                final int d = xs[0];
                if (dist[v] < d) {
                    continue;
                }

                for (int e = head[v]; e != -1; e = next[e]) {
                    final int t = to[e];
                    final int w = d + cost[e];
                    if (w < dist[t]) {
                        dist[t] = w;
                        prev[t] = v;
                        q.add(new int[]{w, t,});
                    }
                }
            }
            for (int i = 0; i < dist.length; i++) {
                if (dist[i] == Integer.MAX_VALUE) {
                    dist[i] = -1;
                }
            }
            return new long[][]{dist, prev};
        }

        public int[][] bfs(final int src) {
            final int[] dist = new int[head.length];
            final int[] prev = new int[head.length];
            final int[] q = new int[2 * head.length + 10];
            int s = 0, t = 0;

            Arrays.fill(dist, -1);
            Arrays.fill(prev, -1);
            dist[src] = 0;
            q[t++] = src;
            while (s != t) {
                final int v = q[s++];
                for (int e = head[v]; e != -1; e = next[e]) {
                    final int u = to[e];
                    if (dist[u] == -1) {
                        dist[u] = dist[v] + 1;
                        prev[u] = v;
                        q[t++] = u;
                    }
                }
            }
            return new int[][]{dist, prev};
        }

    }

    //------->ends !!
    public static void main(String[] args) throws IOException {
        new Thread(null, () -> {
            try {
                new graph1().ace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (StackOverflowError e) {
                System.out.println("RTE");
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
