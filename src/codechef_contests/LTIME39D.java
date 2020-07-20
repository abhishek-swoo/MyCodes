
//not correct

package codechef_contests;
/**
 *
 * @author Abhishek Shankhadhar
 */
import java.io.*;
import java.util.*;
class LTIME39D {

    InputStream obj;
    PrintWriter out;
    String check = "";


    //Solution !!
    int diameter=0;
    int dp1[],dp2[];
    int weight[];
    boolean visit[];
    int mod=(int)1e9+7;
    long ans=1;
//    ArrayList<Integer>[] arr;
    void solution() {
        int n=inti();
        dp1=new int[n+1];
        dp2=new int[n+1];
        weight=new int[n+1];
        long p=1;
        for(int i=1;i<=n;i++){
            weight[i]=inti();
            p=(p*weight[i])%mod;
        }
//        out.println(p);
        ArrayList<Integer> arr[]=new ArrayList[n+1];
//        arr=new ArrayList[n+1];
        
        for(int i=1;i<=n;i++){
            arr[i]=new ArrayList<>();
        }
        
        int from[]=new int[n+1];
        int to[]=new int[n+1];
        for(int i=1;i<n;i++){
            from[i]=inti();
            to[i]=inti();
        }
        
        int remove[]=arri(n-1);
        ArrayList<Long> ag=new ArrayList<>();
        for(int i=n-2;i>=0;i--){
            visit=new boolean[n+1];
//            int u=inti();
            int a=from[remove[i]],b=to[remove[i]];
            arr[a].add(b);
            arr[b].add(a);
            for(int j=1;j<=n;j++){
                if(!visit[j]){
                    visit[j]=true;
                    dfs(j, 0, arr);
                    ans=(ans*diameter)%mod;
                    diameter=0;
                }
            }
            ag.add(ans);
            for(int gg=ag.size()-1;gg>=0;gg--)
                out.println(ag.get(gg));
            ans=1;
        }
        
        visit=new boolean[n+1];
        for(int j=1;j<=n;j++){
                if(!visit[j]){
                    visit[j]=true;
                    dfs(j, 0, arr);
                    ans=(ans*diameter)%mod;
                    diameter=0;
                }
            }
        dfs(1, 0, arr);
        out.println(ans);
//        dfs(1, 0,arr);
    }
    
    void dfs(int V,int pV,ArrayList<Integer> arr[]){
        ArrayList<Integer> al=new ArrayList<>();
        for(int i:arr[V]){
            if(i==pV) continue;
            visit[i]=true;
            dfs(i, V,arr);
            al.add(dp1[i]);
        }
        Collections.sort(al);
        dp1[V]=weight[V];
        if(!al.isEmpty())
            dp1[V]+=al.get(al.size()-1);
        if(al.size()>=2){
            dp2[V]=weight[V]+al.get(al.size()-1)+al.get(al.size()-2);
        }
        
        diameter=Math.max(Math.max(diameter, dp1[V]), dp2[V]);
    }

    //------->ends !!


    public static void main(String[] args) throws IOException {
        new Thread(null, () -> {
        try {
            new LTIME39D().ace();
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
