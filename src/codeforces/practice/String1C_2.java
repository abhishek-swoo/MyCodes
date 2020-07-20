package codeforces.practice;

/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;

public class String1C_2 {

    InputStream obj;
    PrintWriter out;
    String check = "";

    //Solution !!
    void solution() {
        int n = inti(), m = inti();
        root =new TrieNode();
        String arr[] = stra(n);
        for (int i = 0; i < n; i++) {
            add(root,arr[i].toCharArray(),0);
        }
        for (int i = 0; i < m; i++) {
            char c[]=stri().toCharArray();
            boolean flag = find_one_change(root, c, 0, 0);
            if (flag) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    //------->ends !!
        int unique=0;
        TrieNode root;

        public void add(TrieNode curr, char a[], int pos) {
            
            if (pos == a.length) {
                if (!curr.isLeaf) {
                    unique++;
                }
                curr.isLeaf = true;
                return;
            }
            if (curr.child[a[pos] - 'a'] == null) {
                curr.child[a[pos] - 'a'] = new TrieNode();
            }
            add(curr.child[a[pos] - 'a'], a, pos + 1);
        }
        public boolean find_one_change(TrieNode curr, char a[], int pos,int distinct) {
            if(distinct>1)
                return false;
            boolean ret=false;
            if (pos == a.length) {
                return curr.isLeaf && distinct==1;
            }
            for(int i=0;i<3;i++){
                if(curr.child[i]!=null){
                    if(i==a[pos]-'a')
                        ret=ret|find_one_change(curr.child[i], a, pos+1, distinct);
                    else ret=ret|find_one_change(curr.child[i], a, pos+1, distinct+1);
                }
            }
           return ret;
        }

    static public class TrieNode {

        boolean isLeaf;
        TrieNode child[];

        TrieNode() {
            isLeaf = false;
            child = new TrieNode[3];
        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new String1C_2().ace();
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
