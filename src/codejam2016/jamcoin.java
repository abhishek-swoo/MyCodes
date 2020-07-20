package codejam2016;

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

class jamcoin {

    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new jamcoin().ace();
    }

    //Solution !!

    void solution() {
        long[][] base = new long[11][16];
        for (int i = 2; i < 11; i++) {
            for (int j = 0; j < 16; j++) {
                base[i][j] = (long) Math.pow(i, j);
            }
        }
        out.println("Case #1:");
        for (int tn = inti(); tn > 0; tn--) {
            int l=inti(),s=inti();
            int[] binary = new int[16];
            int count=0;
            boolean flag=false,done=false;
            for(int i=0;i<(int)2e14;i++){
                binary=fillArray(i, binary);
                long divisors[]=new long[11];
                if(flag){
                    for(int j=0;j<11;j++){
                        divisors[j]=0;
                        flag=false;
                    }
                }
                for(int b=2;b<=10;b++){
                    long num=MakeNumber(b, base, binary);
                    long div=isPrime(num);
                    if(div==-1){
                        flag=true;
                        break;
                    }
                    else{
                        if(b==10)
                            done=true;
                        divisors[b]=div;
                    }
                }
                if(done){
                    for(int j=0;j<16;j++){
                        out.print(binary[j]);
                    }
                    for(int j=2;j<=10;j++){
                        out.print(" "+divisors[j]);
                    }
                    count++;
                    out.println();
                    if(count>=s)
                        break;
                    done=false;
                }
                
            }
        }
    }

    long MakeNumber(int b, long base[][], int binary[]) {
        long num = 0;
        for (int i = 0; i < 16; i++) {
            num += binary[15-i] * base[b][i];
        }
        return num;
    }

    int[] fillArray(long n, int binary[]) {
        for (int i = 0; i < 16; i++) {
            binary[i] = 0;
        }
        binary[0]=1;
        binary[15]=1;
        int i = 1;
        while (n > 0 && i<15) {
            binary[i++] = (int) (n & 1);
            n = n >> 1;
        }
        return binary;
    }

    long isPrime(long n) {
        int i;
        if (n == 2) {
            return -1;
        }
        if ((n & 1) == 0) {
            return 2;
        }
        for (i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) {
                return i;
            }
        }
        return -1;
    }

    //------->ends !!
    void ace() throws IOException {
        out = new PrintWriter("D:\\outp.txt");
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

    @SuppressWarnings("empty-statement")
    int skip() {
        int b;
        while ((b = readByte()) != -1 && isSpaceChar(b));
        return b;
    }
    
    @SuppressWarnings("empty-statement")
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

    @SuppressWarnings("empty-statement")
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
