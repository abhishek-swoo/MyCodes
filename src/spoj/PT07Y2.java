
package spoj;

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
import java.util.Stack;

class PT07Y2 {
    InputStream obj;
    PrintWriter out;
    String check="";
    public static void main(String[] args)throws IOException{
        new PT07Y2().ace();
    }
    //Solution !!
    boolean visited[];
    void solution()
    {
        int n=inti(),m=inti();
        visited=new boolean[n];
        int from[]=new int[m];
        int to[]=new int[m];
        for(int i=0;i<m;i++){
            from[i]=inti()-1;
            to[i]=inti()-1;
        }
        int graph[][]=packD(n, from, to);
//        pa(graph);
        dfs(0, graph);
    }
    void dfs(int source,int graph[][])
    {
        boolean flag=false;
        Stack<Integer> stack=new Stack<>();
        stack.push(source);
        while(!stack.isEmpty()){
            int v=stack.pop();
//            pa(visited);
            if(!visited[v])
            {
                visited[v]=true;
                for(int i=0;i<graph[v].length;i++){
                    int u=graph[v][i];
                    stack.push(u);
                }
            }
            else{
//                System.out.println("gsh "+v);
                flag=true;
            }
        }
        if(flag)
            out.println("NO");
        else out.println("YES");
    }
    
    //    this code makes graph in 2 D array(undirected) where n is number of vertices & 0 based indexing
static int[][] packD(int n, int[] from, int[] to) {
//    this part of code is taken from "uwi" submission of codechef problem KNODES    
	int[][] g = new int[n][];
	int[] p = new int[n];
	for (int f : from)
            p[f]++;
//	for (int t : to)
//	    p[t]++;
	for (int i = 0; i < n; i++)
	    g[i] = new int[p[i]];
	for (int i = 0; i < from.length; i++) {
	    g[from[i]][--p[from[i]]] = to[i];
//	    g[to[i]][--p[to[i]]] = from[i];
	}
	return g;
}
    //------->ends !!
    void ace()throws IOException{
        out=new PrintWriter(System.out);
        obj=check.isEmpty() ? System.in : new ByteArrayInputStream(check.getBytes());
//        obj=check.isEmpty() ? new FileInputStream("location of file") : new ByteArrayInputStream(check.getBytes());
//        long t1=System.currentTimeMillis();
        solution();
//        long t2=System.currentTimeMillis();
//        out.println(t2-t1);
        out.flush();
        out.close();
    }
    byte inbuffer[]=new byte[1024];
    int lenbuffer=0,ptrbuffer=0;
    int readByte(){
        if(lenbuffer==-1) throw new InputMismatchException();
        if(ptrbuffer>=lenbuffer){ ptrbuffer=0;
            try { lenbuffer=obj.read(inbuffer); } catch (IOException e) { throw new InputMismatchException(); } }
        if(lenbuffer<=0) return -1;
        return inbuffer[ptrbuffer++];
    }
    boolean isSpaceChar(int c){ return (!(c>=33 && c<=126));}
    
    String stri(){int b=skip();
        StringBuilder sb=new StringBuilder();
        while(!(isSpaceChar(b))) // when nextLine, (isSpaceChar(b) && b!=' ')
        { sb.appendCodePoint(b); b=readByte(); }
        return sb.toString();
    }
    int skip(){ int b; while((b=readByte())!=-1 && isSpaceChar(b)); return b;}
    int inti(){
        int num = 0, b; boolean minus = false;
        while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if(b == '-'){ minus = true; b = readByte(); }
        while(true){
            if(b >= '0' && b <= '9'){ num = num * 10 + (b - '0'); }
            else{ return minus ? -num : num; }
            b = readByte();
        }
    }
    long loni(){
        long num = 0; int b; boolean minus = false;
        while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
        if(b == '-'){ minus = true; b = readByte(); }
        while(true){
            if(b >= '0' && b <= '9'){ num = num * 10 + (b - '0'); }
            else{ return minus ? -num : num; } b = readByte(); } }
    float fl(){return Float.parseFloat(stri());}
    double dou(){return Double.parseDouble(stri());}
    char chi(){return (char)skip();}
    int[] arri(int n){int a[]=new int[n];
        for(int i=0;i<n;i++)
            a[i]=inti();
        return a;}
    long[] arrl(int n){long a[]=new long[n];
        for(int i=0;i<n;i++)
            a[i]=loni();
        return a;}
    String[] stra(int n){String a[]=new String[n];
        for(int i=0;i<n;i++)
            a[i]=stri();
        return a;}
    private static void pa(Object... o) { System.out.println(Arrays.deepToString(o));}
//    uwi mod pow function
        public static long pow(long a, long n, long mod) {
//		a %= mod;
	long ret = 1;
	int x = 63 - Long.numberOfLeadingZeros(n);
	for (; x >= 0; x--) { ret = ret * ret % mod; if (n << 63 - x < 0) ret = ret * a % mod; }
	return ret; }
    int gcd(int a, int b){ if (a == 0) return b; return gcd(b%a, a); }
}
