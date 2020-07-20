
/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;

class morganA {

    InputStream obj;
    PrintWriter out;
    String check = "";

    //Solution !!
    void solution() {
        int n = inti(), d = inti();
        int arr[] = arri(n);
        HashMap<Integer, ArrayList<Integer>> hm = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if (hm.containsKey(arr[i])) {
                ArrayList<Integer> al = hm.get(arr[i]);
                al.add(i);
                hm.put(arr[i], al);
            } else {
                ArrayList<Integer> al = new ArrayList<>();
                al.add(i);
                hm.put(arr[i], al);
            }
        }
//        pa(hm);
        for (int i = 0; i < d; i++) {
            int a = inti();
            int min = 0, max = Integer.MAX_VALUE / 2;
            for (int j = 0; j < n; j++) {
                int u = arr[j];
                int index = a + u;
                if (!hm.containsKey(index)) {
                    continue;
                }
                ArrayList<Integer> al = hm.get(index);
                int in = BinarySearchLowerBound(al, j + 1);
                int size = al.size();
                if (in == size || al.get(in) < j) {
                    continue;
                }
                if ((max - min) > (al.get(in)-j)) {
                    max = al.get(in);
                    min = j;
                }

            }
            if(max==Integer.MAX_VALUE/2){
                out.println(-1);
                continue;
            }
            out.println((min+1) + " " + (max+1));
        }
    }

    public static int BinarySearchLowerBound(ArrayList<Integer> a, int v) {
        int low = -1, high = a.size();
        while (high - low > 1) {
            int h = high + low >>> 1;
            if (a.get(h) >= v) {
                high = h;
            } else {
                low = h;
            }
        }
        return high;
    }

    public static int[][] makeBuckets(int[] a, int sup) {
        int n = a.length;
        int[][] bucket = new int[sup + 1][];
        int[] bp = new int[sup + 1];
        for (int i = 0; i < n; i++) {
            bp[a[i]]++;
        }
        for (int i = 0; i <= sup; i++) {
            bucket[i] = new int[bp[i]];
        }
        for (int i = n - 1; i >= 0; i--) {
            bucket[a[i]][--bp[a[i]]] = i;
        }
        return bucket;
    }
    //------->ends !!

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new morganA().ace();
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
