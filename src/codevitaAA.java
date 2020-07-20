

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;
class codevitaAA {

    InputStream obj;
    PrintWriter out;
    String check = "";


    //Solution !!

    void solution()throws IOException{
        BufferedReader ob=new BufferedReader(new FileReader("C:\\Users\\Abhishek Shankhadhar\\Desktop\\a.txt"));
        char c=chi();
        if(c=='E'){
            String s=ob.readLine();
            String num=stri();
            int len=num.length();
            StringBuilder sb=new StringBuilder();
            sb.append(len);
            sb.append('-');
            sb.append(num);
            for(int i=0;i<s.length();i++){
                int ch=s.charAt(i);
                int arr[]=dec(ch, 64, 16);
                StringBuilder sb1=new StringBuilder();
                boolean b=false;
                for(int j=0;j<arr.length;j++){
                    if(arr[j]!=0){
                        b=true;
                    }
                    if(b){
                        if(arr[j]<10)
                            sb1.append(arr[j]);
                        else {
                            switch (arr[j]) {
                                case 10:
                                    sb1.append('A');
                                    break;
                                case 11:
                                    sb1.append('B');
                                    break;
                                case 12:
                                    sb1.append('C');
                                    break;
                                case 13:
                                    sb1.append('D');
                                    break;
                                case 14:
                                    sb1.append('E');
                                    break;
                                case 15:
                                    sb1.append('F');
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
                sb1.reverse();
//                String sw=sb1.toString();
                sb.append('-');
                sb.append(sb1);
            }
            out.println(sb);
        }
        else {
            String s=stri();
            String string[]=s.split("-");
            out.println(string[1]);
//            for(int i=2;i<string.length;i++){
//                string[i]=rev(string[i]);
//            }
            StringBuilder sb=new StringBuilder();
            for(int i=2;i<string.length;i++){
                int a[]=new int[64];
                int num=63;
                for(int j=0;j<string[i].length();j++){
                    char ch=string[i].charAt(j);
                    int ans=-1;
                    if(ch=='A'){
                        a[num--]=10;
                    }
                    else if(ch=='B'){
                        a[num--]=11;
                    }
                    else if(ch=='C'){
                        a[num--]=12;
                    }
                    else if(ch=='D'){
                        a[num--]=13;
                    }
                    else if(ch=='E'){
                        a[num--]=14;
                    }else if(ch=='F'){
                        a[num--]=15;
                    }
                    else {
                        a[num--]=ch-'0';
                    }
                }
//                pa(a);
                int en=enc(a, 16);
                sb.append((char)en);
            }
            out.println(sb);
        }
    }
    int enc(int[] a, int d) {
        int code = 0;
        for (int i = 0; i < a.length; i++) {
            code = code * d + a[i];
        }
        return code;
    }
    
    StringBuilder fun(int n){
        StringBuilder sb=new StringBuilder();
        while (n!=0) {            
            sb.append(n%10);
            n/=10;
        }
        return sb;
    }
    String rev(String s){
        String s1="";
        for(int i=s.length()-1;i>=0;i--){
            s1+=s.charAt(i);
        }
        return s1;
    }
    static int[] dec(int code, int n, int d) {
        int[] a = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            a[i] = code % d;
            code /= d;
        }
        return a;
    }

    //------->ends !!


    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new codevitaAA().ace();
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
//        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
        obj=check.isEmpty() ? new FileInputStream("C:\\Users\\Abhishek Shankhadhar\\Desktop\\a.txt") : new ByteArrayInputStream(check.getBytes());
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
