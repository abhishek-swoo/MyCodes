package codesprint;

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;

class TOH {

    InputStream obj;
    PrintWriter out;
    String check = "";
    int min = Integer.MAX_VALUE;

    //Solution !!
    void solution() {
        int n = inti();
        Stack<Integer> st1 = new Stack<>();
        Stack<Integer> st2 = new Stack<>();
        Stack<Integer> st3 = new Stack<>();
        Stack<Integer> st4 = new Stack<>();
        int arr[] = arri(n);
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] == 1) {
                st1.push(i + 1);
            } else if (arr[i] == 2) {
                st2.push(i + 1);
            } else if (arr[i] == 3) {
                st3.push(i + 1);
            } else if (arr[i] == 4) {
                st4.push(i + 1);
            }
        }
        rec(st1, st2, st3, st4, n, 1);
        out.println(min);
    }

    void rec(Stack<Integer> st1, Stack<Integer> st2, Stack<Integer> st3, Stack<Integer> st4, int n, int i) {
//        System.out.println(i);
        if (i > 10) {
            return;
        }
        if (st1.size()==n) {
            System.out.println("ksdhsjdk");
            if (i < min) {
                min = i;
            }
        }
        System.out.println(st1.size());
        if (!st1.isEmpty() && !check(st1, n)) {
//            System.out.println(i);
            int u = st1.pop();
            if (st2.isEmpty() || st2.peek() < u) {
                st2.push(u);
                rec(st1, st2, st3, st4, n, i + 1);
            }
            if (st3.isEmpty() || st3.peek() < u) {
                st3.push(u);
                rec(st1, st2, st3, st4, n, i + 1);
            }
            if (st4.isEmpty() || st4.peek() < u) {
                st4.push(u);
                rec(st1, st2, st3, st4, n, i + 1);
            }
        }
        if (st1.size()==n) {
            System.out.println("ksdhsjdk");
            if (i < min) {
                min = i;
            }
        }
        if (!st2.isEmpty()) {
//            System.out.println(i);
            int v = st2.pop();
            if (st1.isEmpty() || st1.peek() < v) {
                st1.push(v);
                rec(st1, st2, st3, st4, n, i + 1);
            }
            if (st3.isEmpty() || st3.peek() < v) {
                st3.push(v);
                rec(st1, st2, st3, st4, n, i + 1);
            }
            if (st4.isEmpty() || st4.peek() < v) {
                st4.push(v);
                rec(st1, st2, st3, st4, n, i + 1);
            }
            if (st1.size()==n) {
//                System.out.println("ksdhsjdk");
                if (i < min) {
                    min = i;
                }
            }

        }
        if (!st3.isEmpty()) {
            int w = st3.pop();
            if (st1.isEmpty() || st1.peek() < w) {
                st1.push(w);
                rec(st1, st2, st3, st4, n, i + 1);
            }
            if (st2.isEmpty() || st2.peek() < w) {
                st2.push(w);
                rec(st1, st2, st3, st4, n, i + 1);
            }
            if (st4.isEmpty() || st4.peek() < w) {
                st4.push(w);
                rec(st1, st2, st3, st4, n, i + 1);
            }
            if (st1.size()==n) {
                if (i < min) {
                    min = i;
                }
            }
        }
        if (!st4.isEmpty()) {
//            System.out.println(i);
            int x = st4.pop();
            if (st1.isEmpty() || st1.peek() < x) {
                st1.push(x);
                rec(st1, st2, st3, st4, n, i + 1);
            }
            if (st3.isEmpty() || st3.peek() < x) {
                st3.push(x);
                rec(st1, st2, st3, st4, n, i + 1);
            }
            if (st4.isEmpty() || st4.peek() < x) {
                st4.push(x);
                rec(st1, st2, st3, st4, n, i + 1);
            }
        }
        if (st1.size()==n) {
            if (i < min) {
                min = i;
            }
        }
    }

    boolean check(Stack<Integer> st1, int n) {
//        System.out.println("ksdhsjdk");
        if (st1.size() == n) {
            return true;
        }
        return (n - st1.size()) == st1.peek() - 1;
    }

    boolean complete(Stack<Integer> st1, int n) {
        return st1.size() == n;
    }

    //------->ends !!
    public static void main(String[] args) throws IOException {
        new Thread(null, () -> {
            try {
                new TOH().ace();
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
