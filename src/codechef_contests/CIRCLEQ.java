

package codechef_contests;

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;
class CIRCLEQ {

    InputStream obj;
    PrintWriter out;
    String check = "";


    //Solution !!

    void solution() {
        int n=inti(),q=inti();
        double x[]=new double[n];
        double y[]=new double[n];
        double r[]=new double[n];
        for(int i=0;i<n;i++){
            x[i]=dou();
            y[i]=dou();
            r[i]=dou();
        }
        for(int i=0;i<q;i++){
            double x1=dou(),y1=dou(),x2=dou(),y2=dou();
            double sum=0;
            for(int j=0;j<n;j++){
                sum+=area(x1, x2, y1, y2, x[j], y[j], r[j]);
            }
            out.println(sum);
        }
    }
    
//    this part of code is taken from http://stackoverflow.com/questions/622287/area-of-intersection-between-circle-and-rectangle
    double newSection(double h, double r) {
        assert (r >= 0);
        return (h < r) ? Math.sqrt(r * r - h * h) : 0;
    }

    double findOne(double x, double h, double r) {
        return .5f * (Math.sqrt(1 - x * x / (r * r)) * x * r + r * r * Math.asin(x / r) - 2 * h * x);
    }

    double myarea(double x0, double x1, double h, double r) {
        if (x0 > x1) {
            double temp = x0;
            x0 = x1;
            x1 = temp;
        }
        double s = newSection(h, r);
        return findOne(Math.max(-s, Math.min(s, x1)), h, r) - findOne(Math.max(-s, Math.min(s, x0)), h, r);
    }

    double area(double x0, double x1, double y0, double y1, double r) {
        if (y0 > y1) {
            double temp = y0;
            y0 = y1;
            y1 = temp;
        }
        if (y0 < 0) {
            if (y1 < 0) {
                return area(x0, x1, -y0, -y1, r);
            } else {
                return area(x0, x1, 0, -y0, r) + area(x0, x1, 0, y1, r);
            }
        } else {
            assert (y1 >= 0);
            return myarea(x0, x1, y0, r) - myarea(x0, x1, y1, r);
        }
    }

    double area(double x0, double x1, double y0, double y1, double cx, double cy, double r) {
        x0 -= cx;
        x1 -= cx;
        y0 -= cy;
        y1 -= cy;
        return area(x0, x1, y0, y1, r);
    }

    //------->ends !!


    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new CIRCLEQ().ace();
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

    int[][] ar2D(int n,int m){
        int ark[][]=new int[n][m];
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                ark[i][j]=inti();
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
