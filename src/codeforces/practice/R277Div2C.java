

package codeforces.practice;

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;
class R277Div2C {

    InputStream obj;
    PrintWriter out;
    String check = "";


    //Solution !!
    int maxDigit[];
    int minDigit[];
    int dp[][];
    int m;
    void solution() {
        m=inti();
        int s=inti();
        if(m==1 && s==0){
            out.println("0 0");
            return;
        }
        if (s>9*m || (s==0 && m>0)) {
            out.println("-1 -1");
            return;
        }
        
        maxDigit=new int[m];
        minDigit=new int[m];
        dp=new int[m+1][s+1];
        initialize();
        maximum(0, s);
        initialize();
        minimum(0, s);
        print(minDigit);
        out.print(" ");
        print(maxDigit);
    }
    void initialize(){
        for(int i=0;i<=m;i++)
            Arrays.fill(dp[i], -1);
    }
    void print(int arr[]){
        for(int i=0;i<arr.length;i++)
            out.print(arr[i]);
    }

    int maximum(int i,int sum){
        int end=0;
        if(i==0)
            end=1;
        if(i==m && sum==0)
            return 1;
        if(i>=m || sum<0)
            return 0;
        if(dp[i][sum]!=-1)
            return dp[i][sum];
        
        for(int j=9;j>=end;j--){
            if(maximum(i+1, sum-j)==1){
                maxDigit[i]=j;
                return dp[i][sum] = 1;
            }
        }
        return dp[i][sum]=0;
    }
    
    int minimum(int i,int sum){
        int start=0;
        if(i==0)
            start=1;
        if(i==m && sum==0)
            return 1;
        if(i>=m || sum<0)
            return 0;
        
        if(dp[i][sum]!=-1)
            return dp[i][sum];
        for(int j=start;j<=9;j++){
            if(minimum(i+1, sum-j)==1){
                minDigit[i]=j;
                return dp[i][sum] = 1;
            }
        }
        return dp[i][sum]=0;
    }
    //------->ends !!


    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new R277Div2C().ace();
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
