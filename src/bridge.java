
/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;

class bridge {

    InputStream obj;
    PrintWriter out;
    String check = "";

    //Solution !!
    int count=0;
    int num[];
    int low[];
    int parent[];
    int rootChild=0;
    int root=-1;
    HashSet<String> hhSet=new HashSet<>();
    ArrayList<Integer> a=new ArrayList<>();
    ArrayList<Integer> b=new ArrayList<>();
    ArrayList<Integer> a1=new ArrayList<>();
    ArrayList<Integer> b1=new ArrayList<>();
    void solution() {
        int n = inti(), m = inti();
        visit=new boolean[n];
        int from[] = new int[m];
        int to[] = new int[m];
        num=new int[n];
        low=new int[n];
        parent=new int[n];
        for (int i = 0; i < m; i++) {
            from[i] = inti() - 1;
            to[i] = inti() - 1;
        }
        int g[][] = packU(n, from, to);
        pa(g);
        for(int i=0;i<n;i++){
            num[i]=-1;
        }
        for(int i=0;i<n;i++){
            if(num[i]==-1)
                bridgeFinding(g, i);
        }
        int len=a.size();
        int count1=0;
        boolean flag[]=new boolean[len];
        for(int i=0;i<len;i++){
            count1=0;
            for(int v:g[a.get(i)]){
                if(v==b.get(i))
                    count1++;
                if(v==b.get(i) && count1>1 ){
                    flag[i]=true;
                    break;
                }
            }
        }
        for(int i=0;i<len;i++){
            if(!flag[i]){
                a1.add(a.get(i));
                b1.add(b.get(i));
            }
        }
        dfs(g, a1.get(0));
        boolean f=false;
        int l=a1.size();
        for(int i=0;i<l;i++){
            if(!visit[a1.get(i)] || !visit[b1.get(i)]){
                f=true;
                break;
            }
        }
        if(f){
            out.println("NO");
        }
        else out.println("YES");
    }

    boolean visit[];
    int size = 0;

    void dfs(int graph[][], int s) {
        Stack<Integer> st = new Stack<>();
        st.push(s);
        while (!st.isEmpty()) {
            int v = st.pop();
            if (!visit[v]) {
                visit[v] = true;
                size++;
                for (int i = 0; i < graph[v].length; i++) {
                    int w = graph[v][i];
                    st.push(w);
                }
            }
        }
    }
    int par=0;
    void dfs2(int graph[][], int s,int p) {
        Stack<Integer> st = new Stack<>();
        st.push(s);
        while (!st.isEmpty()) {
            int v = st.pop();
            if (!visit[v]) {
                visit[v] = true;
                size++;
                for (int i = 0; i < graph[v].length; i++) {
                    int w = graph[v][i];
                    if(w==p)
                        continue;
                    dfs2(graph, w,v);
                }
            }
        }
    }
    
    void bridgeFinding(int g[][],int u){
        num[u]=count++;
        low[u]=num[u];
        for(int v:g[u]){
            if(num[v]==-1){
                parent[v]=u;
                if(u==root){
                    rootChild++;
                }
                bridgeFinding(g, v);
                if(low[v]>num[u]){
                    a.add(u);
                    b.add(v);
                    out.println(u+" "+v);
                }
                low[u]=Math.min(low[u], low[v]);
            }
            else if(v!=parent[u]){
                low[u]=Math.min(low[u], num[v]);
            }
        }
    }
    
    static int[][] packU(int n, int[] from, int[] to) {
        int[][] g = new int[n][];
        int[] p = new int[n];
        for (int f : from) {
            p[f]++;
        }
        for (int t : to) {
            p[t]++;
        }
        for (int i = 0; i < n; i++) {
            g[i] = new int[p[i]];
        }
        for (int i = 0; i < from.length; i++) {
            g[from[i]][--p[from[i]]] = to[i];
            g[to[i]][--p[to[i]]] = from[i];
        }
        return g;
    }
    //------->ends !!

    public static void main(String[] args) throws IOException {
        new Thread(null, () -> {
            try {
                new bridge().ace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (StackOverflowError e) {
                System.out.println("RTE");
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
