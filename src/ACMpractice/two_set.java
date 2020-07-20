package ACMpractice;

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;

public class two_set {

    InputStream obj;
    PrintWriter out;
    String check = "";

    //Solution !!
    void solution() {
        int n = inti(), a = inti(), b = inti();
        int arr[] = arri(n);
        int mark[] = new int[n];
        TreeSet<Integer> tree = new TreeSet<>();
        HashSet<Integer> h1 = new HashSet<>();
        HashSet<Integer> h2 = new HashSet<>();
        for (int i : arr) {
            tree.add(i);
        }
        boolean flag = false;
        while (!tree.isEmpty()) {
            int a1 = tree.first(), a2 = a - a1, b1 = tree.first(), b2 = b - b1;
            if (tree.contains(a2) && tree.contains(b2)) {
                if (a2 > b2) {
                    h1.add(a2);
                    h1.add(a1);
                    tree.remove(a1);
                    tree.remove(a2);
                } else {
                    h2.add(b2);
                    h2.add(b1);
                    tree.remove(b1);
                    tree.remove(b2);
                }
            } else if (tree.contains(a2)) {
                h1.add(a2);
                h1.add(a1);
                tree.remove(a1);
                tree.remove(a2);
            } else if (tree.contains(b2)) {
                h2.add(b2);
                h2.add(b1);
                tree.remove(b1);
                tree.remove(b2);
            } else {
                flag = true;
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (h2.contains(arr[i])) {
                mark[i] = 1;
            }
        }
        if (flag) {
            out.println("NO");
        } else {
            out.println("YES");
            for (int i = 0; i < n; i++) {
                out.print(mark[i] + " ");
            }
        }
    }

    //------->ends !!
    public static void main(String[] args) throws IOException {
        new Thread(null, () -> {
            try {
                new two_set().ace();
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
