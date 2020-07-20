package ACMpractice;

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;

public class graph5C {

    InputStream obj;
    PrintWriter out;
    String check = "";

    //Solution !!
    int cpy[][];
    boolean grid[][];
    boolean visited[][];

    void solution() {
        int n = inti(), m = inti();
        int arr[][] = new int[n][m];
        visited = new boolean[n][m];
        grid = new boolean[n][m];
        cpy = new int[n][m];
        int as = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = chi();
                if (c == '#') {
                    arr[i][j] = 1;
                    as++;
                }
            }
        }
        copy(arr, n, m);
        if (as <= 2) {
            out.println(-1);
            return;
        }

        int a = 0, prevx = -1, prevy = -1, prevx2 = 0, prevy2 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (prevx == -1 && prevy == -1 && cpy[i][j] == 1) {
                    prevx = i;
                    prevy = j;
                }
                if (cpy[i][j] == 1 && a == 0) {
                    a++;
                    continue;
                }
                if (cpy[i][j] == 1) {
                    prevx2 = i;
                    prevy2 = j;
                    vis(n, m);
                    copy(arr, n, m);
                    cpy[i][j] = 0;

//                    System.out.println(i + " " + j + " " + prevx + " " + prevy);
//                    pa(cpy);
                    boolean flag = bfs(prevx, prevy, n, m);
                    if (!flag) {
                        out.println(1);
                        return;
                    }
                }
            }
        }
        vis(n, m);
        copy(arr, n, m);
        cpy[prevx][prevy] = 0;

//        System.out.println(prevx + " " + prevy + " " + prevx2 + " " + prevy2);
        boolean flag = bfs(prevx2, prevy2, n, m);
//        System.out.println(flag);
//        pa(visited);
        if (!flag) {
            out.println(1);
            return;
        }
        out.println(2);
    }

    void vis(int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = false;
            }
        }
    }

    boolean bfs(int x, int y, int n, int m) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        q1.add(x);
        q2.add(y);
        while (!q1.isEmpty()) {
            int u = q1.poll();
            int v = q2.poll();
            if (u - 1 >= 0 && !visited[u - 1][v] && cpy[u - 1][v] == 1) {
                q1.add(u - 1);
                q2.add(v);
                visited[u - 1][v] = true;
            }
            if (u + 1 < n && !visited[u + 1][v] && cpy[u + 1][v] == 1) {
                q1.add(u + 1);
                q2.add(v);
                visited[u + 1][v] = true;
            }
            if (v - 1 >= 0 && !visited[u][v - 1] && cpy[u][v - 1] == 1) {
                q1.add(u);
                q2.add(v - 1);
                visited[u][v - 1] = true;
            }
            if (v + 1 < m && !visited[u][v + 1] && cpy[u][v + 1] == 1) {
                q1.add(u);
                q2.add(v + 1);
                visited[u][v + 1] = true;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (cpy[i][j] == 1 && !visited[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    void copy(int arr[][], int n, int m) {
        for (int i = 0; i < n; i++) {
            System.arraycopy(arr[i], 0, cpy[i], 0, m);
        }
    }

    //------->ends !!
    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new graph5C().ace();
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
