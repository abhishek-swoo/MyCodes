

package spoj;

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;
class MTOTALF {

    InputStream obj; 
    PrintWriter out;
    String check = "";


    //Solution !!

    void solution() {
        int n=inti();
        List<Edge> es = new ArrayList<>();
        HashMap<Character,Integer> hh=new HashMap<>();
        int count=0;
        for(int i=0;i<n;i++){
            char a=chi(),b=chi();
            int c=inti();
            int u,v;
            if(hh.containsKey(a)){
                u=hh.get(a);
            }
            else{
                hh.put(a, count);
                u=count++;
            }
            if (hh.containsKey(b)) {
                v=hh.get(b);
            }
            else{
                hh.put(b, count);
                v=count++;
            }
            
            es.add(new Edge(u, v, c));
        }
        Edge[][] g=compileWU(n, es);
        out.println(maximumFlowDinic(g, hh.get('A'), hh.get('Z')));
    }
    public static long maximumFlowDinic(Edge[][] g, int source, int sink) {
        int n = g.length;
        int[] d = new int[n];
        int[] q = new int[n];
        long ret = 0;
        while (true) {
            Arrays.fill(d, -1);
            q[0] = source;
            int r = 1;
            d[source] = 0;

            for (int v = 0; v < r; v++) {
                int cur = q[v];
                for (Edge ne : g[cur]) {
                    if (d[ne.to] == -1 && ne.capacity - ne.flow > 0) {
                        q[r++] = ne.to;
                        d[ne.to] = d[cur] + 1;
                    }
                }
            }
            if (d[sink] == -1) {
                break;
            }
            int[] sp = new int[n];
            for (int i = 0; i < n; i++) {
                sp[i] = g[i].length - 1;
            }
            ret += dfsDinic(d, g, sp, source, sink, Long.MAX_VALUE);
        }

        return ret;
    }

    private static long dfsDinic(int[] d, Edge[][] g, int[] sp, int cur, int sink, long min) {
        if (cur == sink) {
            return min;
        }
        long left = min;
        for (int i = sp[cur]; i >= 0; i--) {
            Edge ne = g[cur][i];
            if (d[ne.to] == d[cur] + 1 && ne.capacity - ne.flow > 0) {
                long fl = dfsDinic(d, g, sp, ne.to, sink, Math.min(left, ne.capacity - ne.flow));
                if (fl > 0) {
                    left -= fl;
                    ne.flow += fl;
                    ne.complement.flow -= fl;
                    if (left == 0) {
                        sp[cur] = i;
                        return min;
                    }
                }
            }
        }
        sp[cur] = -1;
        return min - left;
    }

    public static Edge[][] compileWU(int n, List<Edge> edges) {
        Edge[][] g = new Edge[n][];
        // cloning
        for (int i = edges.size() - 1; i >= 0; i--) {
            Edge origin = edges.get(i);
            Edge clone = new Edge(origin.to, origin.from, origin.capacity * 2);
            origin.flow = origin.capacity;
            clone.flow = origin.capacity;
            clone.complement = origin;
            origin.complement = clone;
            origin.capacity *= 2;
            edges.add(clone);
        }

        int[] p = new int[n];
        for (Edge e : edges) {
            p[e.from]++;
        }
        for (int i = 0; i < n; i++) {
            g[i] = new Edge[p[i]];
        }
        for (Edge e : edges) {
            g[e.from][--p[e.from]] = e;
        }
        return g;
    }
    public static class Edge {

        public int from, to;
        public int capacity;
        public int flow;
        public Edge complement;
        // public int iniflow;

        public Edge(int from, int to, int capacity) {
            this.from = from;
            this.to = to;
            this.capacity = capacity;
        }
    }

    //------->ends !!


    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new MTOTALF().ace();
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
