package spoj;

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;

class MATCHING {

    InputStream obj;
    PrintWriter out;
    String check = "";

    //Solution !!
    void solution() {
        int n = inti(), m = inti(), p = inti();
        int from[]=new int[p];
        int to[]=new int[p];
        for(int i=0;i<p;i++){
            from[i]=inti()-1;
            to[i]=inti()-1;
        }
        int[][] g=packD(n, from, to);
        out.println(doBipartiteMatchingHKNoRec(g, m));
    }

    static int[][] packD(int n, int[] from, int[] to) {
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int f : from) {
            p[f]++;
        }
        for (int i = 0; i < n; i++) {
            g[i] = new int[p[i]];
        }
        for (int i = 0; i < from.length; i++) {
            g[from[i]][--p[from[i]]] = to[i];
        }
        return g;
    }

    public static int doBipartiteMatchingHKNoRec(int[][] g, int m) {
        int n = g.length;
        if (n == 0) {
            return 0;
        }
        int[] from = new int[m];
        int[] to = new int[n];
        Arrays.fill(to, -1);
        Arrays.fill(from, n);

        int[] d = new int[n + 1];
        int mat = 0;
        int[] stack = new int[n + 1];
        int[] adjind = new int[n + 1];
        while (true) {
            Arrays.fill(d, -1);
            int[] q = new int[n];
            int r = 0;
            for (int i = 0; i < n; i++) {
                if (to[i] == -1) {
                    d[i] = 0;
                    q[r++] = i;
                }
            }

            for (int p = 0; p < r; p++) {
                int cur = q[p];
                for (int adj : g[cur]) {
                    int nex = from[adj];
                    if (d[nex] == -1) {
                        if (nex != n) {
                            q[r++] = nex;
                        }
                        d[nex] = d[cur] + 1;
                    }
                }
            }
            if (d[n] == -1) {
                break;
            }

            for (int i = 0; i < n; i++) {
                if (to[i] == -1) {
                    int sp = 1;
                    stack[0] = i;
                    adjind[0] = 0;
                    boolean prevB = false;
                    outer:
                    while (sp >= 1) {
                        int cur = stack[sp - 1];
                        if (cur == n) {
                            prevB = true;
                            sp--;
                            continue;
                        }
                        for (; adjind[sp - 1] < 2 * g[cur].length;) {
                            int adj = g[cur][adjind[sp - 1] / 2];
                            if (adjind[sp - 1] % 2 == 0) {
                                int nex = from[adj];
                                if (d[nex] == d[cur] + 1) {
                                    stack[sp] = nex;
                                    adjind[sp] = 0;
                                    adjind[sp - 1]++;
                                    sp++;
                                    continue outer;
                                } else {
                                    adjind[sp - 1] += 2;
                                }
                            } else {
                                if (prevB) {
                                    to[cur] = adj;
                                    from[adj] = cur;
                                    prevB = true;
                                    sp--;
                                    continue outer;
                                }
                                adjind[sp - 1]++;
                            }
                        }
                        d[cur] = -1;
                        prevB = false;
                        sp--;
                    }
                    if (prevB) {
                        mat++;
                    }
                }
            }
        }

        return mat;
    }

    //------->ends !!
    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new MATCHING().ace();
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
