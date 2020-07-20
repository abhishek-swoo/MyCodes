
/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.ByteArrayInputStream;
//import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Arrays;

class mo_s {

    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new mo_s().ace();
    }
    //Solution !!
    int[] arr;
    int[] frequency = new int[1000005];

    void solution() {
        int n = inti();
        arr = arri(n);
        int d = inti();
        int[][] QueryL = new int[d][2];
        int[] QueryR = new int[d];
        for (int i = 0; i < d; i++) {
            QueryL[i][0] = inti();
            QueryL[i][1] = i;
            QueryR[i] = inti();
        }
        int SIZE_B = (int) Math.floor(Math.sqrt(n));
        long mo_answer[] = new long[d];
        Arrays.sort(QueryL, (int a[], int b[]) -> {
            int block_a = a[0] / SIZE_B;
            int block_b = b[0] / SIZE_B;
            if (block_a != block_b) {
                if (block_a < block_b) {
                    return 1;
                }
                return -1;
            }
            if (QueryR[a[1]] < QueryR[b[1]]) {
                return 1;
            }
            return -1;
        });
        int mo_left = 0, mo_right = -1;
        for (int i = 0; i < d; i++) {
            int left = QueryL[i][0];
            int right = QueryR[QueryL[i][1]];
            while (mo_right < right) {
                mo_right++;
//                add(arr[mo_right]);
            }
            while (mo_right > right) {
//                remove(arr[mo_right]);
                mo_right--;
            }
            while (mo_left < left) {
//                remove(arr[mo_left]);
                mo_left++;
            }
            while (mo_left > left) {
                mo_left--;
//                add(arr[mo_left]);
            }
//            mo_answer[QueryL[i][1]] = current_answer;
        }
    }
//    	public static void add(int val) {
//		ans -= count[val] * 1L * count[val] * 1L * val;
//		count[val]++;
//		ans += count[val] * 1L * count[val] * 1L * val;
//	}

    //------->ends !!
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
