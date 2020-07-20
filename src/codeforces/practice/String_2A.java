

package codeforces.practice;

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;
class String_2A {

    InputStream obj;
    PrintWriter out;
    String check = "";


    //Solution !!

    void solution() {
        int m=inti();
        String s[]=new String[m];
        for(int i=0;i<m;i++){
            s[i]=stri().toLowerCase();
        }
        HashMap<String,String> hm=new HashMap<>();
        int n=inti();
        for(int i=0;i<n;i++){
            String a=stri().toLowerCase(),b=stri().toLowerCase();
            int temp1=0,temp2=0;
            if(hm.containsKey(a)){
                String bb=hm.get(a);
                for(int j=0;j<a.length();j++){
                    if(a.charAt(j)=='r')
                        temp1++;
                }
                for(int j=0;j<bb.length();j++){
                    if(bb.charAt(j)=='r')
                        temp2++;
                }
                if(temp1>temp2){
                    
                }
            }
            hm.put(a, b);
//            hm.put(b, a);
        }
        pa(hm);
        int rs=0,length=0;
        for(int i=0;i<m;i++){
            int temp1=0,temp2=0;
            if(hm.containsKey(s[i])){
                String bb=hm.get(s[i]);
                for(int j=0;j<s[i].length();j++){
                    if(s[i].charAt(j)=='r')
                        temp1++;
                }
                for(int j=0;j<bb.length();j++){
                    if(bb.charAt(j)=='r')
                        temp2++;
                }
                if(temp1>temp2){
                    rs+=temp2;
                    length+=bb.length();
                }
                else if(temp1<=temp2){
                    rs+=temp1;
                    length+=Math.min(bb.length(), s[i].length());
                }
            }
            else {
                for(int j=0;j<s[i].length();j++){
                    if(s[i].charAt(j)=='r')
                        temp1++;
                }
                rs+=temp1;
                length+=s[i].length();
            }
        }
        out.println(rs+" "+length);
    }

    //------->ends !!


    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new String_2A().ace();
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
