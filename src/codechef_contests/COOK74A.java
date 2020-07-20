package codechef_contests;

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;

class COOK74A {

    InputStream obj;
    PrintWriter out;
    String check = "";

    //Solution !!
    void solution() {
        OUTER:
        for (int tn = inti(); tn > 0; tn--) {
            int n = inti();
            int arr[] = arri(n);
            if (n <= 3) {
                Arrays.sort(arr);
                out.println(arr[0]);
                continue;
            }
            HashMap<Integer, Integer> hm = new HashMap<>();
            for (int i = 1; i < n; i++) {
                int d = arr[i] - arr[i - 1];
                if (hm.containsKey(d)) {
                    int num = hm.get(d);
                    hm.remove(d);
                    hm.put(d, num + 1);
                } else {
                    hm.put(d, 1);
                }
            }
            if (hm.size() > 3) {
                out.println(-1);
                continue;
            }
            if(hm.size()==1){
                Arrays.sort(arr);
                out.println(arr[0]);
                continue;
            }
            int max = -1, value = 0;
            boolean flag = false;
            for (Map.Entry<Integer, Integer> v : hm.entrySet()) {
                int a = v.getKey();
                int b = v.getValue();
                if (b > value && b>1) {
                    value = b;
                    max = a;
                }
            }
            if(max==-1){
                out.println(-1);
                continue;
            }
            boolean flag2 = false;

            for (Map.Entry<Integer, Integer> v : hm.entrySet()) {
                int a = v.getKey();
                int b = v.getValue();
                if (a == max) {
                    continue;
                }
                if (b == 2 && hm.size() == 3) {
                    flag = true;
                } else if (b > 2) {
                    flag = true;
                }
                if (b == 2) {
                    flag2 = true;
                }
            }
            if (flag) {
                out.println(-1);
                continue;
            }
            if (flag2) {
                for (int i = 1; i < n; i++) {
                    if (arr[i] - arr[i - 1] != max) {
                        if (i < n - 1 && arr[i] - arr[i - 1] != arr[i + 1] - arr[i]) {
//                            System.out.println((arr[i] - arr[i - 1])+" "+(arr[i + 1] - arr[i]));
                            out.println(-1);
                            continue OUTER;
                        }
                        break;
                    }
                }
            }
            int in = 0;
            for (int i = 1; i < n; i++) {
                if (arr[i] - arr[i - 1] == max) {
                    continue;
                }
                in = i;
                break;
            }
//            System.out.println(in+" sdf");
            if (in == n - 1) {
                out.println(arr[in]);
            } else if (arr[in + 1] - arr[in - 1] != max) {
                if (arr[in + 1] - arr[in] == max) {
                    out.println(arr[in - 1]);
                } else if (arr[in] - arr[in - 1] == max) {
                    out.println(arr[in + 1]);
                } else {
                    out.println(-1);
                }
            } else {
                out.println(arr[in]);
            }
        }
    }

    //------->ends !!
    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new COOK74A().ace();
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
