
/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;

class ittiam2B {
    
    InputStream obj;
    PrintWriter out;
    String check = "";
    int a[];
    //Solution !!
    void solution() {
        long n=loni();
        int m=inti();
        long[] arr=arrl(m);
        a=new int[101];
        for(long i:arr){
            a[(int)i-1]+=1;
        }
//        pa(a);
        solve(n);
    }
    int N = 101;
    void matrixmul(long out[][], long a[][], long b[][]) {
        long temp[][] = new long[N][N];
        int i, j, k;
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                temp[i][j] = 0;
            }
        }
        for (k = 0; k < N; k++) {
            for (i = 0; i < N; i++) {
                for (j = 0; j < N; j++) {
                    temp[i][j] += ((a[i][k] * b[k][j])%mod);
                    temp[i][j]%=mod;
                }
            }
        }
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                out[i][j] = temp[i][j];
            }
        }
    }

    /*  take the in matrix to the pow-th power, return to out */
    void matrixpow(long out[][], long in[][], long pow) {
        long sq[][] = new long[N][N], temp[][] = new long[N][N];
        int i, j;
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                temp[i][j] = i == j ? 1 : 0;
            }
        }
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                sq[i][j] = in[i][j];
            }
        }
        while (pow > 0) {
            if ((pow & 1) == 1) {
                matrixmul(temp, temp, sq);
            }
            matrixmul(sq, sq, sq);
            pow >>= 1;
        }
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                out[i][j] = temp[i][j];
            }
        }
    }
    int mod=(int)1e9+7;
    void solve(long n) {
        long m[][] = new long[N][N];
        int i, j;
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                m[i][j] = 0;
            }
        }
        /*  create matrix from a[] array above */
        for (i = 2; i <= N; i++) {
            m[i - 2][i - 1] = 1;
        }
        for (i = 1; i <= N; i++) {
            m[N - 1][N - i] = a[i - 1];
        }
        matrixpow(m, m, n);
        System.out.println(m[N - 1][N - 1]%mod);
    }

    //------->ends !!
    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new ittiam2B().ace();
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
