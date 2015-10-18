package OCT15;/**
 * Created by ABHISHEK SHANKHADHAR on 09-10-2015.
 */

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

class sphere1 {
    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new sphere1().ace();
    }

    //Solution !!
    void solution() {
        int n = inti(), m = inti(), c = inti(), sum = 0, mod = 1000000007, cnt = 1;
        long arru[] = new long[c + 1];
        long arrl[] = new long[c + 1];
        long mul[] = new long[c + 1];
        for (int i = 0; i < n; i++) {
            arru[inti()]++;
        }
        for (int i = 0; i < m; i++) arrl[inti()]++;
        for (int i = 0; i <= c; ++i) {
            mul[i] = (arrl[i] * arru[i]) % mod;
        }
        long copy[] = new long[c + 1];
        for (int i = 0; i <= c; i++) copy[i] = mul[i];
        for (int j = 1; j < c; j++) {
            for (int i = c - 1; i >= j; i--)
                mul[i] = (mul[i] + mul[i + 1]) % mod;
            System.out.println("Cumulative");
            System.out.println(Arrays.toString(mul));
            for (int i = 1; i <= c - j; i++)
                mul[i + j] = (mul[i + j] * copy[i]) % mod;
            System.out.println("after multiply");
            System.out.println(Arrays.toString(mul));
            long ans = 0;
            for (int i = c; i > j; i--)
                ans = (ans + mul[i]) % mod;
            out.print(ans + " ");
        }
        out.print("0");
        out.println(Arrays.toString(mul));
    }

    //------->ends !!
    void ace() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        solution();
        out.flush();
        out.close();
    }

    byte inbuffer[] = new byte[1024];
    int lenbuffer = 0, ptrbuffer = 0;

    int readByte() {
        if (lenbuffer == -1)
            throw new InputMismatchException();
        if (ptrbuffer >= lenbuffer) {
            ptrbuffer = 0;
            try {
                lenbuffer = obj.read(inbuffer);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
        }
        if (lenbuffer <= 0)
            return -1;
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
        while ((b = readByte()) != -1 && isSpaceChar(b)) ;
        return b;
    }

    int inti() {
        int num = 0, b;
        boolean minus = false;
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
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
        while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
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
        for (int i = 0; i < n; i++)
            a[i] = inti();
        return a;
    }

    long[] arrl(int n) {
        long a[] = new long[n];
        for (int i = 0; i < n; i++)
            a[i] = loni();
        return a;
    }

    String[] stra(int n) {
        String a[] = new String[n];
        for (int i = 0; i < n; i++)
            a[i] = stri();
        return a;
    }
}
