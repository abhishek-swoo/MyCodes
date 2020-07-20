package Codeforces_contest;
/**
 *
 * @author ABHISHEK SHANKHADHAR
 *
 */
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
//import java.io.FileInputStream;
import java.util.Arrays;
import java.util.InputMismatchException;

class R376Div2D {

    InputStream obj;
    PrintWriter out;
    String check = "";

    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new R376Div2D().ace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (StackOverflowError e) {
                    System.out.println("RTE");
                }
            }
        }, "1", 1 << 26).start();
    }
    //Solution !!

    void solution() {
        Trie t = new Trie();

        int q = inti();
        t.insert(0, 30, t.root);

        for (int i = 0; i < q; i++) {
            char c = chi();
            int n = inti();

            if (c == '+') {
                t.insert(n, 30, t.root);
            } else if (c == '-') {
                t.delete(n, 30, t.root);
            } else {
                out.println(t.maxXor(n, 30, t.root));
            }
        }
    }

    //------->ends !!
    void ace() throws IOException {
        out = new PrintWriter(System.out);
        obj = check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
//        out=new PrintWriter("C:\\Users\\Abhishek Shankhadhar\\Desktop\\a\\in1b.txt");
//        obj=check.isEmpty() ? new FileInputStream("C:\\Users\\Abhishek Shankhadhar\\Desktop\\a\\g2.in") : new ByteArrayInputStream(check.getBytes());
        solution();
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

    private static void tr(Object... o) {
        System.out.println(Arrays.deepToString(o));
    }
}

class TrieNode {

    TrieNode[] arr;
    int count;

    // Initialize your data structure here.
    public TrieNode() {
        this.arr = new TrieNode[2];
        count=0;
    }

}

class Trie {

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(int word, int i, TrieNode p) {
        if (i == -1) {
            return;
        }
        if ((word & (1 << i)) == 0) {
            if (p.arr[0] == null) {
                p.arr[0] = new TrieNode();
            }
            p.arr[0].count++;
            insert(word, i - 1, p.arr[0]);
        } else {
            if (p.arr[1] == null) {
                p.arr[1] = new TrieNode();
            }
            p.arr[1].count++;
            insert(word, i - 1, p.arr[1]);
        }
    }

    public void delete(int word, int i, TrieNode p) {
        if (i == -1) {
            return;
        }
        if ((word & (1 << i)) == 0) {
            p.arr[0].count--;
            delete(word, i - 1, p.arr[0]);
        } else {
            p.arr[1].count--;
            delete(word, i - 1, p.arr[1]);
        }
    }

    public int maxXor(int word, int i, TrieNode p) {
        if (i == 0) {
            if ((word & 1 << i) == 0) {
                return (p.arr[1] == null || p.arr[1].count == 0) ? 0 : 1;
            } else {
                return (p.arr[0] == null || p.arr[0].count == 0) ? 0 : 1;
            }
        } else if ((word & 1 << i) == 0) {
            return (p.arr[1] == null || p.arr[1].count == 0) ? maxXor(word, i - 1, p.arr[0]) : (maxXor(word, i - 1, p.arr[1]) + (1 << i));
        } else {
            return (p.arr[0] == null || p.arr[0].count == 0) ? maxXor(word, i - 1, p.arr[1]) : (maxXor(word, i - 1, p.arr[0]) + (1 << i));
        }
    }
}
