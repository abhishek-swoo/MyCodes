package Codeforces_contest;

/**
 *
 * @author Abhishek
 */
import java.io.*;
import java.util.*;

public class R383Div2C {

    InputStream obj;
    PrintWriter out;
    String check = "";

    //Solution !!
    void solution() {
        int n = inti();
        int to[] = new int[n];
        for (int i = 0; i < n; i++) {
            to[i] = inti() - 1;
        }
        int from[] = new int[n];
        for (int i = 0; i < n; i++) {
            from[i] = i;
        }
        int g[][] = packU(n, from, to);
        int a[] = decomposeToBECC(g);
        int in = 0;
//        pa(a);
//        pa(g);
        for (int i : a) {
            in = Math.max(in, i);
        }
        int fre[] = new int[in + 1];
        for (int i = 0; i < a.length; i++) {
            if (to[i] != i) {
                fre[a[i]]++;
            }
        }
        long max = 1, min = Integer.MAX_VALUE / 2;
        for (int i = 0; i < fre.length; i++) {
            if (fre[i] % 2 == 0 && fre[i]!=0) {
                max = lcm(max, fre[i]/2);
            } else if(fre[i]!=0){
                max = lcm(max, fre[i]);
            }
            if (to[i] != i && fre[i] != 0) {
                min = Math.min(min, fre[i]);
            }
        }
//        pa(fre);
        if (min == 1) {
            out.println(-1);
            return;
        }
        out.println(max);

    }

    public static int[] decomposeToBECC(int[][] g) {
        int n = g.length;
        boolean[] visited = new boolean[n];
        int[] ord = new int[n];
        int[] low = new int[n];

        int[] ids = new int[n];
        int[] inds = new int[n];
        int[] parct = new int[n];
        int pos = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                ids[0] = i;
                inds[0] = 0;
                int sp = 1;
                while (sp > 0) {
                    int cur = ids[sp - 1];
                    if (inds[sp - 1] == 0) {
                        visited[cur] = true;
                        ord[cur] = low[cur] = pos++;
                        parct[sp - 1] = 0;
                    }
                    if (inds[sp - 1] == g[cur].length) {
                        if (sp - 2 >= 0) {
                            low[ids[sp - 2]] = Math.min(low[ids[sp - 2]], low[cur]);
                        }
                        sp--;
                        continue;
                    }
                    int next = g[cur][inds[sp - 1]];
                    if (!visited[next]) {
                        ids[sp] = next;
                        inds[sp] = 0;
                        inds[sp - 1]++;
                        sp++;
                        continue;
                    } else if (sp - 2 >= 0 && (next != ids[sp - 2] || ++parct[sp - 1] >= 2)) {
                        low[cur] = Math.min(low[cur], ord[next]);
                    }
                    inds[sp - 1]++;
                }
            }
        }

        int[] clus = new int[n];
        Arrays.fill(clus, -1);
        int[] q = new int[n];
        int cnum = 0;
        for (int i = 0; i < n; i++) {
            if (clus[i] == -1) {
                int p = 0;
                q[p++] = i;
                clus[i] = cnum++;
                for (int r = 0; r < p; r++) {
                    int cur = q[r];
                    for (int next : g[cur]) {
                        if (clus[next] == -1) {
                            clus[next] = ord[cur] < low[next] ? cnum++ : clus[cur];
                            q[p++] = next;
                        }
                    }
                }
            }
        }
        return clus;
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
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new R383Div2C().ace();
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

    long gcd(long a, long b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }

    long lcm(long a, long b) {
        return a * (b / gcd(a, b));
    }
}
