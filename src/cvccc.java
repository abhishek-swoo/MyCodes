

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;
class cvccc {

    InputStream obj;
    PrintWriter out;
    String check = "";


    //Solution !!

    void solution() {
        String s=stri();
        String s1[]=s.split(",");
        long left=Long.parseLong(s1[0]);
        long right=Long.parseLong(s1[1]);
        long dist=Integer.parseInt(s1[2]);
        ArrayList<Long> al=new ArrayList<>();
        if(left>right){
            out.println("0,0,0");
            return;
        }
        for(long i=left;i<=right;i++){
            if(isPalindrome(String.valueOf(i))){
                al.add(i);
            }
        }
        long lans=0,rans=0;
        int i=0,j=0,num=0;
        long diff=Long.MAX_VALUE/3;
        while (true) {
            if(j>=al.size())
                break;
            if(al.get(j)-al.get(i)<dist && num<(j-i+1)){
                lans=al.get(i);
                rans=al.get(j);
                diff=rans-lans;
                num=j-i+1;
                j++;
            }
            else if(al.get(j)-al.get(i)<dist && num==(j-i+1)){
                if(al.get(j)-al.get(i)<diff){
                    lans=al.get(i);
                    rans=al.get(j);
                    diff=j-i+1;
                    
                }
                j++;
            }
            else if(i>=j)
                j++;
            else {
                i++;
            }
        }
        
        out.println(num+","+lans+","+rans);
    }
    
    boolean isPalindrome(String s){
        int i=0,j=s.length()-1;
        while (j>=i) {            
            if(s.charAt(i)!=s.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    //------->ends !!


    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new cvccc().ace();
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
    long lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }
}
