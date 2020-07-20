package spoj;

/**
 *
 * @author Abhishek
 */
import java.io.*;
import java.util.*;

class INVCNT_bit {

    InputStream obj;
    PrintWriter out;
    String check = "";

    //Solution !!
    void solution() {
        int t = inti();
        for (int tt = 0; tt < t; tt++) {
            int n = inti();
            int input[] = arri(n);
            input = shrink(input);
            fenwickTree ft = new fenwickTree();
            int tree[] = new int[200002];
            long sum = 0;
            for (int i = 0; i < n; i++) {
                ft.updateBinaryIndexedTree(tree, 1, input[i] + 1);
//                if (input[i] > 1) {
                    sum += (ft.getSum(tree, 200000) - ft.getSum(tree, input[i] ));
//                } else {
//                    sum += ft.getSum(tree, 200000);
//                }
            }
            out.println(sum);
        }
    }

    public static int[] shrink(int[] a) {
        int n = a.length;
        long[] b = new long[n];
        for (int i = 0; i < n; i++) {
            b[i] = (long) a[i] << 32 | i;
        }
        Arrays.sort(b);
        int[] ret = new int[n];
        int p = 0;
        for (int i = 0; i < n; i++) {
            if (i > 0 && (b[i] ^ b[i - 1]) >> 32 != 0) {
                p++;
            }
            ret[(int) b[i]] = p;
        }
        return ret;
    }

    class fenwickTree {

        public int getParent(int index) {
            return index - (index & -index);
        }

        public int getNext(int index) {
            return index + (index & -index);
        }

        public int[] updateBinaryIndexedTree(int bit[], int val, int index) {
            while (index < bit.length) {
                bit[index] += val;
                index = getNext(index);
            }
            return bit;
        }

        public int getSum(int bit[], int index) {
            index++;
            int sum = 0;
            while (index > 0) {
                sum += bit[index];
                index = getParent(index);
            }
            return sum;
        }

//        public int[] createTree(int input[]) {
//            int bit[] = new int[200002];
//            for (int i = 1; i <= input.length; i++) {
//                updateBinaryIndexedTree(bit, 1, input[i - 1]);
//            }
//            return bit;
//        }
    }

    //------->ends !!
    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new INVCNT_bit().ace();
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
